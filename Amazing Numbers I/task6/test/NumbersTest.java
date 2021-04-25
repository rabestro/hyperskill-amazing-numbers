import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import util.*;

import java.util.Random;
import java.util.stream.LongStream;

public class NumbersTest extends StageTest {
    private static final Random random = new Random();
    private static final long RANDOM_NUMBERS_TESTS = 20;
    private static final long TEST_FIRST_NUMBERS = 20;
    private static final long MAX_NUMBER = Long.MAX_VALUE;

    private static final Checker HELP = new TextChecker(
            "supported requests",
            "The program should display an instruction for the user"
    );
    private static final Checker ENTER_NUMBER = new TextChecker(
            "natural number",
            "The program should ask for a natural number."
    );
    private static final Checker NOT_NATURAL = new RegexChecker(
            "(this|the) number is( not|n't) natural",
            "Number {0} is not natural. Expected error message."
    );
    private static final Checker PROPERTIES = new TextChecker(
            "properties of ",
            "The first line of number''s properties should contains \"{1}\"."
    );
    private static final Checker LINES_IN_CARD = new LinesChecker(7 + 1);

    private final UserProgram program = new UserProgram();
    private final long[] notNaturalNumbers = {-1, -2, -3, -4, -5};

    @DynamicTest(data = "notNaturalNumbers", order = 10)
    CheckResult notNaturalNumbersTest(final long number) {
        return program
                .start()
                .check(HELP)
                .check(ENTER_NUMBER)
                .execute(number)
                .check(NOT_NATURAL)
                .check(HELP)
                .check(ENTER_NUMBER)
                .execute(0)
                .finished()
                .result();
    }

    @DynamicTest(order = 20)
    CheckResult finishByZeroTest() {
        return program
                .start()
                .check(HELP)
                .check(ENTER_NUMBER)
                .execute(-5)
                .check(NOT_NATURAL)
                .check(HELP)
                .execute(-7635)
                .check(NOT_NATURAL)
                .check(HELP)
                .check(ENTER_NUMBER)
                .execute(0)
                .finished()
                .result();
    }

    @DynamicTest(order = 30)
    CheckResult oneNumberTest() {
        final var numbers = LongStream.concat(
                LongStream.range(1, TEST_FIRST_NUMBERS),
                random.longs(RANDOM_NUMBERS_TESTS, 1, MAX_NUMBER));

        program.start();
        numbers.forEach(number -> program
                .check(ENTER_NUMBER)
                .execute(number)
                .check(LINES_IN_CARD)
                .check(PROPERTIES)
                .check(new CardChecker(number))
        );
        return program.execute(0).finished().result();
    }

    @DynamicTest(order = 40)
    CheckResult twoNumbersTest() {
        return program
                .start()
                .check(ENTER_NUMBER)
                .execute("1 " + TEST_FIRST_NUMBERS)
                .check(new LinesChecker(TEST_FIRST_NUMBERS + 1))
                .check(new ListChecker(1, TEST_FIRST_NUMBERS))
                .execute(0)
                .finished()
                .result();
    }
}
