package calculator;

/**
 * Memory store operator class.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
final class MemoryStore extends Operator {
    /**
     * Constructor.
     */
    MemoryStore(State state) {
        super(state);
    }

    /**
     * Execute the operation.
     */
    @Override
    void execute() {
        state.setMemory(state.getCurrentValue());
    }
}
