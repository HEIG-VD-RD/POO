package POO.Lab.MatrixReloaded;

/**
 * The addition operation.
 *
 * @author Aubry Mangold
 * @author Rafaël Dousse
 */
class Addition extends BinaryOperation {
    /**
     * Apply the addition operation on two operands.
     *
     * @param op1 The first operand
     * @param op2 The second operand
     * @return The result of the addition
     */
    @Override
    public int apply(int op1, int op2) {
        return op1 + op2;
    }
}
