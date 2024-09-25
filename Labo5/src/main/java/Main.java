import POO.Lab.MatrixReloaded.Matrix;

/**
 * Test program for the MatrixReloaded laboratory.
 *
 * @author Aubry Mangold
 * @author Rafaël Dousse
 */
public class Main {
    static final String HELP_MESSAGE = "usage: <n1> <m1> <n2> <m2> <modulo>";

    /**
     * Entry point of the application.
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        if (args.length != 5) {
            throw new RuntimeException("Not enough arguments provided.\n" + HELP_MESSAGE);
        }

        int n1, m1, n2, m2, modulo;

        try {
            n1 = Integer.parseInt(args[0]);
            m1 = Integer.parseInt(args[1]);
            n2 = Integer.parseInt(args[2]);
            m2 = Integer.parseInt(args[3]);
            modulo = Integer.parseInt(args[4]);
        } catch (NumberFormatException e) {
            throw new RuntimeException("One or more arguments are not numbers.", e);
        }

        final Matrix matrixOne = new Matrix(n1, m1, modulo);
        final Matrix matrixTwo = new Matrix(n2, m2, modulo);

        final Matrix matrixAdd = Matrix.add(matrixOne, matrixTwo);
        final Matrix matrixSub = Matrix.add(matrixOne, matrixTwo);
        final Matrix matrixMult = Matrix.add(matrixOne, matrixTwo);

        final String output = "The modulus is " + modulo + "\n" +
                "one:" + "\n" + matrixOne + "\n" +
                "two:" + "\n" + matrixTwo + "\n" +
                "one + two:" + "\n" + matrixAdd + "\n" +
                "one - two:" + "\n" + matrixSub + "\n" +
                "one x two:" + "\n" + matrixMult;

        System.out.println(output);
    }
}
