import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;

final class TextChecker {
    static class Arguments {
        String key;
        String regexp;
        String feedback;
        int flags = Pattern.CASE_INSENSITIVE;
    }

    private static final class Checker implements Predicate<String> {
        final Pattern expected;
        final String feedback;

        Checker(final Pattern expected, final String feedback) {
            this.expected = expected;
            this.feedback = feedback;
        }

        @Override
        public boolean test(String actual) {
            return expected.matcher(actual).find();
        }

        String getFeedback(Object... args) {
            return MessageFormat.format(feedback, args);
        }
    }

    private final Map<String, Checker> map = new HashMap<>();

    public TextChecker add(Consumer<Arguments> builderFunction) {
        final var args = new Arguments();
        builderFunction.accept(args);
        map.put(args.key, new Checker(Pattern.compile(args.regexp, args.flags), args.feedback));
        return this;
    }

    public void check(String key, String actual, Object... args) {
        final var checker = map.get(key);
        if (checker.test(actual)) {
            return;
        }
        throw new WrongAnswer(checker.getFeedback(args));
    }
}

public class NumbersTest extends StageTest {

    private static final TextChecker checker = new TextChecker()
            .add($ -> {
                $.key = "not-natural";
                $.regexp = "(this|the) number is( not|n't) natural";
                $.feedback = "Number {0} is not natural. Expected error message.";
            })
            .add($ -> {
                $.key = "is-buzz-number";
                $.regexp = "is(?: a| the)? buzz( number)?";
                $.feedback = "Not found message that {0} is a Buzz number.";
            })
            .add($ -> {
                $.key = "is-not-buzz-number";
                $.regexp = "is( not|n't)(?: a| the)? buzz( number)?";
                $.feedback = "Not found message that {0} is not a Buzz number.";
            })
            .add($ -> {
                $.key = "is-divisible";
                $.regexp = "is divisible by";
                $.feedback = "Not found message that {0} is divisible by 7";
                $.flags += Pattern.LITERAL;
            })
            .add($ -> {
                $.key = "is-ends-with";
                $.regexp = "(is|it) ends with";
                $.feedback = "Not found message that {0} is ends with 7";
            })
            .add($ -> {
                $.key = "is-neither";
                $.regexp = "is neither divisible by 7 nor it ends with 7";
                $.feedback = "Not found message that {0} is is neither divisible by 7 nor it ends with 7";
                $.flags += Pattern.LITERAL;
            });


    private static final Pattern IS_BUZZ_NUMBER = Pattern.compile(
            "is(?: a| the)? buzz( number)?", Pattern.CASE_INSENSITIVE);

    private static final Pattern IS_NOT_BUZZ = Pattern.compile(
            "is( not|n't)(?: a| the)? buzz( number)?", Pattern.CASE_INSENSITIVE);

    private static final Pattern IS_DIVISIBLE = Pattern.compile(
            "is divisible by", Pattern.CASE_INSENSITIVE);

    private static final Pattern IS_ENDS_WITH = Pattern.compile(
            "(is|it) ends with", Pattern.CASE_INSENSITIVE);

    private static final Pattern IS_NEITHER = Pattern.compile(
            "is neither divisible by 7 nor it ends with 7", Pattern.CASE_INSENSITIVE);

    private final long[] number = {1, 2, 3, 4, 5, 9_223_372_036_854_775_807L};

    @DynamicTest(data = "number", order = 1)
    CheckResult simpleTest(final long number) {
        final var program = new TestedProgram();

        assertTrue(program.start().toLowerCase().contains("natural number"),
                "The program should ask for a natural number.");

        final var expected = number % 2 == 0 ? "even" : "odd";
        final var actual = program.execute(String.valueOf(number)).toLowerCase();

        assertTrue(actual.contains(expected),
                "Number {0} should be {1}.", number, expected);

        assertTrue(program.isFinished(),
                "Program should finish after calculating parity of the number.");

        return CheckResult.correct();
    }

    private final long[] incorrect = {0, -1, -2, -3, -4, -5};

    @DynamicTest(data = "incorrect", order = 10)
    CheckResult incorrectNumbers(final long number) {
        final var program = new TestedProgram();

        assertTrue(program.start().toLowerCase().contains("natural number"),
                "The program should ask for a natural number.");

        final var actual = program.execute(String.valueOf(number));

        checker.check("not-natural", actual, number);

        assertTrue(program.isFinished(),
                "Program should finish after printing the error message.");

        return CheckResult.correct();
    }

    private final long[] divisible = {7, 14, 21, 28, 35, 123 * 7, 578 * 7, 9865 * 7};

    @DynamicTest(data = "divisible", order = 20)
    CheckResult divisibleNumbers(final long number) {
        final var program = new TestedProgram();

        assertTrue(program.start().toLowerCase().contains("natural number"),
                "The program should ask for a natural number.");

        final var actual = program.execute(String.valueOf(number));

        assertTrue(IS_BUZZ_NUMBER.matcher(actual).find(),
                "Not found message that {0} is a Buzz number.", number);

        assertTrue(IS_DIVISIBLE.matcher(actual).find(),
                "Not found message that {0} is divisible by 7", number);

        assertTrue(program.isFinished(),
                "Program should finish after printing the error message.");

        return CheckResult.correct();
    }

    private final long[] endsWith7 = {17, 27, 97, 107, 308687, 9821007, 204782607};

    @DynamicTest(data = "endsWith7", order = 30)
    CheckResult endsWith7Numbers(final long number) {
        final var program = new TestedProgram();

        assertTrue(program.start().toLowerCase().contains("natural number"),
                "The program should ask for a natural number.");

        final var actual = program.execute(String.valueOf(number));

        assertTrue(IS_BUZZ_NUMBER.matcher(actual).find(),
                "Not found message that {0} is a Buzz number.", number);

        assertTrue(IS_ENDS_WITH.matcher(actual).find(),
                "Not found message that {0} is ends with 7", number);

        assertTrue(program.isFinished(),
                "Program should finish after printing the error message.");

        return CheckResult.correct();
    }

    private final long[] notBuzz = {6, 11, 18, 29, 34};

    @DynamicTest(data = "notBuzz", order = 30)
    CheckResult notBuzzNumbers(final long number) {
        final var program = new TestedProgram();

        assertTrue(program.start().toLowerCase().contains("natural number"),
                "The program should ask for a natural number.");

        final var actual = program.execute(String.valueOf(number));

        assertTrue(IS_NOT_BUZZ.matcher(actual).find(),
                "Not found message that {0} is not a Buzz number.", number);

        assertTrue(IS_NEITHER.matcher(actual).find(),
                "Not found message that {0} is is neither divisible by 7 nor it ends with 7", number);

        assertTrue(program.isFinished(),
                "Program should finish after printing the error message.");

        return CheckResult.correct();
    }

    private static void assertTrue(final boolean condition, final String error, final Object... args) {
        if (!condition) {
            final var feedback = MessageFormat.format(error, args);
            throw new WrongAnswer(feedback);
        }
    }

}
