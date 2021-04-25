import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import util.NumberProperties;
import util.UserProgram;

import java.text.MessageFormat;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

public class NumbersTest extends StageTest {
    private static final Random random = new Random();
    private static final long RANDOM_NUMBERS_TESTS = 20;
    private static final long TEST_FIRST_NUMBERS = 20;
    private static final long MAX_NUMBER = Long.MAX_VALUE;

    private final UserProgram checker = new UserProgram()
            .add($ -> {
                $.key = Key.HELP;
                $.regexp = "supported requests";
                $.feedback = "The program should display an instruction for the user";
                $.flags += Pattern.LITERAL;
            }).add($ -> {
                $.key = Key.REQUEST;
                $.regexp = "enter a request";
                $.feedback = "The program should ask for a request.";
                $.flags += Pattern.LITERAL;
            }).add($ -> {
                $.key = Key.NOT_NATURAL;
                $.regexp = "(this|the) number is( not|n't) natural";
                $.feedback = "Number {0} is not natural. Expected error message.";
            }).add($ -> {
                $.key = Key.PROPERTIES;
                $.regexp = "properties of ";
                $.feedback = "The first line of number''s properties should contains \"{1}\".";
                $.flags += Pattern.LITERAL;
            });
    private final long[] notNaturalNumbers = {-1, -2, -3, -4, -5};

    @DynamicTest(data = "notNaturalNumbers", order = 10)
    CheckResult notNaturalNumbersTest(final long number) {
        return checker.start()
                .check(Key.HELP)
                .check(Key.REQUEST)
                .execute(number)
                .check(Key.NOT_NATURAL)
                .check(Key.HELP)
                .check(Key.REQUEST)
                .execute(0)
                .finished()
                .correct();
    }

    @DynamicTest(order = 20)
    CheckResult finishByZeroTest() {
        return checker.start()
                .check(Key.HELP)
                .check(Key.REQUEST)
                .execute(-5)
                .check(Key.NOT_NATURAL)
                .check(Key.HELP)
                .execute(-7635)
                .check(Key.NOT_NATURAL)
                .check(Key.HELP)
                .check(Key.REQUEST)
                .execute(0)
                .finished()
                .correct();
    }

    @DynamicTest(order = 30)
    CheckResult firstAndRandomNumbersTest() {
        final var numbers = LongStream.concat(
                LongStream.range(1, TEST_FIRST_NUMBERS),
                random.longs(RANDOM_NUMBERS_TESTS, 1, MAX_NUMBER)
        );
        checker.start();

        numbers.forEach(number -> {
            checker.check(Key.REQUEST).execute(number).check(Key.PROPERTIES);

            for (var property : NumberProperties.values()) {
                final var name = property.name().toLowerCase();
                checker.contains(name, "The property {1} wasn''t found for number {0}.");

                final var expected = property.test(number);
                final var actual = Boolean.parseBoolean(property.extractValue(checker.getOutput())
                        .orElseThrow(() -> new WrongAnswer(
                                "The value for property " + name + " was not found.")));

                if (expected != actual) {
                    throw new WrongAnswer(MessageFormat.format(
                            "For property {0} the expected value is {1} but found {2}.",
                            name, expected, actual));
                }
            }
        });
        return checker.execute(0).finished().correct();
    }

    private enum Key {HELP, REQUEST, NOT_NATURAL, PROPERTIES}

}
