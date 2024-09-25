package POO.Lab;

/**
 * A class representing an integer for lab 4, exercise 2.
 *
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html">Integer</a> for implementation hints.
 */
public class Int {
    /**
     * The value of the Int.
     */
    private int value;

    /**
     * Default constructor.
     */
    public Int() {
        this(0);
    }

    /**
     * Constructor with initial value.
     *
     * @param value The initial value of the Int
     */
    public Int(int value) {
        this.value = value;
    }

    /**
     * Get the value of the Int.
     *
     * @return The value of the Int.
     */
    public int getValue() {
        return value;
    }

    /**
     * Set the value of the Int.
     *
     * @param value The new value of the Int.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Get the value of the Int as a string.
     *
     * @return The value of the Int as a string.
     */
    public String toString() {
        return "" + getValue();
    }

    /**
     * Swap two Int objects.
     *
     * @param first  The first Int to be swapped
     * @param second The second Int to be swapped
     */
    static public void swap(Int first, Int second) {
        int temp = first.getValue();
        first.setValue(second.getValue());
        second.setValue(temp);
    }

    /**
     * Swap two Int objects in an array. This method doesn't build on the swap method because the exercice
     * wasn't asking for it.
     *
     * @param tab    The array of Int
     * @param first  The position of the first Int
     * @param second The position of the second Int
     */
    static public void swap(Int[] tab, int first, int second) {
        if (first < tab.length && second < tab.length) {
            int tmp = tab[first].getValue();
            tab[first].setValue(tab[second].getValue());
            tab[second].setValue(tmp);
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    /**
     * Swap an Int objects with this.
     *
     * @param other The Int to be swapped
     */
    public void swap(Int other) {
        int tmp = this.getValue();
        this.setValue(other.getValue());
        other.setValue(tmp);
    }

    /**
     * Compares two Int objects.
     *
     * @param other The Int to be compared
     * @return 0 if equal, a positive number if this is greater than other, a negative number otherwise
     */
    public int compareTo(Int other) {
        return getValue() - other.getValue();
    }

    /**
     * Converts a string array representing numbers to an Int array
     *
     * @param nums The string array to be converted
     * @return The integer array
     */
    public static Int[] parseArrayFromString(String[] nums) {
        int len = nums.length;
        Int[] result = new Int[len];

        for (int i = 0; i < len; i++) {
            result[i] = new Int(Util.parseInt(nums[i]));
        }

        return result;
    }

    /**
     * Sorts an array of Int using bubble sort.
     *
     * @param arr The array to be sorted
     * @return A sorted array
     */
    public static Int[] sortArray(Int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    arr[j].swap(arr[j + 1]);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }

        return arr;
    }
}
