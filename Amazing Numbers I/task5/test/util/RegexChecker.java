package util;

import java.util.regex.Pattern;

public class RegexChecker extends AbstractChecker {
    private final Pattern expected;

    public RegexChecker(final UserProgram.Arguments args) {
        super(args.feedback);
        expected = Pattern.compile(args.regexp, args.flags);
    }

    public RegexChecker(final String regexp, final String feedback) {
        this(regexp, Pattern.CASE_INSENSITIVE, feedback);
    }

    public RegexChecker(final String regexp, final int flags, final String feedback) {
        super(feedback);
        this.expected = Pattern.compile(regexp, flags);
    }

    @Override
    public boolean test(UserProgram actual) {
        return expected.matcher(actual.getOutput()).find();
    }

}
