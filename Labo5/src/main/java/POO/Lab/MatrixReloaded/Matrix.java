package POO.Lab.MatrixReloaded;

import java.util.Arrays;

/**
 * A matrix representation in row-major form.
 *
 * @author Aubry Mangold
 * @author Rafaël Dousse
 */
public class Matrix {
    /**
     * Module of the matrix. The elements of the matrix must be between 0 and modulus - 1.
     */
    private final int modulus;

    /**
     * Encapsulated data of the matrix.
     */
    private final int[][] data;

    /**
     * Class constructor.
     *
     * @param rows      The number of rows of the matrix.
     * @param columns   The number of columns of the matrix.
     * @param modulus   The modulus of the matrix.
     * @param randomize Whether to randomize the data of the matrix or not.
     * @throws RuntimeException Throws if modulus is negative or 0.
     */
    private Matrix(int rows, int columns, int modulus, boolean randomize) {
        if (modulus <= 0) {
            throw new RuntimeException("Modulus must be greater than 0.");
        } else if (rows <= 0 || columns <= 0) {
            throw new RuntimeException("Row and column count must be greater than 0.");
        }
        this.modulus = modulus;
        this.data = new int[rows][columns];

        if (randomize) {
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < columns; ++j) {
                    this.data[i][j] = (int) (Math.random() * modulus);
                }
            }
        }
    }

    /**
     * Class constructor. Creates a matrix of the given size with random numbers.
     *
     * @param rows    The number of rows of the matrix.
     * @param columns The number of columns of the matrix.
     * @param modulus The modulus of the matrix.
     * @throws RuntimeException Throws if the row or column size is negative or 0.
     */
    public Matrix(int rows, int columns, int modulus) {
        this(rows, columns, modulus, true);
    }

    /**
     * Class constructor. Create a matrix with the given data.
     *
     * @param data    The data of the matrix.
     * @param modulus The module of the matrix.
     * @throws RuntimeException Throws if the matrix has an element greater or equal to modulus.
     */
    public Matrix(int[][] data, int modulus) {
        this(data.length, data[0].length, modulus, false);

        // Deep-copy the data.
        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[0].length; ++j) {
                if (data[i][j] >= modulus) {
                    throw new RuntimeException("Matrix element greater or equal to modulus.");
                } else if (data[i][j] < 0) {
                    throw new RuntimeException("Given data contains negative numbers.");
                }
                this.data[i][j] = data[i][j];
            }
        }
    }

    /**
     * Apply a binary operation on each element of two matrices.
     *
     * @param op The operation to apply.
     * @param m1 The first matrix.
     * @param m2 The second matrix.
     * @return The result matrix.
     */
    private static Matrix entrywiseOperation(Matrix m1, Matrix m2, BinaryOperation op) {
        Matrix m;
        if (m1.modulus != m2.modulus) {
            throw new RuntimeException("Matrices modulus mismatch.");
        }

        int rows = Math.max(m1.data.length, m2.data.length);
        int columns = Math.max(m1.data[0].length, m2.data[0].length);
        m = new Matrix(rows, columns, m1.modulus);

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                int op1 = i < m1.data.length && j < m1.data[0].length ? m1.data[i][j] : 0;
                int op2 = i < m2.data.length && j < m2.data[0].length ? m2.data[i][j] : 0;
                m.data[i][j] = Math.floorMod(op.apply(op1, op2), m.modulus);
            }
        }

        return m;
    }

    /**
     * Add two matrices.
     *
     * @param m1 The first matrix.
     * @param m2 The second matrix.
     * @return The result matrix.
     */
    public static Matrix add(Matrix m1, Matrix m2) {
        return entrywiseOperation(m1, m2, new Addition());
    }

    /**
     * Subtract two matrices.
     *
     * @param m1 The first matrix.
     * @param m2 The second matrix.
     * @return The result matrix.
     */
    public static Matrix subtract(Matrix m1, Matrix m2) {
        return entrywiseOperation(m1, m2, new Subtraction());
    }

    /**
     * Multiply two matrices.
     *
     * @param m1 The first matrix.
     * @param m2 The second matrix.
     * @return The result matrix.
     */
    public static Matrix multiply(Matrix m1, Matrix m2) {
        return entrywiseOperation(m1, m2, new Multiplication());
    }

    /**
     * Get the modulus of the matrix.
     *
     * @return The modulus of the matrix.
     */
    public int getModulus() {
        return modulus;
    }

    /**
     * Get the data of the matrix.
     *
     * @return The data of the matrix.
     */
    public int[][] getData() {
        return data;
    }

    /**
     * Get the string representation of the matrix.
     *
     * @return The string representation of the matrix.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] i : data) {
            for (int j : i) {
                sb.append(j).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Check if two matrices' members are deeply equal.
     *
     * @param o The object to compare to.
     * @return True if the matrices are deeply equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Matrix matrix)) return false;

        return modulus == matrix.modulus && Arrays.deepEquals(data, matrix.data);
    }
}
