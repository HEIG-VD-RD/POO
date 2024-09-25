package POO.Lab.MatrixReloaded;

/**
 * The subtraction operation.
 *
 * @author Aubry Mangold
 * @author Rafaël Dousse
 */
class Subtraction extends BinaryOperation {
    /**
     * Apply the subtraction operation on two operands.
     *
     * @param op1 The first operand
     * @param op2 The second operand
     * @return The result of the subtraction
     */
    @Override
    public int apply(int op1, int op2) {
        return op1 - op2;
    }
}
