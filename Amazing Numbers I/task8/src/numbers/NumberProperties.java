package numbers;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.lang.Character.getNumericValue;

public enum NumberProperties implements LongPredicate {
    EVEN(number -> number % 2 == 0),
    ODD(number -> number % 2 != 0),
    BUZZ(number -> number % 7 == 0 || number % 10 == 7),
    DUCK(number -> String.valueOf(number).indexOf('0') != -1),
    PALINDROMIC(number -> {
        final var digits = String.valueOf(number);
        return new StringBuilder(digits).reverse().toString().equals(digits);
    }),
    GAPFUL(number -> number > 100 &&
            number % (getNumericValue(String.valueOf(number).charAt(0)) * 10L + number % 10) == 0),
    SPY(x -> digitsSum(x) == digitsProduct(x)),
    HARSHAD(x -> x % digitsSum(x) == 0),
//    DISARIUM(x -> {
//        final var number = String.valueOf(x);
//        return LongStream.range(0, number.length())
//                .map(i -> pow(Character.getNumericValue(number.charAt((int) i)), i + 1))
//                .sum() == x;
//    }),
    JUMPING(n -> {
        for (long p = n % 10, r = n / 10; r > 0; r /= 10) {
            long c = r % 10;
            long d = p - c;
            if (d != 1 && d != -1) {
                return false;
            }
            p = c;
        }
        return true;
    });

    private final LongPredicate calculateProperty;

    NumberProperties(LongPredicate calculateProperty) {
        this.calculateProperty = calculateProperty;
    }

    public static String shortProperties(long x) {
        final var sj = new StringJoiner(", ", String.format("%,16d is ", x), "");
        for (var property : NumberProperties.values()) {
            if (property.test(x)) {
                sj.add(property.name().toLowerCase());
            }
        }
        return sj.toString();
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
