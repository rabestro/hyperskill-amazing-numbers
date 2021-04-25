package util;

import java.util.Optional;
import java.util.function.LongPredicate;
import java.util.regex.Pattern;

public enum NumberProperties implements LongPredicate {
    EVEN(number -> number % 2 == 0),
    ODD(number -> number % 2 != 0),
    BUZZ(number -> number % 7 == 0 || number % 10 == 7),
    DUCK(number -> String.valueOf(number).indexOf('0') != -1);

    private final LongPredicate hasProperty;
    private final Pattern pattern = Pattern.compile(
            name().toLowerCase() + "\\s*[:-]\\s*(?<value>true|false)",
            Pattern.CASE_INSENSITIVE);

    NumberProperties(LongPredicate hasProperty) {
        this.hasProperty = hasProperty;
    }

    @Override
    public boolean test(long number) {
        return hasProperty.test(number);
    }

    public Optional<String> extractValue(String output) {
        final var matcher = pattern.matcher(output);
        return matcher.find() ? Optional.of(matcher.group("value")) : Optional.empty();
    }
}
