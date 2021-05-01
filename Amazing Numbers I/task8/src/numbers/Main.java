package numbers;

import java.text.MessageFormat;
import java.util.*;
import java.util.function.LongPredicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Main {
    private static final Pattern PARAMETERS_SEPARATOR = Pattern.compile("[, ]+");
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        printHelp();

        while (true) {
            System.out.printf("%nEnter a request: ");
            final var data = PARAMETERS_SEPARATOR
                    .split(scanner.nextLine().toUpperCase(), 3);
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
                System.out.println("The first parameter should be a natural number or zero.");
                continue;
            }
            if (data.length == 1) {
                System.out.print(NumberProperty.fullProperties(start));
                continue;
            }
            final var count = getNaturalNumber(data[1]);
            if (count < 1) {
                System.out.println("The second parameter should be a natural number.");
                continue;
            }
            if (data.length == 2) {
                printList(start, count, Collections.emptySet());
                continue;
            }

            final var names = PARAMETERS_SEPARATOR
                    .splitAsStream(data[2])
                    .collect(Collectors.toUnmodifiableSet());

            final var parameters = names.stream()
                    .map(name -> name.startsWith("-") ? name.substring(1) : name)
                    .collect(Collectors.toUnmodifiableSet());

            final var isAllParametersCorrect = NumberProperty.NAMES.containsAll(parameters);

            if (!isAllParametersCorrect) {
                final var wrongParameters = new HashSet<>(parameters);
                wrongParameters.removeAll(NumberProperty.NAMES);
                System.out.println(MessageFormat.format(
                        "The {1,choice,1#property|1<properties} {0} {1,choice,1#is|1<are} wrong.",
                        wrongParameters, wrongParameters.size())
                );
                System.out.println("Available properties: " + Arrays.toString(NumberProperty.values()));
                continue;
            }

            NumberProperty.MUTUALLY_EXCLUSIVE
                    .stream()
                    .filter(names::containsAll)
                    .findAny()
                    .ifPresentOrElse(
                            Main::mutuallyExclusiveError,
                            () -> printList(start, count, names)
                    );

        }
        System.out.println("Goodbye!");
    }

    private static long getNaturalNumber(final String input) {
        if (input.isBlank() || !input.chars().allMatch(Character::isDigit)) {
            return -1;
        }
        return Long.parseLong(input);
    }

    private static void printList(long start, long count, Set<String> names) {
        final var query = names
                .stream()
                .map(name -> name.charAt(0) == '-'
                        ? NumberProperty.valueOf(name.substring(1)).negate()
                        : NumberProperty.valueOf(name))
                .reduce(number -> true, LongPredicate::and);

        LongStream.iterate(start, n -> n + 1)
                .filter(query)
                .limit(count)
                .mapToObj(NumberProperty::shortProperties)
                .forEach(System.out::println);
    }

    private static void mutuallyExclusiveError(final Set<String> exclusiveProperties) {
        System.out.println("The request contains mutually exclusive properties: " + exclusiveProperties);
        System.out.println("There are no numbers with all these properties at once.");
    }

    private static void printHelp() {
        System.out.println();
        System.out.println("Supported requests:");
        System.out.println("- one natural number to print the card;");
        System.out.println("- two natural numbers to print the list;:");
        System.out.println("  - a starting number for the list;");
        System.out.println("  - a count of numbers in the list;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("  - if a property name is preceded by a minus, that ");
        System.out.println("    property must not be present in the number.");
        System.out.println("- 0 for the exit. ");
    }
}