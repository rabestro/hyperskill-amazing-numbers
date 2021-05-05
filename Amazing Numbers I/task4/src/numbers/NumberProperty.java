package numbers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.LongPredicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.lang.Character.getNumericValue;

public enum NumberProperty implements LongPredicate {
    BUZZ(number -> number % 7 == 0 || number % 10 == 7),
    DUCK(number -> String.valueOf(number).indexOf('0') != -1),
    PALINDROMIC(number -> {
        final var digits = String.valueOf(number);
        return new StringBuilder(digits).reverse().toString().equals(digits);
    }),
    GAPFUL(number -> number > 100 &&
            number % (getNumericValue(String.valueOf(number).charAt(0)) * 10L + number % 10) == 0),
//    SPY(x -> digitsSum(x) == digitsProduct(x)),
    /*
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
        */
//    SQUARE(number -> pow((long) Math.sqrt(number), 2) == number),
//    SUNNY(number -> NumberProperty.SQUARE.test(number + 1)),
//    JUMPING(n -> {
//        for (long p = n % 10, r = n / 10; r > 0; r /= 10) {
//            long c = r % 10;
//            long d = p - c;
//            if (d != 1 && d != -1) {
//                return false;
//            }
//            p = c;
//        }
//        return true;
//    }),
//    HAPPY(NumberProperty::isHappy),
//    SAD(number -> !isHappy(number)),
    EVEN(number -> number % 2 == 0),
    ODD(number -> number % 2 != 0);

    public static final Set<Set<String>> MUTUALLY_EXCLUSIVE = Stream.concat(
            Arrays.stream(values()).map(Enum::name).map(name -> Set.of(name, "-" + name)),
            Stream.of(
                    Set.of("EVEN", "ODD"), Set.of("DUCK", "SPY"), Set.of("-EVEN", "-ODD"),
                    Set.of("SUNNY", "SQUARE"), Set.of("SAD", "HAPPY"), Set.of("-SAD", "-HAPPY")
            )
    ).collect(Collectors.toUnmodifiableSet());

    public static final Set<String> NAMES = Arrays.stream(values()).map(Enum::name)
            .collect(Collectors.toUnmodifiableSet());
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

    private static boolean isHappy(long number) {
        final var sequence = new HashSet<Long>();
        return LongStream
                .iterate(number, i -> !sequence.contains(i), NumberProperty::happyNext)
                .peek(sequence::add)
                .anyMatch(i -> i == 1);
    }

    private static long happyNext(long number) {
        long result = 0;
        for (long i = number; i > 0; i /= 10) {
            int digit = (int) (i % 10);
            result += digit * digit;
        }
        return result;
    }

    @Override
    public boolean test(long number) {
        return hasProperty.test(number);
    }
}
