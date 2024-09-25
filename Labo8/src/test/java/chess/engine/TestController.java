package chess.engine;

import chess.engine.board.GameState;
import chess.engine.board.Move;
import chess.engine.board.Square;
import chess.engine.util.BoardFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * A controller wrapper for positional testing.
 *
 * @author Rafael Dousse
 * @author Aubry Mangold
 */
public class TestController extends Controller {
    /**
     * Create a controller with a default board.
     */
    public TestController() {
        setBoard(BoardFactory.createInitialBoard());
    }

    /**
     * Create a controller with a custom board.
     *
     * @param fen The FEN string.
     */
    public TestController(String fen) {
        setBoard(BoardFactory.createFromFen(fen));
        updateGameState();
    }

    /**
     * Update the game state that tolerates null kings.
     */
    @Override
    public void updateGameState() {
        if (board().king(board().currentPlayer()) != null) {
            if (board().gameState() == GameState.CHECKMATE || board().isCheckmate()) {
                board().setGameState(GameState.CHECKMATE);
            } else if (board().gameState() == GameState.DRAW || board().isDraw()) {
                board().setGameState(GameState.DRAW);
            } else if (board().gameState() == GameState.CHECK || board().isCheck()) {
                board().setGameState(GameState.CHECK);
            } else {
                board().setGameState(GameState.NORMAL);
            }
        }
    }

    /**
     * Clone the controller.
     *
     * @return A clone of the controller.
     */
    public TestController clone() {
        super.clone();
        var clone = new TestController();
        clone.setBoard(board().clone());
        clone.setView(MockView.getInstance()); // Re-using the same view makes the tests really slow.
        return clone;
    }

    /**
     * Generate all moves for the current player.
     *
     * @param controller The controller.
     * @param printMoves Whether to print the moves.
     * @return A list of all moves for the current player.
     */
    public static List<Move> generateAllMovesForPlayer(TestController controller, boolean printMoves) {
        ArrayList<Move> moves = new ArrayList<>();
        if (controller.board().gameState() == GameState.CHECKMATE || controller.board().gameState() == GameState.DRAW) {
            return moves;
        }

        var pieces = controller.board().pieces(controller.board().currentPlayer()).entrySet();
        for (var piece : pieces) {
            for (int i = 0; i < 64; i++) {
                var newMove = new Move(piece.getKey(), new Square(i));
                var testController = controller.clone();
                if (testController.move(newMove.from().x(), newMove.from().y(), newMove.to().x(), newMove.to().y())) {
                    moves.add(new Move(piece.getKey(), new Square(i)));
                    if (printMoves) {
                        System.out.println(newMove.longAlgebraicNotation());
                    }
                }
            }
        }
        return moves;
    }
}
