package POO.Lab;

public class Util {
    private Util() {
        // Make class non-instantiable.
    }

    /**
     * Converts a string to an integer
     *
     * @param num The string to be converted
     * @return The integer value of the string
     * @throws NumberFormatException If the string is not a valid number
     */
    public static int parseInt(String num) {
        int result = 0;
        boolean isNegative = num.charAt(0) == '-';

        if (isNegative || num.charAt(0) == '+') {
            num = num.substring(1);
        }

        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) < '0' || num.charAt(i) > '9') {
                throw new NumberFormatException("Invalid number");
            }

            result += (num.charAt(i) - '0') * (int) Math.pow(10, num.length() - i - 1);
        }

        if (isNegative) {
            result *= -1;
        }

        return result;
    }

    /**
     * Converts a string array representing numbers to an integer array
     *
     * @param nums The string array to be converted
     * @return The integer array
     */
    public static int[] parseIntArrayFromString(String[] nums) {
        int len = nums.length;
        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            result[i] = parseInt(nums[i]);
        }

        return result;
    }

    /**
     * Sorts an array of integers using bubble sort.
     *
     * @param arr The array to be sorted
     * @return A sorted array
     */
    public static int[] sortIntArray(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
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
