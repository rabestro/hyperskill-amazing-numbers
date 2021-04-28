import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import util.*;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.LongStream;

public final class NumbersTest extends StageTest {
    private static final Random random = new Random();
    private static final int NEGATIVE_NUMBERS_TESTS = 5;
    private static final long FIRST_NUMBERS = 15;
    private static final int RANDOM_TESTS = 10;
    private static final int MAX_COUNT = 20;
    private static final int MIN_START = 2;


    private static final Checker WELCOME = new TextChecker("Welcome to Amazing Numbers!");

    private static final Function<UserProgram, UserProgram> HELP =
            new TextChecker("Supported requests")
                    .andThen(new RegexChecker(
                            "(one|a) natural number .* properties",
                            "Display the instruction on how to use the program")
                    )
                    .andThen(new RegexChecker(
                            "two natural numbers",
                            "In this stage the user may enter two numbers. " +
                                    "The program should explain this in help."
                    ))
                    .andThen(new RegexChecker(
                            "0 for( the)? exit",
                            "Display the instruction on how to exit")
                    );
    private static final Checker ASK_REQUEST = new RegexChecker(
            "enter( a)? request",
            "The program should ask the user to enter a request."
    );
    private static final Checker ERROR_MESSAGE = new RegexChecker(
            "number is( not|n't) natural",
            "Number {0} is not natural. The program should print an error message."
    );
    private static final Checker PROPERTIES_OF = new RegexChecker(
            "properties of \\d",
            "The first line of number''s properties should contains \"Properties of {0}\"."
    );

    private static final Checker RUNNING = new RunnerChecker(
            "The program should continue to work till the user enter \"0\"."
    );

    private static final Checker FINISHED = new FinishChecker(
            "The program should finish after the user enter \"0\"."
    );

    private final UserProgram program = new UserProgram();

    @DynamicTest(order = 5)
    CheckResult welcomeTest() {
        return program.start()
                .check(WELCOME)
                .check(HELP)
                .check(RUNNING)
                .check(ASK_REQUEST)
                .execute(0)
                .check(FINISHED)
                .result();
    }

    @DynamicTest(repeat = NEGATIVE_NUMBERS_TESTS, order = 10)
    CheckResult notNaturalNumbersTest() {
        return program
                .start()
                .check(WELCOME)
                .check(HELP)
                .check(ASK_REQUEST)
                .execute(-random.nextInt() - 1L)
                .check(ERROR_MESSAGE)
                .check(HELP)
                .check(RUNNING)
                .check(ASK_REQUEST)
                .execute(0)
                .check(FINISHED)
                .result();
    }

    @DynamicTest(repeat = RANDOM_TESTS, order = 10)
    CheckResult notNaturalSecondNumberTest() {
        int first = 1 + random.nextInt();
        int second = -random.nextInt();
        return program
                .start()
                .check(WELCOME)
                .check(HELP)
                .check(ASK_REQUEST)
                .execute(first + " " + second)
                .check(ERROR_MESSAGE)
                .check(HELP)
                .check(RUNNING)
                .check(ASK_REQUEST)
                .execute(0)
                .check(FINISHED)
                .result();
    }

    @DynamicTest(order = 20)
    CheckResult naturalNumbersTest() {
        final var numbers = LongStream.concat(
                LongStream.range(1, FIRST_NUMBERS),
                random.longs(RANDOM_TESTS, 1, Long.MAX_VALUE)
        );

        program.start().check(WELCOME).check(HELP);

        numbers.forEach(number -> program
                .check(ASK_REQUEST)
                .execute(number)
                .check(PROPERTIES_OF)
                .check(new PropertiesChecker(number))
                .check(RUNNING));

        return program
                .check(RUNNING)
                .check(ASK_REQUEST)
                .execute(0)
                .check(FINISHED)
                .result();
    }

    @DynamicTest(order = 40)
    CheckResult twoNumbersTest() {
        return program
                .start()
                .check(WELCOME)
                .check(HELP)
                .check(ASK_REQUEST)
                .execute("1 " + FIRST_NUMBERS)
                .check(new LinesChecker(FIRST_NUMBERS + 1))
                .check(new ListChecker(1, FIRST_NUMBERS))
                .execute(0)
                .check(FINISHED)
                .result();
    }

    private Object[][] getRandomTwo() {
        return random
                .longs(RANDOM_TESTS, MIN_START, Long.MAX_VALUE - MAX_COUNT)
                .mapToObj(start -> new Long[]{
                        start,
                        (long) 1 + random.nextInt(MAX_COUNT)})
                .toArray(Long[][]::new);
    }

    @DynamicTest(data = "getRandomTwo", order = 50)
    CheckResult twoRandomNumbersTest(long start, long count) {
        return program
                .start()
                .check(WELCOME)
                .check(HELP)
                .check(ASK_REQUEST)
                .execute(start + " " + count)
                .check(new LinesChecker(count + 1))
                .check(new ListChecker(start, count))
                .check(RUNNING)
                .execute(0)
                .check(FINISHED)
                .result();
    }

}
