package chess.engine;

import chess.ChessController;
import chess.ChessView;
import chess.engine.board.*;
import chess.engine.util.BoardFactory;
import chess.engine.util.PieceFactory;
import chess.engine.util.PromotionChoice;


/**
 * Chess controller.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public class Controller implements ChessController {
    /**
     * The view.
     */
    private ChessView view;

    /**
     * The model.
     */
    private Board board;

    /**
     * Start the controller and a new game.
     *
     * @param view The view to use.
     */
    @Override
    public void start(ChessView view) {
        this.setView(view);
        view.startView();
        newGame();
    }

    /**
     * Move a piece from one square to another. This function checks the values returned in the MoveResult class
     * and updates the board (model) and the GUI (view) accordingly.
     *
     * @param fromX The x coordinate of the square to move from.
     * @param fromY The y coordinate of the square to move from.
     * @param toX   The x coordinate of the square to move to.
     * @param toY   The y coordinate of the square to move to.
     * @return True if the move was successful, false otherwise.
     */
    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        displayGameState();
        if (board().gameState() == GameState.CHECKMATE || board().gameState() == GameState.DRAW) {
            displayGameState();
            return false;
        }

        final var move = new Move(new Square(fromX, fromY), new Square(toX, toY)); // Convert the coordinates to a move.
        final var piece = board().pieces().get(move.from());

        // Preamble checks.
        if (piece == null || piece.color() != board().currentPlayer()) {
            return false;
        }

        // Get the result of moving the piece.
        final MoveResult moveResult = piece.move(move);

        // Check the validity of the move.
        if (!moveResult.isValid()) {
            return false;
        }

        applyMoveSideEffects(moveResult);
        applyMove(move);

        if (moveResult.isPromotion()) handlePromotion(move);

        board().setLastHalfMove(move);
        board().nextTurn();

        updateGameState();

        return true;
    }

    /**
     * Applies the side effects of a move to the board and the view.
     *
     * @param moveResult The move result to apply.
     */
    private void applyMoveSideEffects(MoveResult moveResult) {
        // Adapt the castling rights.
        moveResult.lostCastlingRights().forEach(board().castlingRights()::remove);

        // Remove the deleted pieces and move the current piece.
        moveResult.removedPieces().forEach(square -> {
            board().pieces().remove(square);
            view().removePiece(square.x(), square.y());
        });

        // Move the pieces that were moved as a side effect of the current move.
        moveResult.movedPieces().forEach(m -> {
            var pieceToMove = board().pieces().get(m.from());
            board().pieces().remove(m.from());
            board().pieces().put(m.to(), pieceToMove);
            view().removePiece(m.from().x(), m.from().y());
            view().putPiece(pieceToMove.type(), pieceToMove.color(), m.to().x(), m.to().y());
        });

        if (moveResult.resetHalfMoveClock()) {
            board().setHalfMoveClock(0);
        } else {
            board().incrementHalfMoveClock();
        }
        board().setEnPassantSquare(moveResult.enPassantSquare());
    }

    /**
     * Apply the main move to the board and the view.
     *
     * @param move The move to apply.
     */
    private void applyMove(Move move) {
        final var piece = board().pieces().get(move.from());
        board().pieces().remove(move.from());
        board().pieces().put(move.to(), piece);
        view.removePiece(move.from().x(), move.from().y());
        view.putPiece(piece.type(), piece.color(), move.to().x(), move.to().y());
    }

    /**
     * Handle the promotion of a pawn by asking the user which piece they want to promote to.
     *
     * @param move The move to apply during the promotion.
     */
    private void handlePromotion(Move move) {
        var choice = view().askUser("Promotion", "Which piece would you like to promote to?",
                                    PromotionChoice.PROMOTION_CHOICES);
        if (choice == null) {
            throw new IllegalStateException("User cancelled promotion");
        }
        var newPiece = PieceFactory.createPiece(board(), choice.getPieceType(), board().currentPlayer());
        board().pieces().put(move.to(), newPiece);
        view().removePiece(move.from().x(), move.from().y());
        view().removePiece(move.to().x(), move.to().y());
        view().putPiece(newPiece.type(), newPiece.color(), move.to().x(), move.to().y());
    }

    /**
     * Create a new game by setting the board to the initial position and displaying the pieces.
     */
    @Override
    public void newGame() {
        board = BoardFactory.createInitialBoard();
        // Use the following line to debug a specific position :
        // setBoard(BoardFactory.createFromFen("r1bqkbnr/pppp1ppp/8/n3p2Q/2B1P3/8/PPPP1PPP/RNB1K1NR w KQkq - 2 3")); // For example scholar's mate
        for (var entry : board().pieces().entrySet()) {
            var square = entry.getKey();
            var piece = entry.getValue();
            view().putPiece(piece.type(), piece.color(), square.x(), square.y());
        }

        // Update the game state. Useful to debug positions and see if there is a check, checkmate, stalemate, etc.
        updateGameState();
    }

    /**
     * Set the view.
     */
    public void setView(ChessView view) {
        this.view = view;
    }

    /**
     * Set the board.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Get the board.
     */
    public Board board() {
        return board;
    }

    /**
     * Get the view.
     */
    public ChessView view() {
        return view;
    }

    /**
     * Update the game state and display a message if necessary.
     */
    public void updateGameState() {
        // A check depends on a king existing, otherwise we're in a special kind of chess (kingless, dros delnoch, etc.)
        if (board().king(board().currentPlayer()) != null && board().isCheck()) {
            if (board().isCheckmate()) {
                board().setGameState(GameState.CHECKMATE);
            } else {
                board().setGameState(GameState.CHECK);
            }
        } else if (board().isDraw()) {
            board().setGameState(GameState.DRAW);
        } else {
            board().setGameState(GameState.NORMAL);
        }

        displayGameState();
    }

    /**
     * Display a message if the game is in a special state.
     */
    private void displayGameState() {
        if (board().gameState() == GameState.CHECKMATE) {
            view().displayMessage("CHECKMATE");
        } else if (board().gameState() == GameState.CHECK) {
            view().displayMessage("CHECK");
        } else if (board().gameState() == GameState.DRAW) {
            view().displayMessage("DRAW");
        } else {
            view().displayMessage("");
        }
    }

    /**
     * Clone the controller.
     *
     * @return A clone of the controller.
     */
    public Controller clone() {
        var clone = new Controller();
        clone.setBoard(board().clone());
        return clone;
    }
}
