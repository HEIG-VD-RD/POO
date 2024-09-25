package chess.engine.util;

import chess.ChessView;
import chess.PieceType;

/**
 * Promotion choice class.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public class PromotionChoice implements ChessView.UserChoice {
    /**
     * The queen promotion choice.
     */
    private static final PromotionChoice QUEEN_PROMOTION = new PromotionChoice("Queen", PieceType.QUEEN);

    /**
     * The rook promotion choice.
     */
    private static final PromotionChoice ROOK_PROMOTION = new PromotionChoice("Rook", PieceType.ROOK);

    /**
     * The bishop promotion choice.
     */
    private static final PromotionChoice BISHOP_PROMOTION = new PromotionChoice("Bishop", PieceType.BISHOP);

    /**
     * The knight promotion choice.
     */
    private static final PromotionChoice KNIGHT_PROMOTION = new PromotionChoice("Knight", PieceType.KNIGHT);

    /**
     * All promotion choices.
     */
    public static final PromotionChoice[] PROMOTION_CHOICES = new PromotionChoice[]{QUEEN_PROMOTION, ROOK_PROMOTION, BISHOP_PROMOTION, KNIGHT_PROMOTION,};

    /**
     * The text value of the promotion prompt.
     */
    private final String text;

    /**
     * The piece type of the promotion choice.
     */
    private final PieceType pieceType;

    /**
     * Constructor.
     *
     * @param text      The text value of the promotion prompt.
     * @param pieceType The piece type of the promotion choice.
     */
    public PromotionChoice(String text, PieceType pieceType) {
        this.text = text;
        this.pieceType = pieceType;
    }

    /**
     * Get the text value of the promotion prompt.
     *
     * @return The text value of the promotion prompt.
     */
    @Override
    public String textValue() {
        return text;
    }

    /**
     * Get the piece type of the promotion choice.
     *
     * @return The piece type of the promotion choice.
     */
    public PieceType getPieceType() {
        return pieceType;
    }

    /**
     * Get the string representation of the promotion choice.
     * @return
     */
    @Override
    public String toString() {
        return text;
    }
}