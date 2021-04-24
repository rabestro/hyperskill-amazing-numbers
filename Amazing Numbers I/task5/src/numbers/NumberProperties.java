package numbers;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.function.LongPredicate;
import java.util.stream.Stream;

import static java.lang.Character.getNumericValue;
import static java.util.stream.Collectors.joining;

public enum NumberProperties implements LongPredicate {
    EVEN(x -> x % 2 == 0),
    ODD(x -> x % 2 != 0),
    BUZZ(x -> x % 7 == 0 || x % 10 == 7),
    DUCK(x -> String.valueOf(x).indexOf('0') != -1),
    PALINDROMIC(number -> {
        final var digits = String.valueOf(number);
        return new StringBuilder(digits).reverse().toString().equals(digits);
    }),
    GAPFUL(x -> x > 100 && x % (getNumericValue(String.valueOf(x).charAt(0)) * 10L + x % 10) == 0);

    public static String FORMAT = "%12s: %b%n";
    public static long number = 0;

    private final LongPredicate hasProperty;

    NumberProperties(LongPredicate hasProperty) {
        this.hasProperty = hasProperty;
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
        final var sj = new StringJoiner("", String.format("Properties of %,d%n", x), "");
        for (var property : NumberProperties.values()) {
            sj.add(String.format("%12s: %b%n", property.name().toLowerCase(), property.test(x)));
        }
        return sj.toString();
    }

    public static Stream<NumberProperties> stream() {
        return Arrays.stream(NumberProperties.values());
    }

    @Override
    public boolean test(long number) {
        return hasProperty.test(number);
    }

    @Override
    public String toString() {
        return String.format(FORMAT, name().toLowerCase(), hasProperty.test(number));
    }

    public boolean hasProperty() {
        return hasProperty.test(number);
    }

}
