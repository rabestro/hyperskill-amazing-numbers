package numbers;

import java.util.Arrays;
import java.util.function.LongPredicate;
import java.util.stream.Stream;

import static java.lang.Character.getNumericValue;
import static java.util.stream.Collectors.joining;

public enum NumberProperties implements LongPredicate {
    EVEN(x -> x % 2 == 0),
    ODD(x -> x % 2 != 0),
    BUZZ(x -> x % 7 == 0 || x % 10 == 7),
    DUCK(x -> String.valueOf(x).indexOf('0') != -1),
    GAPFUL(x -> x > 100 && x % (getNumericValue(String.valueOf(x).charAt(0)) * 10L + x % 10) == 0),
    HARSHAD(x -> x % digitsSum(x) == 0),
    SPY(x -> digitsSum(x) == digitsProduct(x)),
    ARMSTRONG(x -> {
        final var number = String.valueOf(x);
        final var power = number.length();
        final var sum = number.chars()
                .map(Character::getNumericValue)
                .mapToLong(digit -> pow(digit, power))
                .sum();
        return x == sum;
    });

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

    @Override
    public boolean test(long number) {
        return calculateProperty.test(number);
    }

    public static long digitsSum(long x) {
        long sum = 0;
        for (long i = x; i > 0; i /= 10) {
            sum += i % 10;
        }
        return sum;
    }

    public static long digitsProduct(long x) {
        long product = 1;
        for (long i = x; i > 0; i /= 10) {
            product *= i % 10;
        }
        return product;
    }

    public static long pow(long n, long p) {
        long result = 1;
        for (long i = p; i > 0; --i) {
            result *= n;
        }
        return result;
    }
}
