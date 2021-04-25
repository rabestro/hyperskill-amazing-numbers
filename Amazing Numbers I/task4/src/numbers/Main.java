package numbers;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        printHelp();
        while (true) {
            System.out.println();
            System.out.println("Enter a request:");
            final var number = scanner.nextLong();
            if (number == 0) {
                break;
            }
            if (number < 0) {
                System.out.println("This number is not natural!");
                printHelp();
                continue;
            }
            printProperties(number);
        }
    }

    private static void printProperties(long number) {
        System.out.printf("Properties of %,d%n", number);
        for (var property : NumberProperties.values()) {
            final var name = property.name().toLowerCase();
            System.out.printf("%12s: %s%n", name, property.test(number));
        }
    }

    private static void printHelp() {
        System.out.println();
        System.out.println("Supported requests:");
        System.out.println("- natural number to print its properties;");
        System.out.println("- 0 for the exit.");
    }
}