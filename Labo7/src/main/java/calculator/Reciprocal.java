package calculator;

/**
 * Reciprocal operator class.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
final class Reciprocal extends UnaryOperator {
    /**
     * Constructor.
     */
    Reciprocal(State state) {
        super(state);
    }

    /**
     * Execute the operation.
     *
     * @param value The current value.
     * @return The result of the operation.
     */
    double execute(double value) {
        if (value == 0) {
            state.setError(true);
            return 0;
        }

        return 1 / value;
    }
}
