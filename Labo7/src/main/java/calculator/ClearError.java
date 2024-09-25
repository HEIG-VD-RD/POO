package calculator;

/**
 * Clear error operator class. Sets the display to 0 and clears the error.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
class ClearError extends Operator {
    /**
     * Constructor.
     */
    ClearError(State state) {
        super(state);
    }

    /**
     * Execute the operation.
     */
    @Override
    void execute() {
        state.setCurrentValue(0);
        state.setInputIntegral(true);
        state.setInputEntered(false);
        state.setError(false);
    }
}
