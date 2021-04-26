package numbers;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter a natural number:");
        final var number = scanner.nextLong();

        if (number > 0) {
            checkParity(number);
            checkBuzz(number);
        } else {
            System.out.println("This number is not natural!");
        }
    }

    private static void checkParity(long number) {
        final var parity = number % 2 == 0 ? "Even" : "Odd";
        System.out.println("This number is " + parity + ".");
    }

    private static void checkBuzz(long number) {
        final var isDivisible = number % 7 == 0;
        final var isEndsWith7 = number % 10 == 7;
        final var isBuzz = isDivisible || isEndsWith7;
        final String explanation = isBuzz ? isDivisible ? isEndsWith7
                ? "divisible by 7 and it ends with 7."
                : "divisible by 7."
                : "ends with 7."
                : "neither divisible by 7 nor it ends with 7.";

        System.out
                .printf("It %s a Buzz number.%n", isBuzz ? "is" : "is not")
                .printf("Explanation:%n%d is %s%n", number, explanation);

    }
}