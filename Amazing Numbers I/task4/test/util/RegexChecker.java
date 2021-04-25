package util;

import org.hyperskill.hstest.exception.outcomes.WrongAnswer;

import java.text.MessageFormat;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class RegexChecker implements Predicate<UserProgram> {
    final Pattern expected;
    final String feedback;

    RegexChecker(final UserProgram.Arguments args) {
        expected = Pattern.compile(args.regexp, args.flags);
        feedback = args.feedback;
    }

    @Override
    public boolean test(UserProgram actual) {
        return expected.matcher(actual.getOutput()).find();
    }

    WrongAnswer getFeedback(Object... args) {
        return new WrongAnswer(MessageFormat.format(feedback, args));
    }
}
