package POO.Lab.MatrixReloaded;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test the binary operators.
 *
 * @see BinaryOperation
 */
public class BinaryOperationTest {
    /**
     * Test the addition operation.
     *
     * @see Addition
     */
    @Test
    public void additionTest() {
        Addition add = new Addition();
        assertEquals(5, add.apply(2, 3));
    }

    /**
     * Test the subtraction operation.
     *
     * @see Subtraction
     */
    @Test
    public void subtractionTest() {
        Subtraction sub = new Subtraction();
        assertEquals(1, sub.apply(3, 2));
    }

    /**
     * Test the multiplication operation.
     *
     * @see Multiplication
     */
    @Test
    public void multiplicationTest() {
        Multiplication mult = new Multiplication();
        assertEquals(6, mult.apply(2, 3));
    }
}
