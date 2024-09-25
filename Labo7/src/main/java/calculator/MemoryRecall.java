package calculator;

/**
 * Memory recall operator class.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
final class MemoryRecall extends Operator {
    /**
     * Constructor.
     */
    MemoryRecall(State state) {
        super(state);
    }

    /**
     * Execute the operation.
     */
    @Override
    void execute() {
        var value = state.getMemory();
        if (value == null) {
            state.setError(true);
        } else {
            state.setCurrentValue(value);
        }
    }
}
