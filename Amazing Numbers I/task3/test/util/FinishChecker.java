package util;

public class FinishChecker extends AbstractChecker {

    public FinishChecker() {
        this("Program should finish at this moment.");
    }

    public FinishChecker(String feedback) {
        super(feedback);
        validator = program -> program.getTestedProgram().isFinished();
    }
}
