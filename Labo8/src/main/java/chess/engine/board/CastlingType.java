package chess.engine.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Castling helper enum.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public enum CastlingType {
    /**
     * White kingside castling and involved squares.
     */
    WHITE_KINGSIDE(Arrays.asList(new Square("E1"), new Square("F1"), new Square("G1"), new Square("H1"))),
    /**
     * White queenside castling and involved squares.
     */
    WHITE_QUEENSIDE(Arrays.asList(new Square("E1"), new Square("D1"), new Square("C1"), new Square("A1"))),
    /**
     * Black kingside castling and involved squares.
     */
    BLACK_KINGSIDE(Arrays.asList(new Square("E8"), new Square("F8"), new Square("G8"), new Square("H8"))),
    /**
     * Black queenside castling and involved squares.
     */
    BLACK_QUEENSIDE(Arrays.asList(new Square("E8"), new Square("D8"), new Square("C8"), new Square("A8")));

    /**
     * The squares of the castling.
     */
    private final List<Square> squares;

    /**
     * Constructor.
     *
     * @param squares The squares involved in the castling.
     */
    CastlingType(List<Square> squares) {
        this.squares = new ArrayList<>(squares);
    }

    /**
     * Get the squares involved in the castling.
     *
     * @return The squares involved in the castling.
     */
    public List<Square> squares() {
        return squares;
    }

    /**
     * Get the end squares of the castling.
     *
     * @return The end squares of the castling, with the king's square first.
     */
    public List<Square> endSquares() {
        return Arrays.asList(squares.get(0), squares.get(2));
    }

    /**
     * Get the inner squares of the castling.
     *
     * @return The inner squares of the castling.
     */
    public List<Square> innerSquares() {
        return squares.subList(1, squares.size() - 1);
    }

    /**
     * Get the castling type of move.
     *
     * @param move The move to find the castling type of.
     * @return The castling type of the move, null if it is not a castling move.
     */
    public static CastlingType valueOf(Move move) {
        if (move.from().equals(WHITE_KINGSIDE.endSquares().get(0)) && move.to().equals(WHITE_KINGSIDE.endSquares().get(1))) {
            return WHITE_KINGSIDE;
        } else if (move.from().equals(WHITE_QUEENSIDE.endSquares().get(0)) && move.to().equals(WHITE_QUEENSIDE.endSquares().get(1))) {
            return WHITE_QUEENSIDE;
        } else if (move.from().equals(BLACK_KINGSIDE.endSquares().get(0)) && move.to().equals(BLACK_KINGSIDE.endSquares().get(1))) {
            return BLACK_KINGSIDE;
        } else if (move.from().equals(BLACK_QUEENSIDE.endSquares().get(0)) && move.to().equals(BLACK_QUEENSIDE.endSquares().get(1))) {
            return BLACK_QUEENSIDE;
        } else {
            return null;
        }
    }
}