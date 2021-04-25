package util;

public class FinishChecker extends AbstractChecker {

    protected FinishChecker(String feedback) {
        super(feedback);
    }

    @Override
    public boolean test(UserProgram program) {
        return program.getTestedProgram().isFinished();
    }
}
