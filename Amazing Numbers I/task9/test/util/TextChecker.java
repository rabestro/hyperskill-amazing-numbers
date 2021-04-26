package util;

public class TextChecker extends AbstractChecker {
    private final String expected;

    public TextChecker(String contains) {
        this(contains, "The program output doesn''t contains \"{0}\".");
    }

    public TextChecker(String contains, String feedback) {
        super(feedback);
        parameters = new Object[]{contains};
        this.expected = contains.toLowerCase();
    }

    @Override
    public boolean test(UserProgram program) {
        return program.getOutput().toLowerCase().contains(expected);
    }
}
