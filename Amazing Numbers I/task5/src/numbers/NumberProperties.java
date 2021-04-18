package numbers;

import java.util.Arrays;
import java.util.function.LongFunction;
import java.util.stream.Stream;

import static java.lang.Character.getNumericValue;

public enum NumberProperties {
    PARITY(x -> x % 2 == 0 ? "even" : "odd"),
    BUZZ(x -> x % 7 == 0 || x % 10 == 7),
    DUCK(x -> String.valueOf(x).indexOf('0') != -1),
    GAPFUL(x -> x > 100 && x % (getNumericValue(String.valueOf(x).charAt(0)) * 10L + x % 10) == 0),
    HARSHAD(x -> x % String.valueOf(x).chars().map(Character::getNumericValue).sum() == 0);

    public static String FORMAT = "%12s: %s%n";
    public static long number = 0;

    private final LongFunction<Object> calculateProperty;

    NumberProperties(LongFunction<Object> calculateProperty) {
        this.calculateProperty = calculateProperty;
    }

    public static boolean isNatural() {
        return number > 0;
    }

    public static Stream<NumberProperties> stream() {
        return Arrays.stream(NumberProperties.values());
    }

    @Override
    public String toString() {
        return String.format(FORMAT, name().toLowerCase(), calculateProperty.apply(number));
    }
}
