package POO.Lab;

/**
 * Main program for lab 4.
 */
public class Main {
    /**
     * Application entry point.
     * @param args The command-line arguments
     */
    public static void main(String... args) {
        // Exercice 3
        System.out.println("3. Tri d'un tableau de Int :");
        Int[] result = null;
        try {
            result = Int.parseArrayFromString(args);
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }

        for (var item : Int.sortArray(result)) {
            System.out.printf("%s ", item.toString());
        }
        System.out.println();
    }
}


