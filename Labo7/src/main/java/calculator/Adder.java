package calculator;

/**
 * Adder operator class.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
final class Adder extends BinaryOperator {
    /**
     * Constructor.
     *
     * @param state The state of the calculator.
     */
    Adder(State state) {
        super(state);
    }

    /**
     * Execute the operation.
     *
     * @param value1 The current value.
     * @param value2 The stack value.
     * @return The result of value1 plus value2.
     */
    @Override
    double execute(double value1, double value2) {
        return value1 + value2;
    }
}
