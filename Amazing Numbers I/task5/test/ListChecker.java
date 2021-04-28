import util.AbstractChecker;
import util.UserProgram;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ListChecker extends AbstractChecker {
    private static final Pattern LINE_PATTERN = Pattern.compile(
            "(?<number>[\\d,. ]*\\d)\\s*(is|:|-)\\s*(?<properties>.+)",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern NON_DIGIT_SYMBOL = Pattern.compile("\\D");
    private static final Pattern PROPERTIES_SEPARATOR = Pattern.compile("[, ;]+");

    private final long start;
    private final long count;

    public ListChecker(long start, long count) {
        super("The list is incorrect");
        this.validator = this::test;
        this.start = start;
        this.count = count;
    }

    public boolean test(UserProgram program) {
        final var lines = program.getOutput()
                .lines()
                .filter(Predicate.not(String::isBlank))
                .limit(count)
                .collect(Collectors.toUnmodifiableList());

        if (lines.size() != count) {
            return false;
        }
        long currentNumber = start;

        for (var line : lines) {
            final var expectedNumber = currentNumber;
            final var matcher = LINE_PATTERN.matcher(line);

            if (!matcher.matches()) {
                feedback = "Can't parse line: \"{0}\". Expected: {1} is ...";
                parameters = new Object[]{line, expectedNumber};
                return false;
            }

            final var rawNumber = matcher.group("number").strip();
            final var actualNumber = NON_DIGIT_SYMBOL.matcher(rawNumber).replaceAll("");

            if (!String.valueOf(expectedNumber).equals(actualNumber)) {
                feedback = "Expected number is {0} but actual number is {1}.";
                parameters = new Object[]{expectedNumber, rawNumber};
                return false;
            }

            final var actualProperties = PROPERTIES_SEPARATOR
                    .splitAsStream(matcher.group("properties").toLowerCase())
                    .collect(Collectors.toUnmodifiableList());

            final var expectedProperties = Arrays
                    .stream(NumberProperties.values())
                    .filter(property -> property.test(expectedNumber))
                    .map(Enum::name)
                    .map(String::toLowerCase)
                    .collect(Collectors.toUnmodifiableSet());

            if (actualProperties.size() != expectedProperties.size()) {
                feedback = "For number {0} expected number of properties is {1} but actual number of properties is {2}. " +
                        "Expected properties are {3}. Actual properties are {4}";
                parameters = new Object[]{expectedNumber, expectedProperties.size(),
                        actualProperties.size(), expectedProperties, actualProperties};
                return false;
            }

            if (!Set.copyOf(actualProperties).equals(expectedProperties)) {
                feedback = "For number {0} expected properties are {1}. Actual properties are {2}.";
                parameters = new Object[]{expectedNumber, expectedProperties, actualProperties};
            }
            currentNumber++;
        }
        return true;
    }
}
