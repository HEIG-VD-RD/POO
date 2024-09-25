package chess.engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import chess.engine.board.Board;
import chess.engine.board.Move;
import chess.engine.board.MoveResult;
import chess.engine.board.Square;
import chess.engine.util.PieceFactory;
import chess.engine.util.PromotionChoice;

import static chess.PieceType.PAWN;

/**
 * Pawn piece.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public class Pawn extends Piece {
    /**
     * Constructor.
     *
     * @param board The board.
     * @param color The color.
     */
    public Pawn(Board board, PlayerColor color) {
        super(board, PieceType.PAWN, color);
    }

    /**
     * Check if the move is pseudo-legal.
     *
     * @param move The move to check.
     * @return True if the move is pseudo-legal, false otherwise.
     */
    @Override
    public boolean pseudoMove(Move move) {
        var startingRank = color() == PlayerColor.WHITE ? 1 : 6;
        var direction = color() == PlayerColor.WHITE ? 1 : -1;

        // Forward move.
        if (move.from().isVerticalTo(move.to())
                && move.to().y() == move.from().y() + direction
                && !hasEnemyOn(move.to())
                && !hasAllyOn(move.to())) {
            return true;
        }

        // Double forward move.
        if (move.from().isVerticalTo(move.to())
                && move.to().y() == move.from().y() + 2 * direction
                && move.from().y() == startingRank
                && !hasAllyOnPath(move)
                && !hasAllyOn(move)
                && !hasEnemyOnPath(move)
                && !hasEnemyOn(move)) {
            return true;
        }

        // En passant.
        if (move.to().equals(board().enPassantSquare())
                && move.distance() == 1
                && move.from().isDiagonalTo(move.to())
                && move.to().y() == move.from().y() + direction
                && board().pieces().get(move.to()) == null) {
            return true;
        }

        // Capture move.
        return move.from().isDiagonalTo(move.to())
                && move.to().y() == move.from().y() + direction
                && hasEnemyOn(move.to());
    }

    /**
     * Check if the move is legal.
     *
     * @param move The move to check.
     * @return True if the move is legal, false otherwise.
     */
    @Override
    public MoveResult move(Move move) {
        boolean legal = pseudoMove(move) && super.move(move).isValid();
        var moveResult = new MoveResult(legal);
        var startingRank = color() == PlayerColor.WHITE ? 1 : 6;
        final var direction = color() == PlayerColor.WHITE ? 1 : -1;

        // Double forward, set en passant
        if (move.from().isVerticalTo(move.to())
                && move.to().y() == move.from().y() + 2 * direction
                && move.from().y() == startingRank) {
            moveResult.setEnPassantSquare(new Square(move.from().x(), move.from().y() + direction));
        }

        // En passant, remove the captured pawn
        if (move.to().equals(board().enPassantSquare())
                && move.distance() == 1
                && move.from().isDiagonalTo(move.to())
                && move.to().y() == move.from().y() + direction
                && board().pieces().get(move.to()) == null) {
            // Remove the captured pawn
            moveResult.addRemovedPieces(new Square(move.to().x(), move.from().y()));
        }

        // Promotion
        if (move.to().isLastRank(color())) {
            moveResult.setPromotion();
        }

        // Reset half move clock because a pawn moved.
        if (moveResult.isValid()) {
            moveResult.setResetHalfMoveClock();
        }

        return moveResult;
    }
}
