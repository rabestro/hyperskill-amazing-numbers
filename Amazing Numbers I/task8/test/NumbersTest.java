import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import util.*;

import java.util.Random;
import java.util.stream.LongStream;

public class NumbersTest extends StageTest {
    private static final Random random = new Random();
    private static final long RANDOM_NUMBERS_TESTS = 20;
    private static final long TEST_FIRST_NUMBERS = 10;
    private static final long MAX_NUMBER = Long.MAX_VALUE;

    private static final Checker HELP = new TextChecker(
            "supported requests",
            "The program should display an instruction for the user"
    );
    private static final Checker REQUEST = new TextChecker(
            "enter a request",
            "The program should ask for a request."
    );
    private static final Checker NOT_NATURAL = new RegexChecker(
            "(this|the) number is( not|n't) natural",
            "Number {0} is not natural. Expected error message."
    );
    private static final Checker PROPERTIES = new TextChecker(
            "properties of ",
            "The first line of number''s properties should contains \"{1}\"."
    );
    private static final Checker LINES_IN_CARD = new LinesChecker(NumberProperties.values().length + 2);

    private final UserProgram program = new UserProgram();
    private final long[] notNaturalNumbers = {-1, -2, -3, -4, -5};
    private final Object[][] searchOneProperty = new Object[][]{
            {1L, TEST_FIRST_NUMBERS, "even"},
            {1L, TEST_FIRST_NUMBERS, "odd"},
            {1L, TEST_FIRST_NUMBERS, "buzz"},
            {1L, TEST_FIRST_NUMBERS, "spy"},
            {999_999L, 5L, "spy"},
            {999_999_999L, 9L, "palindromic"},
            {999_999_999L, 9L, "gapful"},
            {999_999_999L, 9L, "duck"}
    };
    private final Object[][] searchTwoProperties = new Object[][]{
            {1L, TEST_FIRST_NUMBERS, "even spy"},
            {1L, TEST_FIRST_NUMBERS, "odd buzz"},
            {1L, TEST_FIRST_NUMBERS, "buzz gapful"},
            {1L, TEST_FIRST_NUMBERS, "spy buzz"},
            {999_999L, 5L, "spy even"},
            {999_999_999L, 9L, "palindromic odd"},
            {999_999_999L, 9L, "gapful duck"},
            {999_999_999L, 9L, "duck odd"}
    };
    private final Object[][] searchProperties = new Object[][]{
            {1L, 10L, "even spy buzz"},
            {1L, 10L, "odd buzz duck"},
            {1L, 10L, "buzz gapful even"},
            {999_999L, 5L, "spy even gapful"},
    };
    @DynamicTest(data = "notNaturalNumbers", order = 10)
    CheckResult notNaturalNumbersTest(final long number) {
        return program
                .start()
                .check(HELP)
                .check(REQUEST)
                .execute(number)
                .check(NOT_NATURAL)
                .check(HELP)
                .check(REQUEST)
                .execute(0)
                .finished()
                .result();
    }

    @DynamicTest(order = 20)
    CheckResult finishByZeroTest() {
        return program
                .start()
                .check(HELP)
                .check(REQUEST)
                .execute(-5)
                .check(NOT_NATURAL)
                .check(HELP)
                .execute(-7635)
                .check(NOT_NATURAL)
                .check(HELP)
                .check(REQUEST)
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
                .check(REQUEST)
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
                .check(REQUEST)
                .execute("1 " + TEST_FIRST_NUMBERS)
                .check(new LinesChecker(TEST_FIRST_NUMBERS + 1))
                .check(new ListChecker(1, TEST_FIRST_NUMBERS))
                .execute(0)
                .finished()
                .result();
    }

    @DynamicTest(data = "searchOneProperty", order = 50)
    CheckResult twoNumbersAndPropertyTest(Long start, Long count, String property) {
        return program
                .start()
                .check(REQUEST)
                .execute(start + " " + count + " " + property)
                .check(new LinesChecker(count + 1))
                .check(new ListChecker(start, count, new String[]{property}))
                .execute(0)
                .finished()
                .result();
    }

    @DynamicTest(data = "searchTwoProperties", order = 60)
    CheckResult twoNumbersAndTwoPropertyTest(Long start, Long count, String properties) {
        return program
                .start()
                .check(REQUEST)
                .execute(start + " " + count + " " + properties)
                .check(new LinesChecker(count + 1))
                .check(new ListChecker(start, count, properties))
                .check(REQUEST)
                .execute(0)
                .finished()
                .result();
    }

    @DynamicTest(data = "searchProperties", order = 70)
    CheckResult twoNumbersAndPropertiesTest(Long start, Long count, String properties) {
        return program
                .start()
                .check(REQUEST)
                .execute(start + " " + count + " " + properties)
                .check(new LinesChecker(count + 1))
                .check(new ListChecker(start, count, properties))
                .check(REQUEST)
                .execute(0)
                .finished()
                .result();
    }
}
