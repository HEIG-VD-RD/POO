package chess.engine.util;

import chess.PieceType;
import chess.PlayerColor;
import chess.engine.board.Board;
import chess.engine.piece.*;

/**
 * Piece factory.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public class PieceFactory {
    /**
     * Create a piece.
     *
     * @param board The board to pass to the piece.
     * @param type  The type of the piece.
     * @param color The color of the piece.
     * @return The newly created piece.
     */
    public static Piece createPiece(Board board, PieceType type, PlayerColor color) {
        switch (type) {
            case PAWN:
                return new Pawn(board, color);
            case KNIGHT:
                return new Knight(board, color);
            case BISHOP:
                return new Bishop(board, color);
            case ROOK:
                return new Rook(board, color);
            case QUEEN:
                return new Queen(board, color);
            case KING:
                return new King(board, color);
            default:
                throw new IllegalArgumentException("Invalid piece type: " + type);
        }
    }
}