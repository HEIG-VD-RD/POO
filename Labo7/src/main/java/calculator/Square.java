package calculator;

/**
 * Exponential of two operator class.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
final class Square extends UnaryOperator {
    /**
     * Constructor.
     */
    Square(State state) {
        super(state);
    }

    /**
     * Execute the operation.
     *
     * @param value The current value.
     * @return The result of the operation.
     */
    double execute(double value) {
        return Math.pow(value, 2);
    }
}
