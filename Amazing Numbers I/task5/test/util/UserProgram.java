package util;

import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public final class UserProgram {
    public static Checker FINISH_CHECKER = new FinishChecker("Program should finish.");

    private static final Map<Object, Checker> map = new HashMap<>();

    private TestedProgram program;
    private CheckResult result = CheckResult.correct();

    private String input;
    private String output;

    public UserProgram start(String... args) {
        program = new TestedProgram();
        output = program.start(args);
        return this;
    }

    public UserProgram check(final Checker checker) {
        return checker.apply(this);
    }

    public UserProgram check(Object key) {
        return map.get(key).apply(this);
    }

    public CheckResult result() {
        return result;
    }

    public UserProgram execute(long number) {
        return execute(String.valueOf(number));
    }

    public UserProgram execute(String userInput) {
        this.input = userInput;
        output = program.execute(userInput);
        return this;
    }

    public UserProgram contains(String expected, String error) {
        return new TextChecker(expected, error).apply(this);
    }

    public UserProgram finished() {
        return FINISH_CHECKER.apply(this);
    }

    public String getOutput() {
        return output;
    }

    public String getInput() {
        return input;
    }

    public TestedProgram getTestedProgram() {
        return program;
    }

    public UserProgram add(Consumer<Arguments> builderFunction) {
        final var args = new Arguments();
        builderFunction.accept(args);
        map.put(args.key, new RegexChecker(args));
        return this;
    }

    public UserProgram add(Object key, AbstractChecker checker) {
        map.put(key, checker);
        return this;
    }

    public final static class Arguments {
        public Object key;
        public String regexp;
        public String feedback;
        public int flags = Pattern.CASE_INSENSITIVE;
    }

}