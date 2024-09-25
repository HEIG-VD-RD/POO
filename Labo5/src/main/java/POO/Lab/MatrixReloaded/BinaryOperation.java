package POO.Lab.MatrixReloaded;

/**
 * A binary operation.
 *
 * @author Aubry Mangold
 * @author Rafaël Dousse
 */
abstract class BinaryOperation {
    /**
     * Apply a binary operation using two operands.
     *
     * @param op1 The first operand
     * @param op2 The second operand
     * @return The result of the operation
     */
    protected abstract int apply(int op1, int op2);
}
