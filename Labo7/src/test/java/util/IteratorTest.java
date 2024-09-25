package util;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
public class IteratorTest {
    /**
     * Test if the hasNext method works.
     */
    @Test
    public void hasNextTest() {
        Stack<Integer> stack = new Stack<>();
        Iterator<Integer> Iterator = stack.getIterator();
        assertFalse(Iterator.hasNext()); // Empty list has no next element

        stack.push(1);
        Iterator = stack.getIterator();
        assertTrue(Iterator.hasNext()); // List with 1 element has no next element

        stack.push(2);
        Iterator = stack.getIterator();
        assertTrue(Iterator.hasNext()); // List with 2 elements has a next element
    }

    /**
     * Test if the next method works.
     */
    @Test
    public void nextTest() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        Iterator<Integer> Iterator = stack.getIterator();
        assertEquals(2, Iterator.next());
    }

    /**
     * Test if the next method throws an exception when there is no next element.
     */
    @Test
    public void nextThrowsTest() {
        Stack<Integer> stack = new Stack<>();
        Iterator<Integer> Iterator = stack.getIterator();
        assertThrows(NoSuchElementException.class, Iterator::next);
    }
}
