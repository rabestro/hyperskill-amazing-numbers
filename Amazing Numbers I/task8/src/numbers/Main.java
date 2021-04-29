package numbers;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        printHelp();
        main:
        while (true) {
            System.out.printf("%nEnter a request: ");
            final var data = scanner.nextLine().toUpperCase().split(" ");
            System.out.println();
            if (data[0].isBlank()) {
                printHelp();
                continue;
            }
            final var start = getNaturalNumber(data[0]);
            if (start == 0) {
                break;
            }
            if (start < 0) {
                System.out.println("This number is not natural!");
                printHelp();
                continue;
            }
            if (data.length == 1) {
                System.out.print(NumberProperties.fullProperties(start));
                continue;
            }
            final var count = getNaturalNumber(data[1]);
            if (count < 1) {
                System.out.println("The count should be positive number.");
                printHelp();
                continue;
            }
            if (data.length == 2) {
                LongStream.range(start, start + count)
                        .mapToObj(NumberProperties::shortProperties)
                        .forEach(System.out::println);
                continue;
            }
            LongPredicate condition = number -> true;

            for (int i = 2; i < data.length; ++i) {
                final boolean isNegative = data[i].startsWith("-");
                final var name = isNegative ? data[i].substring(1) : data[i];
                final var notFound = NumberProperties.stream().map(Enum::name).noneMatch(name::equals);

                if (notFound) {
                    System.out.printf("The property '%s' is incorrect.%n", name);
                    System.out.println("Available properties: " + Arrays.toString(NumberProperties.values()));
                    continue main;
                }
                final var property = NumberProperties.valueOf(name);
                condition = condition.and(isNegative ? property.negate() : property);
            }

            LongStream.iterate(start, n -> n + 1)
                    .filter(condition)
                    .limit(count)
                    .mapToObj(NumberProperties::shortProperties)
                    .forEach(System.out::println);
        }
        System.out.println("Goodbye!");
    }

    private static long getNaturalNumber(final String input) {
        if (input.isBlank() || !input.chars().allMatch(Character::isDigit)) {
            return -1;
        }
        return Long.parseLong(input);
    }


    private static void printHelp() {
        System.out.println();
        System.out.println("Supported requests:");
        System.out.println("- one natural number to print its properties;");
        System.out.println("- two natural numbers separated by space:");
        System.out.println("  - a starting number for the list;");
        System.out.println("  - a count of numbers in the list;");
        System.out.println("- two natural numbers and property to search for;");
        System.out.println("- two natural numbers and two properties to search for;");
        System.out.println("- If a property name is preceded by a minus, that ");
        System.out.println("  property must not be present in the number.");
        System.out.println("- 0 for the exit. ");
    }
}