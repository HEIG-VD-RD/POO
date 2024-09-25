package calculator;

/**
 * Value operator abstract class. Represents an operation that acts on the current value.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
abstract class UnaryOperator extends Operator {
    /**
     * Constructor.
     */
    UnaryOperator(State state) {
        super(state);
    }

    /**
     * Execute the operation on the current value.
     */
    @Override
    void execute() {
        var value = execute(state.getCurrentValue());
        state.setInputEntered(true);
        state.setCurrentValue(value);
    }

    /**
     * Execute the operation.
     *
     * @param value The current value.
     * @return The result of the operation.
     */
    abstract double execute(double value);
}
