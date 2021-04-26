package numbers;

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
            LongPredicate property = number -> true;
            for (int i = 2; i < data.length; ++i) {
                final var notFound = NumberProperties.stream()
                        .map(Enum::name)
                        .noneMatch((data[i]::equals));
                if (notFound) {
                    System.out.printf("The property '%s' is incorrect", data[i]);
                    continue main;
                }
                property = property.and(NumberProperties.valueOf(data[i]));
            }
            LongStream.iterate(start, n -> n + 1)
                    .filter(property)
                    .limit(count)
                    .forEach(Main::printProperties);
        }
        System.out.println("Goodbye!");
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

    private static void printHelp() {
        System.out.println();
        System.out.println("Supported requests:");
        System.out.println("- one natural number to print its properties;");
        System.out.println("- two natural numbers separated by space:");
        System.out.println("  - a starting number for the list;");
        System.out.println("  - a count of numbers in the list;");
        System.out.println("- two natural numbers and properties to search for");
        System.out.println("- 0 for the exit. ");
    }
}