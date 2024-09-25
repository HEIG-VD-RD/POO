package calculator;

/**
 * Enter operator class.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
final class Enter extends Operator {
    /**
     * Constructor.
     */
    Enter(State state) {
        super(state);
    }

    /**
     * Execute the operation.
     */
    @Override
    void execute() {
        state.stack().push(state.getCurrentValue());
        state.setCurrentValue(0);
        state.setInputIntegral(true);
    }
}
