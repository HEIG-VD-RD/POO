package util;

import java.lang.reflect.Array;

/**
 * A generic Stack class.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
public class Stack<T> {
    /**
     * The head of the stack
     */
    private Node<T> head;

    /**
     * The length of the stack
     */
    private int length;

    /**
     * Constructor of the stack. Sets the head to null.
     */
    public Stack() {
        head = null;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return True if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Adds data to the top of the stack.
     *
     * @param value The data to add to the top of the stack.
     */
    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = head;
        head = newNode;
        length++;
    }

    /**
     * Removes the data at the top of the stack and returns it.
     *
     * @return The data at the top of the stack.
     * @throws IllegalStateException If the stack is empty.
     */
    public T pop() {
        if (head == null) {
            throw new IllegalStateException("Stack is empty");
        }

        Node<T> currentNode = head;
        head = head.next;
        length--;

        return currentNode.data;
    }

    /**
     * Get the length of the stack.
     *
     * @return The length of the stack.
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns the data at the top of the stack without removing it.
     *
     * @return The data at the top of the stack.
     * @throws IllegalStateException If the stack is empty.
     */
    public T peek() {
        if (head == null) {
            throw new IllegalStateException("Stack is empty");
        }
        return head.data;
    }

    /**
     * Returns the data in the stack as an array.
     *
     * @return The data in the stack as an array.
     */
    public T[] getData() {
        if (this.isEmpty()) {
            return null;
        }

        // As discussed in class this seems the best way to create an array of generic type
        @SuppressWarnings("unchecked") T[] tempArray = (T[]) Array.newInstance(head.data.getClass(), length);

        Iterator<T> iterator = this.getIterator();
        int index = 0;
        while (iterator.hasNext()) {
            tempArray[index++] = iterator.next();
        }

        return tempArray;
    }

    /**
     * Iterator for the stack.
     *
     * @return An iterator for the stack starting at the top.
     */
    public Iterator<T> getIterator() {
        return new Iterator<>(head);
    }

    /**
     * Visual representation of the stack.
     *
     * @return A string representation of the stack.
     */
    @Override
    public String toString() {
        if (head == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Iterator<T> Iterator = this.getIterator();

        while (Iterator.hasNext()) {
            sb.append(Iterator.next().toString()).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
