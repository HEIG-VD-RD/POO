package chess.engine.board;

/**
 * Castling rights model.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public class Castling {
    /**
     * Castling rights white kingside.
     */
    private boolean whiteKingSide;

    /**
     * Castling rights white queenside.
     */
    private boolean whiteQueenSide;

    /**
     * Castling rights black kingside.
     */
    private boolean blackKingSide;

    /**
     * Castling rights black queenside.
     */
    private boolean blackQueenSide;

    /**
     * Default constructor.
     */
    public Castling() {
        this(false, false, false, false);
    }

    /**
     * Constructor.
     *
     * @param whiteKingSide  Castling rights white kingside.
     * @param whiteQueenSide Castling rights white queenside.
     * @param blackKingSide  Castling rights black kingside.
     * @param blackQueenSide Castling rights black queenside.
     */
    public Castling(boolean whiteKingSide, boolean whiteQueenSide, boolean blackKingSide,
                    boolean blackQueenSide) {
        this.whiteKingSide = whiteKingSide;
        this.whiteQueenSide = whiteQueenSide;
        this.blackKingSide = blackKingSide;
        this.blackQueenSide = blackQueenSide;
    }

    /**
     * Set the castling rights for a given type.
     *
     * @param type  The type of castling.
     * @param value The value of the castling.
     * @see CastlingType
     */
    public void set(CastlingType type, boolean value) {
        switch (type) {
            case WHITE_KINGSIDE -> whiteKingSide = value;
            case WHITE_QUEENSIDE -> whiteQueenSide = value;
            case BLACK_KINGSIDE -> blackKingSide = value;
            case BLACK_QUEENSIDE -> blackQueenSide = value;
        }
    }

    /**
     * Get the castling rights for a given type.
     *
     * @param type The type of castling.
     * @return The value of the castling.
     * @see CastlingType
     */
    public boolean get(CastlingType type) {
        return switch (type) {
            case WHITE_KINGSIDE -> whiteKingSide;
            case WHITE_QUEENSIDE -> whiteQueenSide;
            case BLACK_KINGSIDE -> blackKingSide;
            case BLACK_QUEENSIDE -> blackQueenSide;
        };
    }

    /**
     * Unset a castling right.
     *
     * @param type The type of castling to unset.
     */
    public void remove(CastlingType type) {
        set(type, false);
    }

    /**
     * Castling rights hash code.
     *
     * @return The hash code of the castling rights.
     */
    @Override
    public int hashCode() {
        return (whiteKingSide ? 1 : 0) + (whiteQueenSide ? 2 : 0) + (blackKingSide ? 4 : 0) + (blackQueenSide ? 8 : 0);
    }

    /**
     * Castling rights equality.
     *
     * @param obj The other castling rights.
     * @return True if the castling rights are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Castling other)) {
            return false;
        }
        return whiteKingSide == other.whiteKingSide
               && whiteQueenSide == other.whiteQueenSide
               && blackKingSide == other.blackKingSide
               && blackQueenSide == other.blackQueenSide;
    }

    /**
     * Castling rights clone.
     *
     * @return The cloned castling rights.
     */
    @Override
    public Castling clone() {
        return new Castling(whiteKingSide, whiteQueenSide, blackKingSide, blackQueenSide);
    }

    /**
     * Check if a move is a castling move.
     *
     * @param move The move to check.
     * @return True if the move is a castling move, false otherwise.
     */
    public static boolean isCastlingMove(Move move) {
        // Iterate on castling types
        for (var castlingType : CastlingType.values()) {
            // Check if the move is a castling move
            if (move.from().equals(castlingType.endSquares().get(0)) && move.to().equals(castlingType.endSquares().get(1))) {
                return true;
            }
        }
        return false;
    }
}
