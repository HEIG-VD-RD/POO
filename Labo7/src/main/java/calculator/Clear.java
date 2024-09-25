package calculator;

/**
 * Clear operator class. Sets the display to 0, clears the error and resets the value stack.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
final class Clear extends ClearError {
    /**
     * Constructor.
     */
    Clear(State state) {
        super(state);
    }

    /**
     * Execute the operation.
     */
    @Override
    void execute() {
        super.execute();
        while (state.stack().getLength() > 0) {
            state.stack().pop();
        }
    }
}
