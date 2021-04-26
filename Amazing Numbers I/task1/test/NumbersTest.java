import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import util.RegexChecker;
import util.UserProgram;

public class NumbersTest extends StageTest {

    private final long[] number = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 17, 23, 33, 107, 777, 196};
    private final long[] notNaturalNumbers = {0, -1, -2, -3, -4, -5};

    @DynamicTest(data = "number", order = 10)
    CheckResult buzzTest(final long number) {
        return new UserProgram()
                .start()
                .contains("natural number", "The program should ask for a natural number.")
                .execute(number)
                .check(new RegexChecker("number is (even|odd)",
                        "Calculate and print the parity of the given number."))
                .contains(number % 2 == 0 ? "even" : "odd", "Number {0} should be {1}.")
                .check(new BuzzChecker(number))
                .finished()
                .result();
    }

    @DynamicTest(data = "notNaturalNumbers", order = 20)
    CheckResult notNaturalNumbersTest(final long number) {
        return new UserProgram()
                .start()
                .execute(number)
                .check(new RegexChecker("number is( not|n't) natural",
                        "Number {0} is not natural. Expected error message."))
                .finished()
                .result();
    }

}
