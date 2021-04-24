package util;

import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public final class TextChecker {
    private final Map<Object, Checker> map = new HashMap<>();
    private TestedProgram program;
    private String output;
    private String userInput = "";

    public TextChecker start(String... args) {
        program = new TestedProgram();
        output = program.start(args);
        return this;
    }

    public TextChecker check(Object key) {
        final var checker = map.get(key);
        if (checker.test(output)) {
            return this;
        }
        throw new WrongAnswer(checker.getFeedback(userInput, output));
    }

    public TextChecker check(Consumer<Arguments> builderFunction) {
        final var args = new Arguments();
        builderFunction.accept(args);
        final var checker = new Checker(Pattern.compile(args.regexp, args.flags), args.feedback);
        if (Objects.nonNull(args.key)) {
            map.put(args.key, checker);
        }
        if (checker.test(output)) {
            return this;
        }
        throw new WrongAnswer(checker.getFeedback(userInput, output));
    }

    public CheckResult correct() {
        return CheckResult.correct();
    }

    public TextChecker execute(long number) {
        return execute(String.valueOf(number));
    }

    public TextChecker execute(String userInput) {
        this.userInput = userInput;
        output = program.execute(userInput);
        return this;
    }

    public TextChecker contains(String expected, String error) {
        if (output.toLowerCase().contains(expected)) {
            return this;
        }
        throw new WrongAnswer(MessageFormat.format(error, userInput, expected));
    }

    public TextChecker finished() {
        if (program.isFinished()) {
            return this;
        }
        throw new WrongAnswer("Program should finish.");
    }

    public String getOutput() {
        return output;
    }

    public TextChecker add(Consumer<Arguments> builderFunction) {
        final var args = new Arguments();
        builderFunction.accept(args);
        map.put(args.key, new Checker(Pattern.compile(args.regexp, args.flags), args.feedback));
        return this;
    }

    public static class Arguments {
        public Object key;
        public String regexp;
        public String feedback;
        public int flags = Pattern.CASE_INSENSITIVE;
    }

    private static final class Checker implements Predicate<String> {
        final Pattern expected;
        final String feedback;

        Checker(final Pattern expected, final String feedback) {
            this.expected = expected;
            this.feedback = feedback;
        }

        @Override
        public boolean test(String actual) {
            return expected.matcher(actual).find();
        }

        String getFeedback(Object... args) {
            return MessageFormat.format(feedback, args);
        }
    }
}