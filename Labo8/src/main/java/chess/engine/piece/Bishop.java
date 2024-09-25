package chess.engine.piece;

import chess.PieceType;
import chess.PlayerColor;
import chess.engine.board.Board;
import chess.engine.board.Move;
import chess.engine.board.MoveResult;

/**
 * Bishop piece.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public class Bishop extends Piece {
    /**
     * Constructor.
     *
     * @param board The board.
     * @param color The color.
     */
    public Bishop(Board board, PlayerColor color) {
        super(board, PieceType.BISHOP, color);
    }

    /**
     * Check if the move is pseudo-legal.
     *
     * @param move The move to check.
     * @return True if the move is pseudo-legal, false otherwise.
     */
    @Override
    public boolean pseudoMove(Move move) {
        return move.from().isDiagonalTo(move.to());
    }

    /**
     * Check if the move is legal.
     *
     * @param move The move to check.
     * @return True if the move is legal, false otherwise.
     */
    @Override
    public MoveResult move(Move move) {
        boolean result = pseudoMove(move)
                && super.move(move).isValid()
                && !hasAllyOnPath(move)
                && !hasAllyOn(move.to())
                && !hasEnemyOnPath(move)
                && !isPinned(move);

        return new MoveResult(result);
    }
}
