package calculator;

/**
 * Multiplier operator class.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
final class Multiplier extends BinaryOperator {
    /**
     * Constructor.
     */
    Multiplier(State state) {
        super(state);
    }

    /**
     * Execute the operation.
     *
     * @param value1 The current value.
     * @param value2 The stack value.
     * @return The result of value1 multiplied by value2.
     */
    @Override
    double execute(double value1, double value2) {
        return value1 * value2;
    }
}
