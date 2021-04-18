import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

import java.text.MessageFormat;
import java.util.regex.Pattern;

public class NumbersTest extends StageTest {

    private static final Pattern ERROR_MESSAGE = Pattern.compile(
            "(this|the) number is( not|n't) natural", Pattern.CASE_INSENSITIVE);

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

        assertTrue(ERROR_MESSAGE.matcher(actual).find(),
                "Number {0} is not natural. Expected error message.", number);

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
