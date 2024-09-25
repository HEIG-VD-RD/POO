package calculator;

/**
 * Binary operator abstract class.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
abstract class BinaryOperator extends Operator {
    /**
     * Constructor.
     *
     * @param state The state of the calculator.
     */
    BinaryOperator(State state) {
        super(state);
    }

    /**
     * Execute the operation on the current value and the stack value.
     */
    @Override
    void execute() {
        var current = state.getCurrentValue();
        var stack = popValue();
        var result = execute(current, stack);
        if (state.getError()) {
            return;
        }
        state.setInputEntered(true);
        state.setCurrentValue(result);
    }

    /**
     * Execute the operation.
     *
     * @param value1 The current value.
     * @param value2 The value popped from the stack.
     * @return The result of the operation.
     */
     abstract double execute(double value1, double value2);

    /**
     * Get the stack value from the state. The use of a wrapper method allows to handle errors.
     *
     * @return The stack value from the state.
     */
    private double popValue() {
        if (state.stack().getLength() == 0) {
            state.setError(true);
            return 0;
        }
        return state.stack().pop();
    }
}
