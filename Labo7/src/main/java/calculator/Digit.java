package calculator;

/**
 * Digit operator class. Uses a string to parse the value.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
final class Digit extends Operator {
    /**
     * The value of the digit
     */
    private final String digitValue;

    /**
     * Constructor.
     */
    Digit(State state, int digit) {
        super(state);
        this.digitValue = String.valueOf(digit);
    }

    /**
     * Add a digit to the current value. If the input is integral, it is prepended to the current value. If the input is
     * fractional, the digit is appended to the current value.
     */
    void execute() {
        // Case where a unary/binary operation was done last and we want to start a new number
        if (state.isInputEntered()) {
            state.stack().push(state.getCurrentValue());
            state.setCurrentValue(0);
            state.setInputIntegral(true);
            state.setInputEntered(false);
        }

        String value = String.valueOf(state.getCurrentValue());
        if (state.isInputIntegral()) { // Case where the input is integral
            value = value.substring(0, value.indexOf('.'));
        } else if (value.endsWith("0")) { // Case where the input is fractional and has a trailing zero
            value = value.substring(0, value.length() - 1);
        }

        value += digitValue;

        state.setCurrentValue(Double.parseDouble(value));
    }
}
