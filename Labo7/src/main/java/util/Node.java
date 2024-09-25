package util;

/**
 * Node class.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
class Node<T> {

    /**
     * The data of the node.
     */
    final T data;
    /**
     * The next node.
     */
    Node<T> next;

    /**
     * Constructor of the node. Sets the data of the node to the given data and the next node to null.
     *
     * @param data The data of the node.
     */
    Node(T data) {
        this.data = data;
        this.next = null;
    }

}
