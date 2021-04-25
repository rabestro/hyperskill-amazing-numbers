import util.AbstractChecker;
import util.UserProgram;

public class CardChecker extends AbstractChecker {
    private final long number;

    public CardChecker(long number) {
        super("Incorrect card for number {0}");
        parameters = new Object[]{number};
        this.number = number;
    }

    @Override
    public boolean test(UserProgram program) {
        for (var property : NumberProperties.values()) {
            final var name = property.name().toLowerCase();

            program.contains(name, "No property with name \"{0}\"");

            final var expected = property.test(number);
            final var actualValue = property.extractValue(program.getOutput());

            if (actualValue.isEmpty()) {
                feedback = "The value for property {0} was not found. Expected: {1}";
                parameters = new Object[]{name, expected};
                return false;
            }
            final var actual = Boolean.parseBoolean(actualValue.get());

            if (expected != actual) {
                feedback = "For property {0} the expected value is {1} but found {2}.";
                parameters = new Object[]{name, expected, actual};
                return false;
            }
        }
        return true;
    }
}
