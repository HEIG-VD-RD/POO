package calculator;

/**
 * Dot operator class.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
final class Dot extends Operator {
    /**
     * Constructor.
     */
    Dot(State state) {
        super(state);
    }

    /**
     * Execute the operation.
     */
    @Override
    void execute() {
        state.setInputIntegral(false);
    }
}
