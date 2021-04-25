package util;

import org.hyperskill.hstest.exception.outcomes.WrongAnswer;

import java.text.MessageFormat;

import static java.util.Objects.isNull;

public abstract class AbstractChecker implements Checker {
    protected String feedback;
    protected Object[] parameters;

    protected AbstractChecker(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public abstract boolean test(UserProgram program);

    @Override
    public UserProgram apply(UserProgram program) {
        if (this.test(program)) {
            return program;
        }
        throw isNull(parameters)
                ? getFeedback(program.getInput(), program.getOutput())
                : getFeedback(parameters);
    }

    public WrongAnswer getFeedback(Object... args) {
        return new WrongAnswer(MessageFormat.format(feedback, args));
    }
}
