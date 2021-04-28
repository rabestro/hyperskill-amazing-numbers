package util;

public class TextChecker extends AbstractChecker {
    private final String expected;

    public TextChecker(String expected) {
        this(expected, "Expected that the output contains \"{2}\".");
    }

    public TextChecker(String expected, String feedback) {
        super(feedback);
        this.expected = expected.toLowerCase();
    }

    @Override
    public boolean test(UserProgram program) {
        parameters = new Object[]{program.getInput(), program.getOutput(), expected};
        return program.getOutput().toLowerCase().contains(expected);
    }
}
