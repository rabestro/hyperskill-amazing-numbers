package util;

import org.hyperskill.hstest.exception.outcomes.WrongAnswer;

import java.text.MessageFormat;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static java.util.Objects.isNull;

public class Checker implements UnaryOperator<UserProgram> {
    protected Predicate<UserProgram> validator;
    protected String feedback;
    protected Object[] parameters;

    protected Checker() {
        this("Incorrect output for user input: {0}.");
    }

    protected Checker(String feedback) {
        this(feedback, $ -> true);
    }

    public Checker(String feedback, Predicate<UserProgram> validator) {
        this.feedback = feedback;
        this.validator = validator;
    }

    @Override
    public UserProgram apply(UserProgram program) {
        if (validator.test(program)) {
            return program;
        }

//        program.setResult(CheckResult.wrong(
//                MessageFormat.format(feedback, Optional.ofNullable(parameters)
//                        .orElse(new Object[]{program.getInput(), program.getOutput()})))
//        );

        throw isNull(parameters)
                ? getFeedback(program.getInput(), program.getOutput())
                : getFeedback(parameters);
    }

    public WrongAnswer getFeedback(Object... args) {
        return new WrongAnswer(MessageFormat.format(feedback, args));
    }
}
