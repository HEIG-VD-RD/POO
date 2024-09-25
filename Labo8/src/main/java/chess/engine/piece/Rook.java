package chess.engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import chess.engine.board.*;

import static chess.engine.board.CastlingType.*;

/**
 * Rook piece.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public class Rook extends Piece {
    /**
     * Constructor.
     *
     * @param board The board.
     * @param color The color.
     */
    public Rook(Board board, PlayerColor color) {
        super(board, PieceType.ROOK, color);
    }

    /**
     * Check if the move is pseudo-legal.
     *
     * @param move The move to check.
     * @return True if the move is pseudo-legal, false otherwise.
     */
    @Override
    public boolean pseudoMove(Move move) {
        return move.from().isOrthogonalTo(move.to());
    }

    /**
     * Check if the move is legal.
     *
     * @param move The move to check.
     * @return True if the move is legal, false otherwise.
     */
    @Override
    public MoveResult move(Move move) {
        boolean legal = pseudoMove(move) && !hasAllyOnPath(move) && !hasAllyOn(move.to()) && !hasEnemyOnPath(
                move) && !isPinned(move) && super.move(move).isValid();

        var result = new MoveResult(legal);

        if (move.from().equals(new Square(0, 0))) {
            result.addLostCastlingRight(WHITE_QUEENSIDE);
        } else if (move.from().equals(new Square(7, 0))) {
            result.addLostCastlingRight(WHITE_KINGSIDE);
        } else if (move.from().equals(new Square(0, 7))) {
            result.addLostCastlingRight(BLACK_QUEENSIDE);

        } else if (move.from().equals(new Square(7, 7))) {
            result.addLostCastlingRight(BLACK_KINGSIDE);
        }

        return result;
    }
}
