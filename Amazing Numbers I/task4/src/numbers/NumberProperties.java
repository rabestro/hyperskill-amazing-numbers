package numbers;

import java.util.function.LongPredicate;

public enum NumberProperties implements LongPredicate {
    EVEN(number -> number % 2 == 0),
    ODD(number -> number % 2 != 0),
    BUZZ(number -> number % 7 == 0 || number % 10 == 7),
    DUCK(number -> String.valueOf(number).indexOf('0') != -1),
    PALINDROMIC(number -> {
        final var digits = String.valueOf(number);
        return new StringBuilder(digits).reverse().toString().equals(digits);
    });

    private final LongPredicate hasProperty;

    NumberProperties(LongPredicate calculateProperty) {
        this.hasProperty = calculateProperty;
    }

    @Override
    public boolean test(long number) {
        return hasProperty.test(number);
    }

}
