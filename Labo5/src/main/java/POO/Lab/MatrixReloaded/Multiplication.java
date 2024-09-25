package POO.Lab.MatrixReloaded;

/**
 * The multiplication operation.
 *
 * @author Aubry Mangold
 * @author Rafaël Dousse
 */
class Multiplication extends BinaryOperation {
    /**
     * Apply the multiplication operation on two operands.
     *
     * @param op1 The first operand
     * @param op2 The second operand
     * @return The result of the multiplication
     */
    @Override
    public int apply(int op1, int op2) {
        return op1 * op2;
    }
}
