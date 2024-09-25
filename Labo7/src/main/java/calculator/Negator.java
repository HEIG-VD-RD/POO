package calculator;

/**
 * Negator operator class.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
final class Negator extends Operator {
    /**
     * Constructor.
     */
    Negator(State state) {
        super(state);
    }

    /**
     * Execute the operation.
     */
    @Override
    void execute() {
        var value = state.getCurrentValue();
        if (value == 0) {
            return;
        }
        state.setCurrentValue(-value);
    }
}
