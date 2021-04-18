package numbers;

import java.util.Arrays;
import java.util.function.LongFunction;
import java.util.stream.Stream;

public enum NumberProperties {
    PARITY(number -> number % 2 == 0 ? "even" : "odd"),
    BUZZ(number -> number % 7 == 0 || number % 10 == 7),
    DUCK(number -> String.valueOf(number).indexOf('0') != -1);

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
