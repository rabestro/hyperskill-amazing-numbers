package util;

import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public final class UserProgram {
    private final Map<Object, RegexChecker> map = new HashMap<>();
    private TestedProgram program;
    private String output;
    private Object input = "";

    public UserProgram start(String... args) {
        program = new TestedProgram();
        output = program.start(args);
        return this;
    }

    public UserProgram check(Object key) {
        final var checker = map.get(key);
        if (!checker.test(this)) {
            throw checker.getFeedback(input, output);
        }
        return this;
    }

    public CheckResult correct() {
        return CheckResult.correct();
    }

    public UserProgram execute(Object userInput) {
        this.input = userInput;
        output = program.execute(userInput.toString());
        return this;
    }

    public UserProgram contains(String expected, String error) {
        if (output.toLowerCase().contains(expected)) {
            return this;
        }
        throw new WrongAnswer(MessageFormat.format(error, input, expected));
    }

    public UserProgram finished() {
        if (program.isFinished()) {
            return this;
        }
        throw new WrongAnswer("Program should finish.");
    }

    public String getOutput() {
        return output;
    }

    public UserProgram add(Consumer<Arguments> builderFunction) {
        final var args = new Arguments();
        builderFunction.accept(args);
        map.put(args.key, new RegexChecker(args));
        return this;
    }

    public static class Arguments {
        public Object key;
        public String regexp;
        public String feedback;
        public int flags = Pattern.CASE_INSENSITIVE;
    }



}

