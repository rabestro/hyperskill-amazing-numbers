package util;

public class RunnerChecker extends AbstractChecker {
    public RunnerChecker() {
        this("Program should be running.");
    }

    public RunnerChecker(String feedback) {
        super(feedback);
        validator = userProgram -> !userProgram.getTestedProgram().isFinished();
    }

}
