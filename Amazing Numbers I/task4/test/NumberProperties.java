import java.util.Optional;
import java.util.function.LongPredicate;
import java.util.regex.Pattern;

public enum NumberProperties implements LongPredicate {
    EVEN(x -> x % 2 == 0),
    ODD(x -> x % 2 != 0),
    BUZZ(x -> x % 7 == 0 || x % 10 == 7),
    DUCK(x -> String.valueOf(x).indexOf('0') != -1),
    PALINDROMIC(number -> {
        final var digits = String.valueOf(number);
        return new StringBuilder(digits).reverse().toString().equals(digits);
    }),
    GAPFUL(number -> {
        if (number < 100) {
            return false;
        }
        long last = number % 10;
        long first = number / 100;
        while (first > 10) {
            first /= 10;
        }
        long divider = first * 10 + last;
        return number % divider == 0;
    });

    private final LongPredicate hasProperty;
    private final Pattern pattern = Pattern.compile(
            name() + "\\s*[:-]\\s*(?<value>true|false)",
            Pattern.CASE_INSENSITIVE
    );

    NumberProperties(LongPredicate hasProperty) {
        this.hasProperty = hasProperty;
    }

    @Override
    public boolean test(long number) {
        return hasProperty.test(number);
    }

    public Optional<Boolean> extractValue(String output) {
        final var matcher = pattern.matcher(output);
        matcher.find();
        return Optional
                .ofNullable(matcher.group("value"))
                .map(Boolean::valueOf);
    }
}
