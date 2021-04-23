import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import util.TextChecker;

import java.util.regex.Pattern;

public class NumbersTest extends StageTest {

    private enum Key {ENTER_NUMBER, BUZZ_NUMBER}

    private static final TextChecker checker = new TextChecker();

    private final long[] number = {1, 2, 3, 4, 5, 9_223_372_036_854_775_807L};

    @DynamicTest(data = "number", order = 1)
    CheckResult simpleTest(final long number) {
        final var expected = number % 2 == 0 ? "even" : "odd";
        return checker.start()
                .check($ -> {
                    $.key = Key.ENTER_NUMBER;
                    $.regexp = "natural number";
                    $.feedback = "The program should ask for a natural number.";
                    $.flags += Pattern.LITERAL;
                })
                .execute(number)
                .contains(expected, "Number {0} should be {1}.")
                .finish()
                .correct();
    }

    private final long[] notNaturalNumbers = {0, -1, -2, -3, -4, -5};

    @DynamicTest(data = "notNaturalNumbers", order = 10)
    CheckResult notNaturalNumbersTest(final long number) {
        return checker.start()
                .check(Key.ENTER_NUMBER)
                .execute(number)
                .check($ -> {
                    $.regexp = "(this|the) number is( not|n't) natural";
                    $.feedback = "Number {0} is not natural. Expected error message.";
                })
                .finish()
                .correct();
    }

    private final long[] divisible = {7, 14, 21, 28, 35, 123 * 7, 578 * 7, 9865 * 7};

    @DynamicTest(data = "divisible", order = 20)
    CheckResult divisibleNumbers(final long number) {
        return checker.start()
                .check(Key.ENTER_NUMBER)
                .execute(number)
                .check($ -> {
                    $.key = Key.BUZZ_NUMBER;
                    $.regexp = "is(?: a| the)? buzz( number)?";
                    $.feedback = "Not found message that {0} is a Buzz number.";
                })
                .contains("explanation", "The output should contains explanation.")
                .contains("is divisible by", "Not found message that {0} {1} 7")
                .finish()
                .correct();
    }

    private final long[] endsWith7 = {17, 27, 97, 107, 308687, 9821007, 204782607};

    @DynamicTest(data = "endsWith7", order = 30)
    CheckResult endsWith7Numbers(final long number) {
        return checker.start()
                .check(Key.ENTER_NUMBER)
                .execute(number)
                .check(Key.BUZZ_NUMBER)
                .contains("explanation", "The output should contains explanation.")
                .contains("ends with", "Not found message that {0} {1} 7")
                .finish()
                .correct();
    }

    private final long[] notBuzz = {6, 11, 18, 29, 34};

    @DynamicTest(data = "notBuzz", order = 30)
    CheckResult notBuzzNumbers(final long number) {
        return checker.start()
                .check(Key.ENTER_NUMBER)
                .execute(number)
                .check($ -> {
                    $.regexp = "is( not|n't)(?: a| the)? buzz( number)?";
                    $.feedback = "Not found message that {0} is not a Buzz number.";
                })
                .contains("explanation", "The output should contains explanation.")
                .check($ -> {
                    $.regexp = "is neither divisible by 7 nor it ends with 7";
                    $.feedback = "Not found message that {0} is not a Buzz number.";
                })
                .contains("is neither divisible by 7 nor it ends with 7", "Not found message that {0} {1}")
                .finish()
                .correct();
    }

}
