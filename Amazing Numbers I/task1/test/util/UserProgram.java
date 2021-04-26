package util;

import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

public final class UserProgram {
    public static Checker FINISH_CHECKER = new FinishChecker("Program should finish.");

    private TestedProgram program;
    private CheckResult result = CheckResult.correct();

    private Object input;
    private String output;

    public UserProgram start(String... args) {
        program = new TestedProgram();
        output = program.start(args);
        result = CheckResult.correct();
        return this;
    }

    public void setResult(CheckResult result) {
        this.result = result;
    }

    public UserProgram check(final Checker checker) {
        return checker.apply(this);
    }

    public CheckResult result() {
        return result;
    }

    public UserProgram execute(Object userInput) {
        this.input = userInput;
        output = program.execute(userInput.toString());
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

    public Object getInput() {
        return input;
    }

    public TestedProgram getTestedProgram() {
        return program;
    }

}