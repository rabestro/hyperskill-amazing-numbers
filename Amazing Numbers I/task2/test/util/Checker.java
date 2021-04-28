package util;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public interface Checker extends UnaryOperator<UserProgram>, Predicate<UserProgram> {
    void setFeedback(String feedback);
}
