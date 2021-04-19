package numbers;

import java.util.Scanner;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        main:
        while (true) {
            System.out.printf("%nEnter a natural number: ");
            final var data = scanner.nextLine().toUpperCase().split(" ");
            System.out.println();
            final var start = getNaturalNumber(data[0]);
            if (start == 0) {
                break;
            }
            if (start < 0) {
                System.out.println("This number is not natural!");
                continue;
            }
            if (data.length == 1) {
                System.out.print(NumberProperties.fullProperties(start));
                continue;
            }
            final var count = getNaturalNumber(data[1]);
            if (count < 1) {
                System.out.println("The count should be greater then zero.");
                continue;
            }
            if (data.length == 2) {
                LongStream.range(start, start + count).forEach(Main::printProperties);
                continue;
            }
            LongPredicate condition = number -> true;

            for (int i = 2; i < data.length; ++i) {
                final boolean isNegative = data[i].startsWith("-");
                final var name = isNegative ? data[i].substring(1) : data[i];
                final var notFound = NumberProperties.stream().map(Enum::name).noneMatch(name::equals);

                if (notFound) {
                    System.out.printf("The property '%s' is incorrect", name);
                    continue main;
                }
                final var property = NumberProperties.valueOf(name);
                condition = condition.and(isNegative ? property.negate() : property);
            }

            LongStream.iterate(start, n -> n > 0, n -> n + 1)
                    .filter(condition)
                    .limit(count)
                    .forEach(Main::printProperties);
        }
    }

    private static void printProperties(long number) {
        System.out.printf("%,16d is %s%n", number, NumberProperties.shortProperties(number));
    }

    private static long getNaturalNumber(final String input) {
        if (input.isBlank() || !input.chars().allMatch(Character::isDigit)) {
            return -1;
        }
        return Long.parseLong(input);
    }
}