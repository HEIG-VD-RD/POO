package calculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Operators unit tests.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
public class OperatorsTest {
    /**
     * The state used throughout the tests.
     */
    private State state;

    /**
     * The operator used throughout the tests.
     */
    private Operator op;

    /**
     * Set up the test stack.
     */
    @BeforeEach
    public void setUp() {
        this.state = new State();
        this.state.stack().push(2.0);
        this.state.setCurrentValue(4.0);
    }

    /**
     * Tear down the test stack.
     */
    @AfterEach
    public void tearDown() {
        this.state = null;
        this.op = null;
    }

    /**
     * Test the addition operation.
     */
    @Test
    public void adderTest() {
        op = new Adder(state);
        op.execute();
        assertEquals(6.0, state.getCurrentValue());
    }

    /**
     * Test the division operation.
     */
    @Test
    public void dividerTest() {
        op = new Divider(state);
        op.execute();
        assertEquals(0.5, state.getCurrentValue());
    }

    /**
     * Test the that division by zero sets the error state.
     */
    @Test
    public void divideByZeroTest() {
        op = new Divider(state);
        state.setCurrentValue(0.0);
        op.execute();
        assertTrue(state.getError());
    }

    /**
     * Test the factorial operation.
     */
    @Test
    public void subtractorTest() {
        op = new Subtractor(state);
        op.execute();
        assertEquals(-2.0, state.getCurrentValue());
    }

    /**
     * Test the square operation.
     */
    @Test
    public void squareTest() {
        op = new Square(state);
        op.execute();
        assertEquals(16.0, state.getCurrentValue());
    }

    /**
     * Test the factorial operation.
     */
    @Test
    public void multiplicatorTest() {
        op = new Multiplier(state);
        op.execute();
        assertEquals(8.0, state.getCurrentValue());
    }

    /**
     * Test the factorial operation.
     */
    @Test
    public void reciprocalTest() {
        op = new Reciprocal(state);
        op.execute();
        assertEquals(0.25, state.getCurrentValue());
    }

    /**
     * Test that the reciprocal of 0 sets the error state.
     */
    @Test
    public void reciprocalZeroTest() {
        op = new Reciprocal(state);
        state.setCurrentValue(0.0);
        op.execute();
        assertTrue(state.getError());
    }

    /**
     * Test the factorial operation.
     */
    @Test
    public void squareRootTest() {
        op = new SquareRoot(state);
        op.execute();
        assertEquals(2, state.getCurrentValue());
    }

    /**
     * Test that taking the square root of a negative number sets the error state.
     */
    @Test
    public void negativeRootTest() {
        state.setCurrentValue(-1.0);
        op = new SquareRoot(state);
        op.execute();
        assertTrue(state.getError());
    }

    /**
     * Test the memory operators.
     */
    @Test
    public void memoryTest() {
        op = new MemoryRecall(state);
        op.execute();
        assertNull(state.getMemory());

        op = new MemoryStore(state);
        state.setCurrentValue(4.0);
        op.execute();
        assertEquals(4.0, state.getMemory());

        op = new MemoryRecall(state);
        op.execute();
        assertEquals(4.0, state.getCurrentValue());
    }

    /**
     * Test the clear error operation.
     */
    @Test
    public void clearErrorTest() {
        state.setError(true);
        state.setCurrentValue(1);
        state.setInputIntegral(false);
        state.setInputEntered(true);

        op = new ClearError(state);
        op.execute();
        assertEquals(0.0, state.getCurrentValue().doubleValue());
        assertTrue(state.isInputIntegral());
        assertFalse(state.isInputEntered());
        assertFalse(state.getError());
    }

    /**
     * Test the clear operator.
     */
    @Test
    public void clearTest() {
        op = new Clear(state);
        op.execute();
        assertEquals(0.0, state.getCurrentValue().doubleValue());
        assertFalse(state.getError());
        assertEquals(0, state.stack().getLength());
    }

    /**
     * Test the enter operator.
     */
    @Test
    public void enterTest() {
        op = new Enter(state);
        state.setCurrentValue(0.0);
        op.execute();
        assertEquals(0.0, state.stack().pop().doubleValue());
    }

