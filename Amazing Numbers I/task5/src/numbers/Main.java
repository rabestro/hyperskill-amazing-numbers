package numbers;

import java.util.Scanner;
import java.util.stream.LongStream;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.printf("%nEnter a natural number: ");
            final var data = scanner.nextLine().split(" ");
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
                System.out.println("The count should be natural number.");
                continue;
            }
            LongStream.range(start, start + count).forEach(Main::printProperties);
        }
    }

    private static long getNaturalNumber(final String input) {
//        if (input.isBlank() || !input.chars().allMatch(Character::isDigit)) {
//            return -1;
//        }

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
}