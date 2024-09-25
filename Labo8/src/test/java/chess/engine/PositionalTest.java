package chess.engine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * These test assert that the GUI allows for the correct amount of possible move in a given position.
 * The test is run on a set of positions from a CSV file which are automatically loaded. The CSV file
 * contains the FEN string of the position, and the number of possible moves. The next player to move
 * is always black. The test is run on all positions in the `positions.csv` file. If a test fails, the
 * test watcher will print the missing and extra moves.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public class PositionalTest {
    @ExtendWith(PositionalTestWatcher.class)
    @ParameterizedTest
    @CsvFileSource(resources = "/positions.csv", numLinesToSkip = 1)
    public void chessPositionsSuite(String group, String name, String fen, int expectedMoves) {
        assertEquals(expectedMoves, countMoves(fen));
    }

    /**
     * Count the number of moves that are found in a given position.
     *
     * @param fen The FEN string of the position.
     * @return The number of moves that are found.
     */
    public int countMoves(String fen) {
        var moves = TestController.generateAllMovesForPlayer(new TestController(fen), true);
        return moves.size();
    }

    /**
     * Test a single position. Used for debugging purposes. The EXPECTED MOVES and FEN variables should
     * be changed to what is being tested.
     */
    @Test
    public void testPosition() {
        final int EXPECTED_MOVES = 43;
        final String FEN = "r1bqkbnr/pppp1ppp/8/n3p2Q/2B1P3/8/PPPP1PPP/RNB1K1NR w KQkq - 2 3";

        assertEquals(EXPECTED_MOVES, countMoves(FEN));
    }
}

