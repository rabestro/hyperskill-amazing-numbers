package numbers;

import java.util.Arrays;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.LongPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Character.getNumericValue;

public enum NumberProperty implements LongPredicate {
    EVEN(x -> x % 2 == 0),
    ODD(x -> x % 2 != 0),
    BUZZ(x -> x % 7 == 0 || x % 10 == 7),
    DUCK(x -> String.valueOf(x).indexOf('0') != -1),
    PALINDROMIC(number -> {
        final var digits = String.valueOf(number);
        return new StringBuilder(digits).reverse().toString().equals(digits);
    }),
    GAPFUL(x -> x > 100 && x % (getNumericValue(String.valueOf(x).charAt(0)) * 10L + x % 10) == 0),
    SPY(x -> digitsSum(x) == digitsProduct(x));

    public static final Set<Set<NumberProperty>> MUTUALLY_EXCLUSIVE =
            Set.of(Set.of(EVEN, ODD), Set.of(DUCK, SPY));

    public static final Set<String> NAMES = Arrays.stream(values())
            .map(Enum::name).collect(Collectors.toUnmodifiableSet());

    private final LongPredicate hasProperty;

    NumberProperty(LongPredicate hasProperty) {
        this.hasProperty = hasProperty;
    }

    public static String shortProperties(long x) {
        final var sj = new StringJoiner(", ", String.format("%,16d is ", x), "");
        for (var property : NumberProperty.values()) {
            if (property.test(x)) {
                sj.add(property.name().toLowerCase());
            }
        }
        return sj.toString();
    }

    public static String fullProperties(long x) {
        final var sj = new StringJoiner("", String.format("Properties of %,d%n", x), "");
        for (var property : NumberProperty.values()) {
            sj.add(String.format("%12s: %b%n", property.name().toLowerCase(), property.test(x)));
        }
        return sj.toString();
    }

    public static Stream<NumberProperty> stream() {
        return Arrays.stream(NumberProperty.values());
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

    @Override
    public boolean test(long number) {
        return hasProperty.test(number);
    }


}