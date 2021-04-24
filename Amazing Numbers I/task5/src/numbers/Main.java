package numbers;

import java.util.Scanner;
import java.util.stream.LongStream;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        printHelp();

        while (true) {
            System.out.printf("%nEnter a natural number: ");
            final var data = scanner.nextLine().split(" ");
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
                System.out.println("The count should be natural number.");
                printHelp();
                continue;
            }
            LongStream.range(start, start + count).forEach(Main::printProperties);
        }
    }

    private static long getNaturalNumber(final String input) {
        if (input.isBlank()) {
            return -1;
        }
        for (final char symbol: input.toCharArray()) {
            if (!Character.isDigit(symbol)) {
                return -1;
            }
        }
        return Long.parseLong(input);
    }

    private static void printProperties(long number) {
        System.out.printf("%,16d is %s%n", number, NumberProperties.shortProperties(number));
    }


    private static void printHelp() {
        System.out.println("Supported requests:");
        System.out.println("- one natural number to print its properties;");
        System.out.println("- two natural numbers separated by space:");
        System.out.println("  - a starting number for the list;");
        System.out.println("  - a count of numbers in the list;");
        System.out.println("- 0 for exit. ");
    }
}