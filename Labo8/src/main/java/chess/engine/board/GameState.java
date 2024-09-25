package chess.engine.board;

/**
 * State of the game.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public enum GameState {
    /**
     * Normal.
     */
    NORMAL,
    /**
     * Check.
     */
    CHECK,
    /**
     * Checkmate.
     */
    CHECKMATE,
    /**
     * Draw by stalemate, threefold repetition, fivefold repetition,  50 moves rule or insufficient material.
     */
    DRAW
}
