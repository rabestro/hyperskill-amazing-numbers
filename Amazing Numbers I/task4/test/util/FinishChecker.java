package util;

public class FinishChecker extends AbstractChecker {

    public FinishChecker(String feedback) {
        super(feedback);
        validator = program -> program.getTestedProgram().isFinished();
    }
}
