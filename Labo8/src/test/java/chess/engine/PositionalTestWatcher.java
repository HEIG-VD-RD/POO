package chess.engine;

import chess.engine.board.Move;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test watcher that prints the missing and extra moves when a test fails.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public class PositionalTestWatcher implements TestWatcher, BeforeEachCallback, AfterEachCallback {
    /**
     * The output stream.
     */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    /**
     * The original output stream.
     */
    private final PrintStream originalOut = System.out;

    /**
     * Called before each test. Resets the output stream and stores it in the extension context.
     *
     * @param context The extension context.
     */
    @Override
    public void beforeEach(ExtensionContext context) {
        outContent.reset();
        System.setOut(new PrintStream(outContent));
        context.getStore(ExtensionContext.Namespace.GLOBAL).put("outContent", outContent);
    }

    /**
     * Called after each test. Resets the output stream.
     * @param context The extension context.
     */
    @Override
    public void afterEach(ExtensionContext context) {
        System.setOut(originalOut);
    }

    /**
     * Called when a test fails. Prints the missing and extra moves.
     *
     * @param context The extension context.
     * @param cause  The cause of the failure.
     */
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        ByteArrayOutputStream outContent = context.getStore(ExtensionContext.Namespace.GLOBAL)
                                                  .get("outContent", ByteArrayOutputStream.class);
        String stdout = outContent.toString();

        System.out.println("Test failed: " + context.getDisplayName());

        final String fen = context.getDisplayName().split(",")[2];
        List<Move> expected = legalMoves(fen);
        List<Move> actual = new ArrayList<>();
        Arrays.stream(stdout.split("\n")).forEach(line -> actual.add(new Move(line)));

        List<Move> expectedCopy = new ArrayList<>(expected);
        List<Move> actualCopy = new ArrayList<>(actual);
        expectedCopy.removeAll(actual);
        actualCopy.removeAll(expected);
        System.out.println("Missing moves:");
        expectedCopy.forEach(System.out::println);
        System.out.println("Extra moves:");
        actualCopy.forEach(System.out::println);
    }

    /**
     * Called when a test is successful. Resets the output stream.
     *
     * @param context The extension context.
     */
    @Override
    public void testSuccessful(ExtensionContext context) {
        System.setOut(originalOut);
    }

    /**
     * Called when a test is aborted. Resets the output stream.
     *
     * @param context The extension context.
     * @param cause  The cause of the abortion.
     */
    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.setOut(originalOut);
    }

    /**
     * Get the legal moves for the given FEN using the `legal_moves.py` python script.
     * Note: this requires python and pychess to be installed and available in the system path.
     * The script can be found in `src/test/resources/legal_moves.py`.
     *
     * @param fen The FEN string of the position.
     * @return The list of legal moves.
     */
    private List<Move> legalMoves(String fen) {
        List<Move> moves = new ArrayList<>();
        try {
            ProcessBuilder pb = new ProcessBuilder(Arrays.asList("python", "src/test/resources/legal_moves.py", fen));
            Process p = pb.start();

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String ret;
            while ((ret = in.readLine()) != null) {
                String[] moveList = ret.replace("[", "").replace("]", "").replace("'", "").split(", ");
                for (String move : moveList) {
                    moves.add(new Move(move));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error while executing `legalMoves.py`. Check that python and pychess are installed and available in the system path.");
        }

        return moves;
    }
}