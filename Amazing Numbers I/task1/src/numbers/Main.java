package numbers;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter a natural number:");
        final var number = scanner.nextLong();

        if (number > 0) {
            final var parity = number % 2 == 0 ? "Even" : "Odd";
            System.out.println("This number is " + parity + ".");
        } else {
            System.out.println("This number is not natural!");
        }
    }
}

