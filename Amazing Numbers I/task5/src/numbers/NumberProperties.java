package numbers;

import java.util.StringJoiner;
import java.util.function.LongPredicate;

import static java.lang.Character.getNumericValue;

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

    private final LongPredicate hasProperty;

    NumberProperties(LongPredicate hasProperty) {
        this.hasProperty = hasProperty;
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


    @Override
    public boolean test(long number) {
        return hasProperty.test(number);
    }

}
