import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import util.*;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.LongStream;

public final class NumbersTest extends StageTest {
    private static final Random random = new Random();
    private static final long RANDOM_TESTS = 20;
    private static final long NEGATIVE_NUMBERS_TESTS = 5;
    private static final long FIRST_NUMBERS = 15;

    private static final Checker WELCOME = new TextChecker("Welcome to Amazing Numbers!");

    private static final Function<UserProgram, UserProgram> HELP =
            new TextChecker("Supported requests")
                    .andThen(new RegexChecker(
                            "natural number .* properties",
                            "Display the instruction on how to use the program")
                    )
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

    private UserProgram program = new UserProgram();

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

    private long[] getNegativeNumbers() {
        return random.longs(NEGATIVE_NUMBERS_TESTS, Short.MIN_VALUE, -1).toArray();
    }

    @DynamicTest(data = "getNegativeNumbers", order = 10)
    CheckResult notNaturalNumbersTest(final long negativeNumber) {
        return program
                .start()
                .check(WELCOME)
                .check(HELP)
                .check(ASK_REQUEST)
                .execute(negativeNumber)
                .check(ERROR_MESSAGE)
                .check(HELP)
                .check(RUNNING)
                .check(ASK_REQUEST)
                .execute(0)
                .check(FINISHED)
                .result();
    }

    private LongStream getNumbers() {
        return LongStream.concat(
                LongStream.range(1, FIRST_NUMBERS),
                random.longs(RANDOM_TESTS, 1, Long.MAX_VALUE)
        );
    }

    @DynamicTest(order = 20)
    CheckResult naturalNumbersTest() {
        program.start().check(WELCOME).check(HELP);

        getNumbers()
                .forEach(number -> program
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


}
