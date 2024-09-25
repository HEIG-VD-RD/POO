package chess.engine.board;

import chess.PlayerColor;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Square representation. The origin is in the top left corner. Squares go from A1 to H8 or from 0 to 63. X and Y
 * coordinates are 0-based.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public record Square(int x, int y) {
    /**
     * The minimum index of a square.
     */
    public final static int MIN_INDEX = 0;

    /**
     * The maximum index of a square.
     */
    public final static int MAX_INDEX = 7;

    /**
     * The width of the board.
     */
    public final static int BOARD_WIDTH = 8;

    /**
     * Canonical constructor.
     *
     * @param x The x coordinate of the square.
     * @param y The y coordinate of the square.
     */
    public Square {
        if (x < MIN_INDEX || x > MAX_INDEX || y < MIN_INDEX || y > MAX_INDEX) {
            throw new IllegalArgumentException("Invalid square coordinates: " + x + ", " + y + ".");
        }
    }

    /**
     * Constructor from a string.
     *
     * @param position The position of the square in algebraic notation.
     */
    public Square(String position) {
        this(position.toUpperCase().charAt(0) - 'A', position.charAt(1) - '1');
    }

    /**
     * Constructor from an index with 0 in the top left corner and 63 in the bottom right corner.
     *
     * @param index The index of the square.
     */
    public Square(int index) {
        this(index % BOARD_WIDTH, index / BOARD_WIDTH);
    }

    /**
     * Check if the square is a valid square on the board.
     *
     * @param x The x coordinate of the square.
     * @param y The y coordinate of the square.
     * @return True if the square is valid, false otherwise.
     */
    public static boolean isValid(int x, int y) {
        return x >= MIN_INDEX && x <= MAX_INDEX && y >= MIN_INDEX && y <= MAX_INDEX;
    }

    /**
     * Get the index of the square with 0 in the top left corner and 63 in the bottom right corner.
     *
     * @return The index of the square.
     */
    public int index() {
        return y * BOARD_WIDTH + x;
    }

    /**
     * Distance to another square. Works no matter the direction.
     *
     * @param square The other square.
     * @return The distance in squares.
     */
    public int distanceTo(Square square) {
        return Math.max(Math.abs(x - square.x), Math.abs(y - square.y));
    }

    /**
     * Check if the square is diagonal to another square.
     *
     * @param square The other square.
     * @return The distance in squares.
     */
    public boolean isDiagonalTo(Square square) {
        return Math.abs(x - square.x) == Math.abs(y - square.y);
    }

    /**
     * Check if the square is vertical to another square.
     *
     * @param square The other square.
     * @return The distance in squares.
     */
    public boolean isVerticalTo(Square square) {
        return x == square.x;
    }

    /**
     * Check if the square is horizontal to another square.
     *
     * @param square The other square.
     * @return The distance in squares.
     */
    public boolean isHorizontalTo(Square square) {
        return y == square.y;
    }

    /**
     * Check if the square is orthogonal to another square.
     *
     * @param square The other square.
     * @return The distance in squares.
     */
    public boolean isOrthogonalTo(Square square) {
        return isVerticalTo(square) || isHorizontalTo(square);
    }

    /**
     * Check if the square is aligned either orthogonally or diagonally to another square.
     *
     * @param square The other square.
     * @return The distance in squares.
     */
    public boolean isAlignedTo(Square square) {
        return isOrthogonalTo(square) || isDiagonalTo(square);
    }

    /**
     * Get the squares adjacent to this square.
     *
     * @return The list of adjacent squares.
     */
    public List<Square> neighbors() {
        return IntStream.rangeClosed(this.x - 1, this.x + 1)
                 .boxed()
                 .flatMap(x -> IntStream.rangeClosed(this.y - 1, this.y + 1)
                 .filter(y -> isValid(x, y))
                 .mapToObj(y -> new Square(x, y)))
                 .filter(square -> !square.equals(this))
                 .toList();
    }

    /**
     * All squares of the board.
     *
     * @return A list containing all squares of the board.
     */
    public static List<Square> allSquares() {
        return IntStream.rangeClosed(MIN_INDEX, MAX_INDEX)
                        .boxed()
                        .flatMap(x -> IntStream.rangeClosed(MIN_INDEX, MAX_INDEX)
                        .mapToObj(y -> new Square(x, y))).toList();
    }

    /**
     * Check if the square is light.
     *
     * @return True if the square is light, false otherwise.
     */
    public boolean isLight() {
        return (x + y) % 2 == 0;
    }

    /**
     * Check if the square is dark.
     *
     * @return True if the square is dark, false otherwise.
     */
    public boolean isDark() {
        return !isLight();
    }

    /**
     * Check if the square is in the first or last rank depending on the color.
     *
     * @param color The color of the player.
     * @return True if the square is in the first rank, false otherwise.
     */
    public boolean isLastRank(PlayerColor color) {
        var lastRank = color == PlayerColor.WHITE ? MAX_INDEX : MIN_INDEX;
        return y == lastRank;
    }

    /**
     * String representation of the square.
     *
     * @return The string representation of the square.
     */
    public String toString() {
        return String.format("%c%c", x + 'A', y + '1');
    }

    /**
     * Check if the square is equal to another square.
     *
     * @param object The other square.
     * @return True if the squares are equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Square s)) return false;
        return s.x == x && s.y == y;
    }

    /**
     * Hash code of the square.
     *
     * @return The hash code of the square.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}