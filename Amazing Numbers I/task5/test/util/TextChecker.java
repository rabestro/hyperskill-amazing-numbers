package util;

public class TextChecker extends AbstractChecker {
    private final String expected;

    public TextChecker(String contains, String feedback) {
        super(feedback);
        this.expected = contains;
    }

    @Override
    public boolean test(UserProgram program) {
        return program.getOutput().toLowerCase().contains(expected);
    }
}
