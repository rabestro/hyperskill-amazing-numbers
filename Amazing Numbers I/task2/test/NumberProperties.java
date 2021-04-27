import java.util.Optional;
import java.util.function.LongPredicate;
import java.util.regex.Pattern;

public enum NumberProperties implements LongPredicate {
    EVEN(x -> x % 2 == 0),
    ODD(x -> x % 2 != 0),
    BUZZ(x -> x % 7 == 0 || x % 10 == 7),
    DUCK(x -> String.valueOf(x).indexOf('0') != -1);

    private final LongPredicate hasProperty;
    private final Pattern pattern = Pattern.compile(
            name() + "\\s*[:-]\\s*(?<value>true|false)",
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
