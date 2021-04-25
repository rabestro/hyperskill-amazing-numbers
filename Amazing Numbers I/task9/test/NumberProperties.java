import java.util.Optional;
import java.util.function.LongPredicate;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

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
    ARMSTRONG(x -> {
        final var number = String.valueOf(x);
        final var power = number.length();
        final var sum = number.chars()
                .map(Character::getNumericValue)
                .mapToLong(digit -> pow(digit, power))
                .sum();
        return x == sum;
    }),
    DISARIUM(x -> {
        final var number = String.valueOf(x);
        return LongStream.range(0, number.length())
                .map(i -> pow(Character.getNumericValue(number.charAt((int) i)), i + 1))
                .sum() == x;
    });

    private final LongPredicate hasProperty;
    private final Pattern pattern = Pattern.compile(
            name() + "\\s*[:-]\\s*(?<value>true|false)",
            Pattern.CASE_INSENSITIVE);

    NumberProperties(LongPredicate hasProperty) {
        this.hasProperty = hasProperty;
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

    public Optional<String> extractValue(String output) {
        final var matcher = pattern.matcher(output);
        return matcher.find() ? Optional.of(matcher.group("value")) : Optional.empty();
    }

    @Override
    public boolean test(long number) {
        return hasProperty.test(number);
    }
}
