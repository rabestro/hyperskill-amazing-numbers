package util;

public class RunnerChecker extends AbstractChecker {
    public RunnerChecker() {
        this("Program should be running.");
    }

    public RunnerChecker(String feedback) {
        super(feedback);
    }

    @Override
    public boolean test(UserProgram userProgram) {
        return !userProgram.getTestedProgram().isFinished();
    }
}
