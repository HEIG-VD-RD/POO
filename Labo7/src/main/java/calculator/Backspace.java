package calculator;

/**
 * Backspace operator class.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
final class Backspace extends Operator {
    /**
     * Constructor.
     *
     * @param state The state of the calculator.
     */
    Backspace(State state) {
        super(state);
    }

    /**
     * Execute the operation.
     */
    @Override
    void execute() {
        // Early return if there is no digit.
        if (state.getCurrentValue() == 0.0) {
            return;
        }

        String currentValue = String.valueOf(state.getCurrentValue());

        // Number is integral, remove 3 chars, otherwise number is decimal and remove 1 char.
        if (currentValue.endsWith(".0")) {
            currentValue = currentValue.substring(0, currentValue.length() - 3);
        } else {
            currentValue = currentValue.substring(0, currentValue.length() - 1);
        }

        // Handle case where the last digit was erased.
        if (currentValue.isEmpty()) {
            currentValue = "0.0";
        }

        state.setCurrentValue(Double.parseDouble(currentValue));
    }
}
