package chess.engine.board;

import java.util.ArrayList;
import java.util.List;

/**
 * Move result model. Used to return the validity of the move and the side effects that should be applied to the
 * game model after a move.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public class MoveResult {
    /**
     * Whether the move is valid.
     */
    private boolean isValid;

    /**
     * The square where an en passant capture is possible.
     */
    private Square enPassantSquare;

    /**
     * The castling rights that are lost.
     */
    private final List<CastlingType> lostCastlingTypes;

    /**
     * The pieces that are removed.
     */
    private final List<Square> removedPieces;

    /**
     * The pieces that are moved.
     */
    private final List <Move> movedPieces;

    /**
     * Whether the half move clock should be reset.
     */
    private boolean mustResetHalfMoveClock;

    /**
     * Whether the move lead to a promotion.
     */
    private boolean isPromotion;

    /**
     * Default constructor.
     */
    public MoveResult() {
        this(false);
    }

    /**
     * Constructor.
     *
     * @param isValid Whether the move is valid.
     */
    public MoveResult(boolean isValid) {
        this.isValid = isValid;
        this.enPassantSquare = null;
        this.isPromotion = false;
        this.lostCastlingTypes = new ArrayList<>();
        this.removedPieces = new ArrayList<>();
        this.movedPieces = new ArrayList<>();
    }

    /**
     * Whether the move is valid.
     *
     * @return True if the move is valid, false otherwise.
     */
    public boolean isValid() {
        return isValid;
    }

    /**
     * Set the validity of the move.
     *
     * @param isValid Whether the move is valid.
     */
    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    /**
     * The square where an en passant capture is possible.
     *
     * @return The square where an en passant capture is possible.
     */
    public Square enPassantSquare() {
        return enPassantSquare;
    }

    /**
     * Set the square where an en passant capture is possible.
     *
     * @param enPassantSquare The square where an en passant capture is possible.
     */
    public void setEnPassantSquare(Square enPassantSquare) {
        this.enPassantSquare = enPassantSquare;
    }

    /**
     * Get the castling rights that are lost.
     *
     * @return A list of castling rights that are lost.
     */
    public List<CastlingType> lostCastlingRights() {
        return lostCastlingTypes;
    }

    /**
     * Add a lost castling right.
     *
     * @param right The castling right that is lost.
     */
    public void addLostCastlingRight(CastlingType right) {
        if (!this.lostCastlingTypes.contains(right)) {
            this.lostCastlingTypes.add(right);
        }
    }

    /**
     * Get the pieces that are removed.
     *
     * @return A list of squares representing the removed pieces.
     */
    public List<Square> removedPieces() {
        return removedPieces;
    }

    /**
     * Add a removed piece.
     *
     * @param removedPiece The removed piece.
     */
    public void addRemovedPieces(Square removedPiece) {
        if (!this.removedPieces.contains(removedPiece)) {
            this.removedPieces.add(removedPiece);
        }
    }

    /**
     * Add a piece that must be removed.
     */
    public void addMovedPiece(Move move) {
        if (!this.movedPieces.contains(move)) {
            this.movedPieces.add(move);
        }
    }

    /**
     * Get the pieces that are moved.
     *
     * @return A list of moves representing the moved pieces.
     */
    public List<Move> movedPieces() {
        return movedPieces;
    }

    /**
     * Set the half move clock reset boolean.
     */
    public void setResetHalfMoveClock() {
        this.mustResetHalfMoveClock = true;
    }

    /**
     * Whether the half move clock should be reset.
     *
     * @return True if the half move clock should be reset, false otherwise.
     */
    public boolean resetHalfMoveClock() {
        return mustResetHalfMoveClock;
    }

    /**
     * Whether the move lead to a promotion.
     *
     * @return True if the move lead to a promotion, false otherwise.
     */
    public boolean isPromotion() {
        return isPromotion;
    }

    /**
     * Set the promotion boolean to true.
     */
    public void setPromotion() {
        this.isPromotion = true;
    }
}