package numbers;

import java.util.Scanner;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.printf("%nEnter a natural number: ");
            final var data = scanner.nextLine().split(" ");
            System.out.println();
            final var start = Long.parseLong(data[0]);
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
            final var count = Long.parseLong(data[1]);
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
                property = property.and(NumberProperties.valueOf(data[i].toUpperCase()));
            }
            LongStream.iterate(start, n -> n + 1)
                    .filter(property)
                    .limit(count)
                    .forEach(Main::printProperties);
        }
    }

    private static void printProperties(long number) {
        System.out.printf("%,16d is %s%n", number, NumberProperties.shortProperties(number));
    }
}