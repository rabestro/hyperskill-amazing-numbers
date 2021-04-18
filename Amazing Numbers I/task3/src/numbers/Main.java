package numbers;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter a natural number:");
        NumberProperties.number = scanner.nextLong();

        if (NumberProperties.isNatural()) {
            System.out.printf("Properties of %,d%n", NumberProperties.number);
            NumberProperties.stream().forEach(System.out::print);
        } else {
            System.out.println("This number is not natural!");
        }
    }

}