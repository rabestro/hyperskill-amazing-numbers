package util;

import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.testcase.CheckResult;

import java.text.MessageFormat;

import static java.util.Objects.isNull;

public abstract class AbstractChecker implements Checker {
    protected String feedback;
    protected Object[] parameters;

    protected AbstractChecker() {
        this("Incorrect output for user input: {0}.");
    }

    protected AbstractChecker(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public UserProgram apply(UserProgram program) {
        if (this.test(program)) {
            return program;
        }
        program.setResult(CheckResult.wrong(MessageFormat.format(feedback,
                isNull(parameters) ? new Object[]{program.getInput(), program.getOutput()} : parameters)));

        throw isNull(parameters)
                ? getFeedback(program.getInput(), program.getOutput())
                : getFeedback(parameters);
    }

    @Override
    public Checker setFeedback(String feedback) {
        this.feedback = feedback;
        return this;
    }
    public WrongAnswer getFeedback(Object... args) {
        return new WrongAnswer(MessageFormat.format(feedback, args));
    }
}
