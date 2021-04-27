package util;

public class FinishChecker extends AbstractChecker {

    protected FinishChecker() {
        this("Program should finish at this moment.");
    }

    protected FinishChecker(String feedback) {
        super(feedback);
    }

    @Override
    public boolean test(UserProgram program) {
        return program.getTestedProgram().isFinished();
    }
}
