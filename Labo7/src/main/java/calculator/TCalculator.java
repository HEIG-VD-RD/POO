package calculator;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * .
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
public class TCalculator {
    /**
     * The state of the calculator.
     */
    private final State state = new State();

    /**
     * The operators of the calculator.
     */
    private final Map<String, Operator> operators = Map.ofEntries(Map.entry("0", new Digit(state, 0)), Map.entry("1", new Digit(state, 1)), Map.entry("2", new Digit(state, 2)), Map.entry("3", new Digit(state, 3)), Map.entry("4", new Digit(state, 4)), Map.entry("5", new Digit(state, 5)), Map.entry("6", new Digit(state, 6)), Map.entry("7", new Digit(state, 7)), Map.entry("8", new Digit(state, 8)), Map.entry("9", new Digit(state, 9)), Map.entry(".", new Dot(state)), Map.entry("+", new Adder(state)), Map.entry("-", new Subtractor(state)), Map.entry("*", new Multiplier(state)), Map.entry("/", new Divider(state)), Map.entry("√", new SquareRoot(state)), Map.entry("±", new Negator(state)), Map.entry("1/n", new Reciprocal(state)), Map.entry("^2", new Square(state)), Map.entry("C", new Clear(state)), Map.entry("CE", new ClearError(state)), Map.entry("MS", new MemoryStore(state)), Map.entry("MR", new MemoryRecall(state)), Map.entry("Enter", new Enter(state)));

    /**
     * Whether the user is entering the initial operation on the calculator.
     */
    private boolean initialInput = true;

    /**
     * Constructor.
     */
    public TCalculator() {
        run();
    }

    /**
     * Run the calculator. The main loop does an input -> action -> render cycle until the user enters "exit".
     */
    private void run() {
        while (true) {
            var input = input();
            if (input.equals("exit")) break;
            action(input);
            render();
        }
    }

    /**
     * Prompt the user for input.
     *
     * @return The user input.
     */
    private String input() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("> ");
        String input;
        while (!(input = keyboard.nextLine()).equals("\n") && !input.equals("exit") && !operators.containsKey(input) && !input.chars().allMatch(c -> operators.containsKey(Character.toString(c)))) {
            System.out.println("Invalid input");
            System.out.print("> ");
        }

        return input;
    }

    /**
     * Executes an action.
     *
     * @param input The name of the action to execute.
     */
    private void action(String input) {
        // Check whether the user wants to clear the calculator.
        if (input.equals("C") || input.equals("CE")) {
            initialInput = true;
            operators.get(input).execute();
            return;
        } else if (state.getError()) {
            // If the calculator is in error state, don't do anything.
            return;
        }

        // Check it the value is a command.
        if (operators.containsKey(input) && !Pattern.compile("^[0-9]*$").matcher(input).matches()) {
            operators.get(input).execute();
            return;
        }

        // Check whether the currentValue should be entered or not.
        if (!initialInput && Character.isDigit(input.toCharArray()[0])) {
            operators.get("Enter").execute();
        }

        // Execute the action specified by each character in the input string.
        for (var character : input.toCharArray()) {
            operators.get(String.valueOf(character)).execute();
            initialInput = false;
        }
    }

    /**
     * Render the calculator state.
     */
    private void render() {
        if (state.getError()) {
            System.out.printf("Error%n");
        } else {
            System.out.printf("%s %s%n", state.getCurrentValue(), state.stack());
        }
    }
}
