package chess.engine.board;

import java.util.List;

/**
 * Directions on the chessboard.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public enum Direction {
    UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT;

    /**
     * Get the straight directions.
     *
     * @return A list of the straight directions.
     */
    public static List<Direction> straight() {
        return List.of(new Direction[]{UP, DOWN, LEFT, RIGHT});
    }

    /**
     * Get the diagonal directions.
     *
     * @return A list of the diagonal directions.
     */
    public static List<Direction> diagonal() {
        return List.of(new Direction[]{UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT});
    }
}
