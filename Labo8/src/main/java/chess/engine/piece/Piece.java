package chess.engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import chess.engine.board.*;

import java.util.Map;

/**
 * Chess piece representation. All the pieces inherit from this class.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public abstract class Piece {
    /**
     * The board the piece refers to.
     */
    private Board board;

    /**
     * The type of the piece.
     */
    private PieceType type;

    /**
     * The color of the piece.
     */
    private PlayerColor color;

    /**
     * Constructor.
     *
     * @param board The board the piece belongs to.
     * @param type  The type of the piece.
     * @param color The color of the piece.
     */
    public Piece(Board board, PieceType type, PlayerColor color) {
        this.setBoard(board);
        this.setType(type);
        this.setColor(color);
    }

    /**
     * Set the board of the piece.
     *
     * @param board The board of the piece.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Set the type of the piece.
     *
     * @param type The type of the piece.
     */
    public void setType(PieceType type) {
        this.type = type;
    }

    /**
     * Set the color of the piece.
     *
     * @param color The color of the piece.
     */
    public void setColor(PlayerColor color) {
        this.color = color;
    }

    /**
     * Check if the move is pseudo-legal, i.e. if the move is legal without considering the other pieces.
     *
     * @param move The move to check.
     * @return True if the move is pseudo-legal, false otherwise.
     */
    public abstract boolean pseudoMove(Move move);

    /**
     * Check if the destination square of a move has an enemy piece on it.
     *
     * @param move The move to check.
     * @return True if the square has an enemy piece on it, false otherwise.
     */
    public boolean hasEnemyOn(Move move) {
        return board().pieces().get(move.to()) != null && board().pieces().get(move.to()).color() != color();
    }

    /**
     * Get the board of the piece.
     *
     * @return The board of the piece.
     */
    public Board board() {
        return board;
    }

    /**
     * Get the color of the piece.
     *
     * @return The color of the piece.
     */
    public PlayerColor color() {
        return color;
    }

    /**
     * Check if the destination square of a move has an allied piece on it.
     *
     * @param move The move to check.
     * @return True if the square has an allied piece on it, false otherwise.
     */
    public boolean hasAllyOn(Move move) {
        return board().pieces().get(move.to()) != null && board().pieces().get(move.to()).color() == color();
    }

    /**
     * Check if there is an enemy on the path of a move.
     *
     * @param move The move to check.
     * @return True if there is an enemy on the path, false otherwise.
     */
    public boolean hasEnemyOnPath(Move move) {
        for (var square : move.innerPath()) {
            // The king needs to be "invisible" to detect if it is simply moving back on a rank or file that is under attack.
            if (hasEnemyOn(square) && board().pieces().get(square).type() != PieceType.KING) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a square has an enemy piece on it.
     *
     * @param square The square to check.
     * @return True if the square has an enemy piece on it, false otherwise.
     */
    public boolean hasEnemyOn(Square square) {
        return board().pieces().get(square) != null && board().pieces().get(square).color() != color();
    }

    /**
     * Get the type of the piece.
     *
     * @return The type of the piece.
     */
    public PieceType type() {
        return type;
    }

    /**
     * Check if there is an ally on the path of a move.
     *
     * @param move The move to check.
     * @return True if there is an ally on the path, false otherwise.
     */
    public boolean hasAllyOnPath(Move move) {
        for (var square : move.innerPath()) {
            if (hasAllyOn(square)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a square has an allied piece on it.
     *
     * @param square The square to check.
     * @return True if the square has an allied piece on it, false otherwise.
     */
    public boolean hasAllyOn(Square square) {
        return board().pieces().get(square) != null && board().pieces().get(square).color() == color();
    }

    /**
     * Check if the piece is pinned.
     *
     * @param attemptedMove The move to check.
     * @return True if the piece is pinned, false otherwise.
     */
    public boolean isPinned(Move attemptedMove) {
        // Find the path to the king and check if there is an enemy piece on it.
        var king = board().king(color());
        if (king == null) {
            return false; // Workaround to allow games without a king to occur without triggering null ptr exceptions
        }
        var kingSquare = king.position();
        var selfSquare = position();

        // Check that the piece is orthogonal or diagonal to the king
        if (!kingSquare.isAlignedTo(selfSquare)) {
            return false;
        }

        // Check that the piece can move towards the king
        var move = new Move(kingSquare, selfSquare); // Go from the king to the square to do a projection
        if (move.innerPath().stream().anyMatch(p -> board().pieces().get(p) != null)) {
            return false;
        }

        Piece pinningPiece = null;

        // Create the projected path (what is after the king and our piece) and check if there is an enemy piece on it.
        var projectedPath = move.projectedPath();
        for (var square : projectedPath) {
            var piece = board().pieces().get(square); // Might be null at this point. hasEnemyOn asserts it isn't.
            if (hasEnemyOn(square) && piece.type() != PieceType.KING && piece.type() != PieceType.KNIGHT && piece.type() != PieceType.PAWN && piece.move(
                    new Move(square, selfSquare)).isValid()) {
                pinningPiece = piece; // Store the pinning piece
                break;
            }
        }

        // Check if the attempted move targets the pinning piece
        if (pinningPiece != null && (attemptedMove.to().equals(pinningPiece.position()) || new Move(
                attemptedMove.from(), pinningPiece.position()).innerPath().contains(attemptedMove.to()))) {
            return false; // The move is valid as it targets the pinning piece or is toward the attacker.
        }

        return pinningPiece != null;
    }

    /**
     * Get the position of the piece.
     *
     * @return The position of the piece.
     */
    public Square position() {
        return board().pieces().entrySet().stream().filter(entry -> entry.getValue() == this).map(Map.Entry::getKey)
                      .findFirst().orElse(null);
    }

    /**
     * Check if the move is legal, i.e. if the move is legal considering the other pieces and game rules.
     *
     * @param move The move to check.
     * @return True if the move is legal, false otherwise.
     */
    public MoveResult move(Move move) {
        // Check that the move doesn't try to capture a king.
        var king = board().king(board().nextPlayer());
        if (king != null && move.to().equals(king.position())) {
            return new MoveResult(false);
        }

        // If in check, verify that the move either protects/moves the king or captures the attacker.
        king = board().king(board().currentPlayer());
        if (king != null && board().gameState() == GameState.CHECK && type() != PieceType.KING) {
            return new MoveResult(king.defendingMoves().contains(move));
        }
        var moveResult = new MoveResult(true);
        // If the move is a capture, reset the half move clock.
        if (board().pieces().containsKey(move.to()) && board().pieces().get(move.to()).color() != color()) {
            moveResult.resetHalfMoveClock();
        }

        return moveResult;
    }

    /**
     * String representation of the piece, in long textual format.
     *
     * @return The string representation of the piece.
     */
    @Override
    public String toString() {
        return String.format("%s %s", color(), type());
    }
}
