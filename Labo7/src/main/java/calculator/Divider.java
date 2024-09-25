package calculator;

/**
 * Divider operator class.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
final class Divider extends BinaryOperator {
    /**
     * Constructor.
     *
     * @param state The state of the calculator.
     */
    Divider(State state) {
        super(state);
    }

    /**
     * Execute the operation.
     *
     * @param value1 The current value.
     * @param value2 The stack value.
     * @return The result of value2 divided by value1.
     */
    @Override
    double execute(double value1, double value2) {
        if (value1 == 0) {
            state.setError(true);
            return 0;
        }

        if (value2 == 0) {
            return 0;
        }

        return value2 / value1;
    }
}
