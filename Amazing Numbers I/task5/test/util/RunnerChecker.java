package util;

public class RunnerChecker extends AbstractChecker {

    public RunnerChecker(String feedback) {
        super(feedback);
        validator = userProgram -> !userProgram.getTestedProgram().isFinished();
    }

}
