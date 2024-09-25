package util;

import java.util.NoSuchElementException;


/**
 * Stack iterator class.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
public class Iterator<T> implements java.util.Iterator<T> {
    /**
     * Node for the current element
     */
    private Node<T> node;

    /**
     * Constructor of the iterator. Sets the node to the given node.
     *
     * @param node The node to start the iterator from.
     */
    Iterator(Node<T> node) {
        this.node = node;
    }

    /**
     * Checks if the iterator has a next element.
     *
     * @return True if the iterator has a next element, false otherwise.
     */
    public boolean hasNext() {
        return node != null;
    }

    /**
     * Iterate to the next element.
     *
     * @return The data of the current element.
     * @throws NoSuchElementException If there is no next element.
     */
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Node<T> current = node;
        node = node.next;
        return current.data;
    }
}
