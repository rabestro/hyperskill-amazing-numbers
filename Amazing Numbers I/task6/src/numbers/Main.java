package numbers;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.LongPredicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Main {
    private static final Pattern SEPARATOR = Pattern.compile("[, ]+");

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        printHelp();

        while (true) {
            System.out.printf("%nEnter a request: ");
            final var data = SEPARATOR.split(scanner.nextLine().toUpperCase(), 3);
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
                System.out.print(NumberProperty.fullProperties(start));
                continue;
            }
            final var count = getNaturalNumber(data[1]);
            if (count < 1) {
                System.out.println("The count of numbers in the list should be a natural number.");
                printHelp();
                continue;
            }
            if (data.length == 2) {
                printList(start, count, Collections.emptySet());
                continue;
            }

            final var names = SEPARATOR
                    .splitAsStream(data[2])
                    .collect(Collectors.toUnmodifiableSet());

            if (!NumberProperty.NAMES.containsAll(names)) {
                final var unknown = new HashSet<>(names);
                unknown.removeAll(NumberProperty.NAMES);
                System.out.println(MessageFormat.format(
                        "The {1,choice, 1#property|1<properties} {0} {1,choice, 1#is|1<are} unknown.",
                        unknown, unknown.size())
                );
                System.out.println("Available properties: " + NumberProperty.NAMES);
                continue;
            }

            final var properties = names.stream()
                    .map(NumberProperty::valueOf)
                    .collect(Collectors.toUnmodifiableSet());

            NumberProperty.MUTUALLY_EXCLUSIVE
                    .stream()
                    .filter(properties::containsAll)
                    .findAny()
                    .ifPresentOrElse(
                            Main::mutuallyExclusiveError,
                            () -> printList(start, count, properties)
                    );
        }
        System.out.println("Goodbye!");
    }

    private static void printList(long start, long count, Set<NumberProperty> properties) {
        final var condition = properties.stream()
                .map(property -> (LongPredicate) property)
                .reduce(number -> true, LongPredicate::and);

        LongStream.iterate(start, n -> n + 1)
                .filter(condition)
                .limit(count)
                .mapToObj(NumberProperty::shortProperties)
                .forEach(System.out::println);
    }

    private static void mutuallyExclusiveError(Set<NumberProperty> exclusiveProperties) {
        System.out.println("The request contains mutually exclusive properties: " + exclusiveProperties);
        System.out.println("There are no numbers with all these properties at once.");
    }

    private static long getNaturalNumber(final String input) {
        for (final char symbol : input.toCharArray()) {
            if (!Character.isDigit(symbol)) {
                return -1;
            }
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
        System.out.println("- 0 for the exit. ");
    }
}