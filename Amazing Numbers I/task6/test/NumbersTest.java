import numbers.NumberProperties;
import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import util.*;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

public final class NumbersTest extends StageTest {
    private static final Random random = new Random();
    private static final int NEGATIVE_NUMBERS_TESTS = 5;
    private static final int FIRST_NUMBERS = 15;
    private static final int RANDOM_TESTS = 10;
    private static final int MAX_COUNT = 20;
    private static final int MIN_START = 2;

    private static final Checker WELCOME = new TextChecker("Welcome to Amazing Numbers!");

    private static final Function<UserProgram, UserProgram> HELP =
            new TextChecker("Supported requests")
                    .andThen(new RegexChecker(
                            "(one|a) natural number .* properties",
                            "Display the instruction on how to use the program"))
                    .andThen(new TextChecker(
                            "two natural numbers",
                            "In this stage the user may enter two numbers to print a list. "
                                    + "The program should explain this in the help."))
                    .andThen(new TextChecker(
                            "property to search for",
                            "In this stage the user may enter two numbers and property to search for. "
                                    + "The program should explain this in the help."))
                    .andThen(new TextChecker(
                            "two properties to search for",
                            "In this stage the user may enter two numbers and two property to search for. "
                                    + "The program should explain this in the help."))
                    .andThen(new RegexChecker(
                            "0 for( the)? exit",
                            "Display the instruction on how to exit"));

    private static final Checker ASK_REQUEST = new RegexChecker(
            "enter( a)? request",
            "The program should ask the user to enter a request."
    );
    private static final Checker ERROR_FIRST = new RegexChecker(
            "number is( not|n't) (natural|positive)",
            "Number {0} is not natural. The program should print an error message."
    );
    private static final Checker ERROR_SECOND = new RegexChecker(
            "number is( not|n't) natural|count( of numbers in the list)? should be( a)? (natural|positive) number.",
            "Number {0} is not natural. The program should print an error message."
    );
    private static final Checker ERROR_PROPERTY = new RegexChecker(
            "(The )?property .+ (not found|is (wrong|incorrect))",
            "The request: \"{0}\" has incorrect property. "
                    + "If incorrect property specified print the error message and list of available properties."
    );
    private static final Checker HELP_PROPERTIES = new TextChecker(
            "Available properties"
    );
    private static final Checker LIST_PROPERTIES = new Checker(
            program -> Arrays.stream(NumberProperties.values())
                    .map(Enum::name)
                    .map("(?i)\\b"::concat)
                    .map(Pattern::compile)
                    .map(p -> p.matcher(program.getOutput()))
                    .allMatch(Matcher::find),
            "If incorrect property specified then show the list of available properties."
    );
    private static final Checker PROPERTIES_OF = new RegexChecker(
            "properties of \\d",
            "The first line of number''s properties should contains \"Properties of {0}\"."
    );
    private static final Checker RUNNING = new Checker(Predicate.not(UserProgram::isFinished),
            "The program should continue to work till the user enter \"0\"."
    );
    private static final Checker FINISHED = new Checker(UserProgram::isFinished,
            "The program should finish after the user enter \"0\"."
    );

    private final UserProgram program = new UserProgram();
    private final String[] wrongRequests = new String[]{
            "1 10 gay", "40 2 bay", "37 4 8", "67 2 +y-", "2 54 Prime Number", "6 8 ...", "5 9 ,"
    };
    private final String[] wrongSecondProperty = new String[]{
            "1 10 odd girl", "40 2 even day", "37 4 spy 89", "67 2 DUCK +"
    };

    // Stage #3

    @DynamicTest(order = 5)
    CheckResult welcomeTest() {
        return program
                .start()
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
        long negativeNumber = -random.nextInt(Byte.MAX_VALUE) - 1L;
        return program
                .start()
                .check(WELCOME)
                .check(HELP)
                .check(ASK_REQUEST)
                .execute(negativeNumber)
                .check(ERROR_FIRST)
                .check(HELP)
                .check(RUNNING)
                .check(ASK_REQUEST)
                .execute(0)
                .check(FINISHED)
                .result();
    }

    @DynamicTest(repeat = RANDOM_TESTS, order = 10)
    CheckResult notNaturalSecondNumberTest() {
        int first = 1 + random.nextInt(Short.MAX_VALUE);
        int negativeSecond = -random.nextInt(Short.MAX_VALUE);
        return program
                .start()
                .check(WELCOME)
                .check(HELP)
                .check(ASK_REQUEST)
                .execute(first + " " + negativeSecond)
                .check(ERROR_SECOND)
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

    // Stage #4

    @DynamicTest(order = 40)
    CheckResult firstNumbersListTest() {
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
                .mapToObj(start -> new Long[]{start, (long) 1 + random.nextInt(MAX_COUNT)})
                .toArray(Long[][]::new);
    }

    @DynamicTest(data = "getRandomTwo", order = 44)
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

    // Stage #5

    @DynamicTest(data = "wrongRequests", order = 50)
    CheckResult wrongPropertyRequestTest(String wrongPropertyRequest) {
        return program
                .start()
                .check(WELCOME)
                .check(HELP)
                .check(ASK_REQUEST)
                .execute(wrongPropertyRequest)
                .check(ERROR_PROPERTY)
                .check(HELP_PROPERTIES)
                .check(LIST_PROPERTIES)
                .check(RUNNING)
                .check(ASK_REQUEST)
                .execute(0)
                .check(FINISHED)
                .result();
    }

    private String[] getProperties() {
        return Arrays.stream(NumberProperties.values()).map(Enum::name).toArray(String[]::new);
    }

    @DynamicTest(data = "getProperties", order = 53)
    CheckResult allPropertiesSearchTest(String property) {
        final var start = 1L + random.nextInt(Short.MAX_VALUE);
        final var count = 1L + random.nextInt(MAX_COUNT);
        return program
                .start()
                .check(WELCOME)
                .check(HELP)
                .check(ASK_REQUEST)
                .execute(start + " " + count + " " + property)
                .check(new LinesChecker(count + 1))
                .check(new ListChecker(start, count, property))
                .check(RUNNING)
                .execute(0)
                .check(FINISHED)
                .result();
    }

    @DynamicTest(repeat = RANDOM_TESTS, order = 55)
    CheckResult twoRandomNumbersAndPropertyTest() {
        final var start = 1L + random.nextInt(Short.MAX_VALUE);
        final var count = 1L + random.nextInt(MAX_COUNT);
        final var index = random.nextInt(NumberProperties.values().length);
        final var property = NumberProperties.values()[index].name();
        final var request = start + " " + count + " " + property;

        return program
                .start()
                .check(WELCOME)
                .check(HELP)
                .check(ASK_REQUEST)
                .execute(request)
                .check(new LinesChecker(count + 1))
                .check(new ListChecker(start, count, property))
                .check(RUNNING)
                .check(ASK_REQUEST)
                .execute(0)
                .check(FINISHED)
                .result();
    }

    // Stage #6

    @DynamicTest(data = "wrongSecondProperty", order = 60)
    CheckResult wrongSecondPropertyRequestTest(String wrongSecondProperty) {
        return program
                .start()
                .check(WELCOME)
                .check(HELP)
                .check(ASK_REQUEST)
                .execute(wrongSecondProperty)
                .check(ERROR_PROPERTY)
                .check(HELP_PROPERTIES)
                .check(LIST_PROPERTIES)
                .check(RUNNING)
                .check(ASK_REQUEST)
                .execute(0)
                .check(FINISHED)
                .result();
    }

}
