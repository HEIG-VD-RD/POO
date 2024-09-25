package chess.engine.board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * A ply is a pair of squares, one for the origin and one for the destination. Also known as a half move.
 *
 * @param from The square from which the piece is moved.
 * @param to   The square to which the piece is moved.
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public record Move(Square from, Square to) {
    /**
     * Canonical constructor.
     *
     * @param from The square from which the piece is moved.
     * @param to   The square to which the piece is moved.
     */
    public Move {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Square cannot be null");
        }
    }

    /**
     * New move from short algebraic notation.
     *
     * @param algebraicNotation The algebraic notation of the move.
     */
    public Move(String algebraicNotation) {
        this(new Square(algebraicNotation.substring(0, 2)), new Square(algebraicNotation.substring(2, 4)));
    }

    /**
     * The algebraic notation of the move.
     */
    public String algebraicNotation() {
        return String.format("%c%c", from.x() + 'a', from.y() + '1');
    }

    /**
     * The long algebraic notation of the move.
     */
    public String longAlgebraicNotation() {
        return String.format("%c%c%c%c", from.x() + 'a', from.y() + '1', to.x() + 'a', to.y() + '1');
    }

    /**
     * The squares from the origin to the destination of the move.

     * @return A list of squares representing the path.
     */
    public List<Square> path() {
        List<Square> path = new ArrayList<>();
        int dx = Integer.compare(to.x(), from.x());
        int dy = Integer.compare(to.y(), from.y());

        for (int x = from.x(), y = from.y(); x != to.x() || y != to.y(); x += dx, y += dy) {
            if (Square.isValid(x, y)) {
                path.add(new Square(x, y));
            }
        }

        return path;
    }

    /**
     * The squares between the origin and the destination of the move.
     *
     * @return A list of squares representing the inner path.
     */
    public List<Square> innerPath() {
        List<Square> path = new ArrayList<>();
        int dx = Integer.compare(to.x(), from.x());
        int dy = Integer.compare(to.y(), from.y());

        // Iterate on dx and dy at once.
        for (int x = from.x() + dx, y = from.y() + dy; (x != to.x() || y != to.y()) && Square.isValid(x,y); x += dx, y += dy) {
            if (Square.isValid(x, y)) {
                path.add(new Square(x, y));
            }
        }

        return path;
    }

    /**
     * Project the path from after the destination square to the end of the board.
     *
     * @return A list of squares representing the path.
     */
    public List<Square> projectedPath() {
        int dx = Integer.compare(to.x(), from.x());
        int dy = Integer.compare(to.y(), from.y());

        return IntStream.rangeClosed(Square.MIN_INDEX, Square.MAX_INDEX)
                 .boxed() // Box the int to use mapToObj
                 .flatMap(x -> IntStream.rangeClosed(0, 7)
                 .filter(y -> x + dx >= Square.MIN_INDEX
                           && x + dx < Square.BOARD_WIDTH
                           && y + dy >= Square.MIN_INDEX
                           && y + dy < Square.BOARD_WIDTH)
                 .mapToObj(y -> new Square(x + dx, y + dy)))
                 .toList();
    }

    /**
     * The direction of the move.
     *
     * @return The direction of the move.
     */
    public Direction direction() {
        // Check if the move is aligned horizontally, vertically or diagonally
        if (to().isDiagonalTo(from())) {
            return to().x() > from().x()
                    ? to().y() > from().y()
                    ? Direction.UP_RIGHT
                    : Direction.DOWN_RIGHT
                    : to().y() > from().y()
                    ? Direction.UP_LEFT
                    : Direction.DOWN_LEFT;
        } else if (to().isOrthogonalTo(from())) {
            return to().x() > from().x()
                    ? Direction.RIGHT
                    : to().x() < from().x()
                    ? Direction.LEFT
                    : to().y() > from().y()
                    ? Direction.UP : Direction.DOWN;
        } else {
            return null;
        }
    }

    /**
     * The distance between the origin and the destination of the move.
     *
     * @return The distance in squares.
     */
    public int distance() {
        return from().distanceTo(to());
    }

    /**
     * Display the move in algebraic notation.
     *
     * @return The move in algebraic notation.
     */
    @Override
    public String toString() {
        return longAlgebraicNotation();
    }
}
