package calculator;

import util.Stack;

/**
 * Calculator state class.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
class State {
    /**
     * The stack of values.
     */
    private final Stack<Double> valueStack;

    /**
     * The stack of calculations history.
     */
    private Double memory;

    /**
     * The current value.
     */
    private Double currentValue;

    /**
     * The error state.
     */
    private boolean error;

    /**
     * Whether the current input is integral or not.
     */
    protected boolean inputIntegral = true;

    /**
     * Whether the user just entered a value or not.
     */
    protected boolean inputEntered = false;

    /**
     * Constructor.
     */
    State() {
        this.valueStack = new Stack<>();
        this.memory = null;
        this.currentValue = 0.;
        this.error = false;
    }

    /**
     * Get the value stack.
     *
     * @return The value stack.
     */
    Stack<Double> stack() {
        return valueStack;
    }

    /**
     * Get the memory value.
     *
     * @return The memory value.
     */
    Double getMemory() {
        return memory;
    }

    /**
     * Set the memory value.
     *
     * @param memory The memory value to set.
     */
    void setMemory(Double memory) {
        this.memory = memory;
    }

    /**
     * Get the current value.
     *
     * @return The current value.
     */
    Double getCurrentValue() {
        return currentValue;
    }

    /**
     * Set the current value.
     *
     * @param currentValue The value to set.
     */
    void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * Get the error state.
     *
     * @return The error state.
     */
    boolean getError() {
        return error;
    }

    /**
     * Set the error state.
     *
     * @param error The error state to set.
     */
    void setError(boolean error) {
        this.error = error;
    }

    /**
     * If the input is operating on the whole or fractional part.
     *
     * @return True if the input is integral, false otherwise.
     */
    boolean isInputIntegral() {
        return inputIntegral;
    }

    /**
     * Set the input operating on the whole or fractional part.
     *
     * @param value The new value.
     */
    void setInputIntegral(boolean value) {
        inputIntegral = value;
    }

    /**
     * If the last input triggered a push on the stack or not.
     *
     * @return True if the operation triggered a push, false otherwise.
     */
    boolean isInputEntered() {
        return inputEntered;
    }

    /**
     * Set if the last input should be entered.
     *
     * @param value The new value.
     */
    void setInputEntered(boolean value) {
        inputEntered = value;
    }
}