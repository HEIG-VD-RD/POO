import POO.Lab.MatrixReloaded.Matrix;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the test program.
 *
 * @author Aubry Mangold
 * @author Rafaël Dousse
 */
public class MainTest {
    /**
     * Test the main method with the provided example using regex.
     */
    @Test
    public void outputTest() {
        final PrintStream standardOut = System.out;
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outputStreamCaptor));

        final String[] args = {"3", "4", "3", "5", "5"};
        final String expected = """
                The modulus is 5
                one:
                [0-9]+ [0-9]+ [0-9]+ [0-9]+\s
                [0-9]+ [0-9]+ [0-9]+ [0-9]+\s
                [0-9]+ [0-9]+ [0-9]+ [0-9]+\s
                                
                two:
                [0-9]+ [0-9]+ [0-9]+ [0-9]+ [0-9]+\s
                [0-9]+ [0-9]+ [0-9]+ [0-9]+ [0-9]+\s
                [0-9]+ [0-9]+ [0-9]+ [0-9]+ [0-9]+\s
                                
                one + two:
                [0-9]+ [0-9]+ [0-9]+ [0-9]+ [0-9]+\s
                [0-9]+ [0-9]+ [0-9]+ [0-9]+ [0-9]+\s
                [0-9]+ [0-9]+ [0-9]+ [0-9]+ [0-9]+\s
                                
                one - two:
                [0-9]+ [0-9]+ [0-9]+ [0-9]+ [0-9]+\s
                [0-9]+ [0-9]+ [0-9]+ [0-9]+ [0-9]+\s
                [0-9]+ [0-9]+ [0-9]+ [0-9]+ [0-9]+\s
                                
                one x two:
                [0-9]+ [0-9]+ [0-9]+ [0-9]+ [0-9]+\s
                [0-9]+ [0-9]+ [0-9]+ [0-9]+ [0-9]+\s
                [0-9]+ [0-9]+ [0-9]+ [0-9]+ [0-9]+\s
                \n""";

        Main.main(args);

        assertLinesMatch(expected.lines(), outputStreamCaptor.toString().lines());

        System.setOut(standardOut);
    }

    /**
     * Test that the main method throws an exception when not enough arguments are provided.
     */
    @Test
    public void wrongArgumentsTest() {
        final String[] args = new String[]{"1", "2", "3"};
        assertThrows(RuntimeException.class, () -> {
            Main.main(args);
        });
    }

    /**
     * Test that the operations given as an example in the lab handout have the right output.
     */
    @Test
    public void requiredOperationsTest() {
        final Matrix m1 = new Matrix(new int[][]{{1, 3, 1, 1}, {3, 2, 4, 2}, {1, 0, 1, 0}}, 5);
        final Matrix m2 = new Matrix(new int[][]{{1, 4, 2, 3, 2}, {0, 1, 0, 4, 2}, {0, 0, 2, 0, 2}}, 5);
        final Matrix expected1 = new Matrix(new int[][]{{2, 2, 3, 4, 2}, {3, 3, 4, 1, 2}, {1, 0, 3, 0, 2}}, 5);
        final Matrix expected2 = new Matrix(new int[][]{{0, 4, 4, 3, 3}, {3, 1, 4, 3, 3}, {1, 0, 4, 0, 3}}, 5);
        final Matrix expected3 = new Matrix(new int[][]{{1, 2, 2, 3, 0}, {0, 2, 0, 3, 0}, {0, 0, 2, 0, 0}}, 5);

        assertEquals(expected1, Matrix.add(m1, m2));
        assertEquals(expected2, Matrix.subtract(m1, m2));
        assertEquals(expected3, Matrix.multiply(m1, m2));
    }
}