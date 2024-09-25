package POO.Lab.MatrixReloaded;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test for the matrix class.
 *
 * @author Aubry Mangold
 * @author Rafaël Dousse
 */
public class MatrixTest {
    /**
     * Test the overloading of the `equals` method.
     */
    @Test
    public void equalsShouldCompareMembers() {
        final Matrix m1 = new Matrix(new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 5);
        final Matrix m2 = new Matrix(new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 5);
        assertEquals(m1, m2);
    }

    /**
     * Test the creation of a matrix with bad parameters such as rows, columns or modulus negative or inferior to 0.
     */
    @Test
    public void creationWithBadParametersTest() {
        assertThrows(RuntimeException.class, () -> new Matrix(2, 2, -1));
        assertThrows(RuntimeException.class, () -> new Matrix(2, 2, 0));
        assertThrows(RuntimeException.class, () -> new Matrix(0, 0, 1));
        assertThrows(RuntimeException.class, () -> new Matrix(0, 2, 1));
        assertThrows(RuntimeException.class, () -> new Matrix(2, 0, 1));
        assertThrows(RuntimeException.class, () -> new Matrix(0, 0, 1));
    }

    /**
     * Test that an operation on matrices of different sizes zeroes the element that are out of range of a matrix.
     */
    @Test
    public void operationZeroesElementsTest() {
        final Matrix m1 = new Matrix(new int[][]{{2, 4, 2}, {3, 1, 2}, {1, 3, 2}}, 5);
        final Matrix m2 = new Matrix(new int[][]{{1, 2}, {3, 2}, {3, 4}}, 5);
        final Matrix m3 = Matrix.add(m1, m2);

        final Matrix expected = new Matrix(new int[][]{{3, 1, 2}, {1, 3, 2}, {4, 2, 2}}, 5);
        assertEquals(m3, expected);
    }

    /**
     * Test that the addition operation is implemented.
     */
    @Test
    public void additionTest() {
        final Matrix m1 = new Matrix(new int[][]{{2, 4}, {3, 1}, {1, 3}}, 5);
        final Matrix m2 = new Matrix(new int[][]{{0, 0}, {0, 0}, {0, 0}}, 5);
        final Matrix m3 = Matrix.add(m1, m2);

        final Matrix expected = new Matrix(new int[][]{{2, 4}, {3, 1}, {1, 3}}, 5);
        assertEquals(m3, expected);
    }

    /**
     * Test that the subtraction operation is implemented.
     */
    @Test
    public void subtractionTest() {
        final Matrix m1 = new Matrix(new int[][]{{2, 4}, {3, 1}, {1, 3}}, 5);
        final Matrix m2 = new Matrix(new int[][]{{0, 0}, {0, 0}, {0, 0}}, 5);
        final Matrix m3 = Matrix.subtract(m1, m2);

        final Matrix expected = new Matrix(new int[][]{{2, 4}, {3, 1}, {1, 3}}, 5);
        assertEquals(m3, expected);
    }

    /**
     * Test that the multiplication operation is implemented.
     */
    @Test
    public void multiplicationTest() {
        final Matrix m1 = new Matrix(new int[][]{{2, 4}, {3, 1}, {1, 3}}, 5);
        final Matrix m2 = new Matrix(new int[][]{{0, 0}, {0, 0}, {0, 0}}, 5);
        final Matrix m3 = Matrix.multiply(m1, m2);

        final Matrix expected = new Matrix(new int[][]{{0, 0}, {0, 0}, {0, 0}}, 5);
        assertEquals(m3, expected);
    }

    /**
     * Test that two matrices with different modulos cannot be operated on.
     */
    @Test
    public void differentModuloTest() {
        final Matrix m1 = new Matrix(new int[][]{{2, 4}, {3, 1}, {1, 3}}, 5);
        final Matrix m2 = new Matrix(new int[][]{{0, 0}, {0, 0}, {0, 0}}, 6);

        assertThrows(RuntimeException.class, () -> Matrix.multiply(m1, m2));
    }

    /**
     * Test that a matrix cannot be initialized with an element greater or equal to modulo.
     */
    @Test
    public void elementGreaterOrEqualToModuloTest() {
        assertThrows(RuntimeException.class, () -> new Matrix(new int[][]{{2, 4}, {6, 1}, {1, 3}}, 5));
    }

    /**
     * Test that a matrix cannot be initialized with an element smaller than 0.
     */
    @Test
    public void elementSmallerThanZeroTest() {
        assertThrows(RuntimeException.class, () -> new Matrix(new int[][]{{2, -4}, {-6, 1}}, 5));
    }

    /**
     * Test the string representation of a matrix.
     */
    @Test
    public void toStringTest() {
        final Matrix m1 = new Matrix(new int[][]{{2, 2, 4}, {3, 1, 3}, {1, 3, 1}}, 5);
        final String s = "2 2 4 \n3 1 3 \n1 3 1 \n";

        assertEquals(m1.toString(), s);
    }
}