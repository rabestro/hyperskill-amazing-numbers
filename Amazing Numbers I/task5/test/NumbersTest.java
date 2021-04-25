import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import util.*;

import java.text.MessageFormat;
import java.util.Random;
import java.util.stream.LongStream;

public class NumbersTest extends StageTest {
    private static final Random random = new Random();
    private static final long RANDOM_NUMBERS_TESTS = 20;
    private static final long TEST_FIRST_NUMBERS = 20;
    private static final long MAX_NUMBER = Long.MAX_VALUE;

    private final UserProgram program = new UserProgram()
            .add(Key.HELP, new TextChecker("supported requests",
                    "The program should display an instruction for the user"))
            .add(Key.ENTER_NUMBER, new TextChecker("natural number",
                    "The program should ask for a natural number."))
            .add(Key.NOT_NATURAL, new RegexChecker(
                    "(this|the) number is( not|n't) natural",
                    "Number {0} is not natural. Expected error message."))
            .add(Key.PROPERTIES, new TextChecker("properties of ",
                    "The first line of number''s properties should contains \"{1}\"."));

    private final long[] notNaturalNumbers = {-1, -2, -3, -4, -5};

    @DynamicTest(data = "notNaturalNumbers", order = 10)
    CheckResult notNaturalNumbersTest(final long number) {
        return program
                .start()
                .check(Key.HELP)
                .check(Key.ENTER_NUMBER)
                .execute(number)
                .check(Key.NOT_NATURAL)
                .check(Key.HELP)
                .check(Key.ENTER_NUMBER)
                .execute(0)
                .finished()
                .result();
    }

    @DynamicTest(order = 20)
    CheckResult finishByZeroTest() {
        return program
                .start()
                .check(Key.HELP)
                .check(Key.ENTER_NUMBER)
                .execute(-5)
                .check(Key.NOT_NATURAL)
                .check(Key.HELP)
                .execute(-7635)
                .check(Key.NOT_NATURAL)
                .check(Key.HELP)
                .check(Key.ENTER_NUMBER)
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
        numbers.forEach(number -> {
            program.check(Key.ENTER_NUMBER).execute(number).check(Key.PROPERTIES);

            for (var property : NumberProperties.values()) {
                final var name = property.name().toLowerCase();
                program.contains(name, "The property {1} wasn''t found for number {0}.");

                final var expected = property.test(number);
                final var actual = Boolean.parseBoolean(property.extractValue(program.getOutput())
                        .orElseThrow(() -> new WrongAnswer(
                                "The value for property " + name + " was not found.")));

                if (expected != actual) {
                    throw new WrongAnswer(MessageFormat.format(
                            "For property {0} the expected value is {1} but found {2}.",
                            name, expected, actual));
                }
            }
        });
        return program.execute(0).finished().result();
    }

    @DynamicTest(order = 40)
    CheckResult twoNumbersTest() {
        return program
                .start()
                .check(Key.ENTER_NUMBER)
                .execute("1 " + TEST_FIRST_NUMBERS)
                .check(new LinesChecker(TEST_FIRST_NUMBERS + 1))
                .execute(0)
                .finished()
                .result();
    }

    private static final Checker CHECK_LIST = new AbstractChecker("The list is incorrect") {
        @Override
        public boolean test(UserProgram program) {
            return false;
        }
    };

    private enum Key {HELP, ENTER_NUMBER, NOT_NATURAL, PROPERTIES}
}
