import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import util.TextChecker;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.function.LongPredicate;
import java.util.regex.Pattern;

enum NumberProperties implements LongPredicate {
    EVEN(number -> number % 2 == 0),
    ODD(number -> number % 2 != 0),
    BUZZ(number -> number % 7 == 0 || number % 10 == 7),
    DUCK(number -> String.valueOf(number).indexOf('0') != -1);

    private final LongPredicate hasProperty;
    private final Pattern pattern = Pattern.compile(
            name().toLowerCase() + "\\s*[:-]\\s*(?<value>true|false)",
            Pattern.CASE_INSENSITIVE);

    NumberProperties(LongPredicate hasProperty) {
        this.hasProperty = hasProperty;
    }

    @Override
    public boolean test(long number) {
        return hasProperty.test(number);
    }

    public Optional<String> extractValue(String output) {
        final var matcher = pattern.matcher(output);
        return matcher.find() ? Optional.of(matcher.group("value")) : Optional.empty();
    }
}

public class NumbersTest extends StageTest {
    private enum Key {ENTER_NUMBER, NOT_NATURAL}

    private static final TextChecker checker = new TextChecker()
            .add($ -> {
                $.key = Key.ENTER_NUMBER;
                $.regexp = "natural number";
                $.feedback = "The program should ask for a natural number.";
                $.flags += Pattern.LITERAL;
            })
            .add($ -> {
                $.key = Key.NOT_NATURAL;
                $.regexp = "(this|the) number is( not|n't) natural";
                $.feedback = "Number {0} is not natural. Expected error message.";
            });

    private final long[] number = {1, 2, 3, 4, 5, 6, 11, 13, 19, 222, 300, 133, 765};

    @DynamicTest(data = "number", order = 1)
    CheckResult simpleTest(final long number) {
        checker.start()
                .check(Key.ENTER_NUMBER)
                .execute(number)
                .contains("properties of " + number,
                        "The first line of number''s properties should contains \"{1}\".");

        for (var property : NumberProperties.values()) {
            final var name = property.name().toLowerCase();
            checker.contains(name, "The property {1} wasn''t found for number {0}.");
            final var expected = property.test(number);
            final var actual = Boolean.parseBoolean(property.extractValue(checker.getOutput())
                    .orElseThrow(() ->
                            new WrongAnswer("The value for property " + name + " was not found.")));
            if (expected != actual) {
                throw new WrongAnswer(MessageFormat.format(
                        "For property {0} the expected value is {1} but found {2}.",
                        name, expected, actual));
            }
        }
        return checker.finish().correct();
    }

    private final long[] notNaturalNumbers = {0, -1, -2, -3, -4, -5};

    @DynamicTest(data = "notNaturalNumbers", order = 10)
    CheckResult notNaturalNumbersTest(final long number) {
        return checker.start()
                .check(Key.ENTER_NUMBER)
                .execute(number)
                .check(Key.NOT_NATURAL)
                .finish()
                .correct();
    }

}
