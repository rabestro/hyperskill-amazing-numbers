import java.util.Optional;
import java.util.function.LongPredicate;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

import static java.lang.Character.getNumericValue;

public enum NumberProperty implements LongPredicate {
    BUZZ(x -> x % 7 == 0 || x % 10 == 7),
    DUCK(number -> digits(number).anyMatch(digit -> digit == 0)),
    PALINDROMIC(number -> {
        final var digits = String.valueOf(number);
        return new StringBuilder(digits).reverse().toString().equals(digits);
    }),
    GAPFUL(number -> number > 100 &&
            number % (getNumericValue(String.valueOf(number).charAt(0)) * 10L + number % 10) == 0),
    SPY(x -> digits(x).sum() == digits(x).reduce(1L, (a, b) -> a * b)),
    SQUARE(number -> pow((long) Math.sqrt(number), 2) == number),
    SUNNY(number -> NumberProperty.SQUARE.test(number + 1)),
    EVEN(x -> x % 2 == 0),
    ODD(x -> x % 2 != 0);

    private final LongPredicate hasProperty;
    private final Pattern pattern = Pattern.compile(
            name() + "\\s*[:-]\\s*(?<value>true|false)",
            Pattern.CASE_INSENSITIVE
    );

    NumberProperty(LongPredicate hasProperty) {
        this.hasProperty = hasProperty;
    }

    private static LongStream digits(long number) {
        return Long.toString(number).chars().mapToLong(Character::getNumericValue);
    }

    public static long pow(long n, long p) {
        long result = 1;
        for (long i = p; i > 0; --i) {
            result *= n;
        }
        return result;
    }

    @Override
    public boolean test(long number) {
        return hasProperty.test(number);
    }

    public Optional<Boolean> extractValue(String output) {
        final var matcher = pattern.matcher(output);
        matcher.find();
        return Optional.ofNullable(matcher.group("value")).map(Boolean::valueOf);
    }

}
