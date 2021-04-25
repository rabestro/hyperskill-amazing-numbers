package util;

import static java.util.function.Predicate.not;

public class LinesChecker extends AbstractChecker {
    private final long expected;

    public LinesChecker(final long expected) {
        super("Expected {0} non blank lines but actual output contains {1} lines.");
        this.expected = expected;
    }

    @Override
    public boolean test(UserProgram program) {
        final var actual = program.getOutput().lines().filter(not(String::isBlank)).count();
        parameters = new Object[]{expected, actual};
        return actual == expected;
    }

}
