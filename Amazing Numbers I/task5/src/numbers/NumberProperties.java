package numbers;

import java.util.Arrays;
import java.util.function.LongPredicate;
import java.util.stream.Stream;

import static java.lang.Character.getNumericValue;
import static java.util.stream.Collectors.joining;

public enum NumberProperties {
    EVEN(x -> x % 2 == 0),
    ODD(x -> x % 2 != 0),
    BUZZ(x -> x % 7 == 0 || x % 10 == 7),
    DUCK(x -> String.valueOf(x).indexOf('0') != -1),
    GAPFUL(x -> x > 100 && x % (getNumericValue(String.valueOf(x).charAt(0)) * 10L + x % 10) == 0),
    HARSHAD(x -> x % String.valueOf(x).chars().map(Character::getNumericValue).sum() == 0);

    public static String FORMAT = "%12s: %b%n";
    public static long number = 0;

    private final LongPredicate calculateProperty;

    NumberProperties(LongPredicate calculateProperty) {
        this.calculateProperty = calculateProperty;
    }

    public static String shortProperties(long x) {
        number = x;
        return stream()
                .filter(NumberProperties::hasProperty)
                .map(NumberProperties::name)
                .map(String::toLowerCase)
                .collect(joining(", "));
    }

    public static String fullProperties(long x) {
        number = x;
        final var prefix = String.format("Properties of %,d%n", number);
        return stream()
                .map(NumberProperties::toString)
                .collect(joining("", prefix, ""));
    }

    public static Stream<NumberProperties> stream() {
        return Arrays.stream(NumberProperties.values());
    }

    @Override
    public String toString() {
        return String.format(FORMAT, name().toLowerCase(), calculateProperty.test(number));
    }

    public boolean hasProperty() {
        return calculateProperty.test(number);
    }

}
