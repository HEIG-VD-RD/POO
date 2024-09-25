package util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 * Unit tests
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
public class StackTest {
    /**
     * Test the push method.
     */
    @Test
    public void pushTest() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        assertEquals(1, stack.peek());
    }

    /**
     * Test the pop method.
     */
    @Test
    public void popTest() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.peek());

    }

    /**
     * Test if the pop method throws an exception when there is no next element.
     */
    @Test
    public void popThrowsTest() {
        Stack<Integer> stack = new Stack<>();
        assertThrows(IllegalStateException.class, stack::pop);
    }

    /**
     * Test the peek method.
     */
    @Test
    public void peekTest() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.peek());
    }

    /**
     * Test if the peek method throws an exception when there is no next element.
     */
    @Test
    public void peekThrowsTest() {
        Stack<Integer> stack = new Stack<>();
        assertThrows(IllegalStateException.class, stack::peek);
    }

    /**
     * Test the length method.
     */
    @Test
    public void lengthTest() {
        Stack<Integer> stack = new Stack<>();
        assertEquals(0, stack.getLength());
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.getLength());
    }

    /**
     * Test the getData method.
     */
    @Test
    public void getDataTest() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        Integer[] myArray = {4, 3, 2, 1};

        assertArrayEquals(myArray, stack.getData());
    }

    @Test
    public void getDataDoesntExposeDataTest() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        var data = stack.getData();
        data[3] = 0;

        assertEquals(4, stack.peek());
    }

    /**
     * Test the toString method.
     */
    @Test public void toStringTest(){
        Stack<Integer> stack = new Stack<>();
        assertEquals("", stack.toString());
        stack.push(3);
        stack.push(2);
        stack.push(1);
        assertEquals("1 2 3", stack.toString());
    }
}
