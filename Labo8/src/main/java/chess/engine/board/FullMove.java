package chess.engine.board;

import java.util.Objects;

/**
 * A full move is a pair of moves, one for each player. Used to detect threefold repetition.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public record FullMove(Move firstHalfMove, Move secondHalfMove, Castling castling, Square enPassantSquare) {

    /**
     * Equality check.
     *
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FullMove fullMove = (FullMove) obj;
        return Objects.equals(firstHalfMove, fullMove.firstHalfMove)
               && Objects.equals(secondHalfMove, fullMove.secondHalfMove)
               && Objects.equals(castling, fullMove.castling)
               && Objects.equals(enPassantSquare, fullMove.enPassantSquare);
    }
}
