package calculator;


/**
 * Operator abstract class.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
abstract class Operator {
    /**
     * The state of the calculator.
     */
    final State state;

    /**
     * Constructor.
     *
     * @param state The state of the calculator.
     */
    Operator(State state) {
        this.state = state;
    }

    /**
     * Execute the operation.
     */
    abstract void execute();
}
