package numbers;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter a natural number:");
        final var number = scanner.nextLong();

        if (number > 0) {
            System.out.printf("Properties of %,d%n", number);
            for (var property : NumberProperties.values()) {
                final var name = property.name().toLowerCase();
                final var hasProperty = property.test(number);
                System.out.printf("%12s: %s%n", name, hasProperty);
            }
        } else {
            System.out.println("This number is not natural!");
        }
    }

}