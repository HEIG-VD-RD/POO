package chess.engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import chess.engine.board.Board;
import chess.engine.board.Move;
import chess.engine.board.MoveResult;

/**
 * Knight piece.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public class Knight extends Piece {
    /**
     * Constructor.
     *
     * @param board The board.
     * @param color The color.
     */
    public Knight(Board board, PlayerColor color) {
        super(board, PieceType.KNIGHT, color);
    }

    /**
     * Check if the move is pseudo-legal.
     *
     * @param move The move to check.
     * @return True if the move is pseudo-legal, false otherwise.
     */
    @Override
    public boolean pseudoMove(Move move) {
        int vDiff = move.to().y() - move.from().y();
        int hDiff = move.to().x() - move.from().x();
        if (Math.abs(vDiff) + Math.abs(hDiff) == 3 && Math.abs(vDiff) * Math.abs(hDiff) == 2) {
            return !hasAllyOn(move.to()) && !isPinned(move);
        }
        return false;
    }

    /**
     * Check if the move is legal.
     *
     * @param move The move to check.
     * @return True if the move is legal, false otherwise.
     */
    @Override
    public MoveResult move(Move move) {
        boolean result = pseudoMove(move) && super.move(move).isValid();
        return new MoveResult(result);
    }
}