    /**
     * Test the dot operator.
     */
    @Test
    public void dotTest() {
        state.setCurrentValue(0.0);
        op = new Digit(state, 3);
        op.execute();
        op = new Dot(state);
        op.execute();
        op = new Digit(state, 3);
        op.execute();
        assertEquals(3.3, state.getCurrentValue().doubleValue());
        op.execute();
        assertEquals(3.33, state.getCurrentValue().doubleValue());
        op.execute();
        assertEquals(3.333, state.getCurrentValue().doubleValue());
        op.execute();
        assertEquals(3.3333, state.getCurrentValue().doubleValue());
        op = new Digit(state, 2);
        op.execute();
        assertEquals(3.33332, state.getCurrentValue().doubleValue());
    }

    /**
     * Test the negate operator.
     */
    @Test
    public void negateTest() {
        op = new Negator(state);
        op.execute();
        assertEquals(-4.0, state.getCurrentValue().doubleValue());

        op = new Digit(state, 1);
        op.execute();
        assertEquals(-41.0, state.getCurrentValue().doubleValue());

        op = new Negator(state);
        op.execute();
        assertEquals(41.0, state.getCurrentValue().doubleValue());

        op = new Digit(state, 6);
        op.execute();
        assertEquals(416.0, state.getCurrentValue().doubleValue());
    }

    /**
     * Test if a negative zero is correctly set to 0 silenty.
     */
    @Test
    public void negateZeroTest() {
        state.setCurrentValue(0);
        op = new Negator(state);
        op.execute();
        assertEquals(0, state.getCurrentValue());
    }

    /**
     * Test the digit operator.
     */
    @Test
    public void digitTest() {
        state.setCurrentValue(0.0);
        op = new Digit(state, 5);
        op.execute();
        assertEquals(5.0, state.getCurrentValue().doubleValue());
        op = new Digit(state, 6);
        op.execute();
        assertEquals(56.0, state.getCurrentValue().doubleValue());
    }

    /**
     * Test the backspace operator.
     */
    @Test
    public void backspaceTest(){
        state.setCurrentValue(0.0);
        op = new Backspace(state);
        op.execute();
        assertEquals(0.0, state.getCurrentValue().doubleValue());

        state.setCurrentValue(0.0);
        op = new Digit(state, 5);
        op.execute();
        op = new Backspace(state);
        op.execute();
        assertEquals(0.0, state.getCurrentValue().doubleValue());

        state.setCurrentValue(0.0);
        op = new Digit(state, 5);
        op.execute();
        op = new Digit(state, 6);
        op.execute();
        op = new Backspace(state);
        op.execute();
        assertEquals(5.0, state.getCurrentValue().doubleValue());

        state.setCurrentValue(0.0);
        op = new Digit(state, 5);
        op.execute();
        op = new Dot(state);
        op.execute();
        op = new Digit(state, 6);
        op.execute();
        op = new Digit(state, 6);
        op.execute();
        op = new Backspace(state);
        op.execute();
        assertEquals(5.6, state.getCurrentValue().doubleValue());
    }

    /**
     * Test the sequence of operation given in the assignment.
     */
    @Test
    public void sequenceTest() {
        state.setCurrentValue(0.0);
        op = new Digit(state, 3);
        op.execute();
        op = new Dot(state);
        op.execute();
        op = new Digit(state, 5);
        op.execute();
        op = new Enter(state);
        op.execute();
        op = new Digit(state, 4);
        op.execute();
        op = new Adder(state);
        op.execute();
        assertEquals(7.5, state.getCurrentValue());

        op = new Digit(state, 2);
        op.execute();
        assertEquals(7.5, state.stack().peek());

        op = new Dot(state);
        op.execute();
        op = new Digit(state, 5);
        op.execute();
        op = new Square(state);
        op.execute();
        assertEquals(6.25, state.getCurrentValue());

        op = new Digit(state, 1);
        op.execute();
        assertEquals(6.25, state.stack().peek());
        assertEquals(1, state.getCurrentValue());

        op = new Adder(state);
        op.execute();
        assertEquals(7.25, state.getCurrentValue());

        op = new Divider(state);
        op.execute();
        assertEquals(1.0344827586206897, state.getCurrentValue());
    }
}