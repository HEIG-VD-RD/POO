package calculator;

/**
 * Subtractor operator class.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
final class Subtractor extends BinaryOperator {
    /**
     * Constructor.
     */
    Subtractor(State state) {
        super(state);
    }

    /**
     * Execute the operation.
     *
     * @param value1 The current value.
     * @param value2 The stack value.
     * @return The result of value2 minus value1.
     */
    @Override
    double execute(double value1, double value2) {
        return value2 - value1;
    }
}
