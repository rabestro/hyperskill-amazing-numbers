package util;

import org.hyperskill.hstest.exception.outcomes.WrongAnswer;

import java.text.MessageFormat;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public abstract class AbstractChecker implements UnaryOperator<UserProgram>, Predicate<UserProgram> {
    protected final String feedback;

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
        throw getFeedback(program.getInput(), program.getOutput());
    }

    public WrongAnswer getFeedback(Object... args) {
        return new WrongAnswer(MessageFormat.format(feedback, args));
    }
}
