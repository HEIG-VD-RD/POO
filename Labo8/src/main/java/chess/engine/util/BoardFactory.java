package chess.engine.util;

import chess.PieceType;
import chess.PlayerColor;
import chess.engine.board.Board;
import chess.engine.board.Square;
import chess.engine.piece.*;

import static chess.engine.board.CastlingType.*;

/**
 * Chess board factory. Encapsulates the logic for creating a new board from a FEN string. Some default boards are
 * provided for convenience.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public class BoardFactory {
    /**
     * The FEN string for the initial piece placement.
     */
    private final static String START_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    private final static String PIECE_FEN = "rnbqkbnr/8/8/8/8/8/8/RNBQKBNR w KQkq - 0 1";
    private final static String PAWN_FEN = "8/pppppppp/8/8/8/8/PPPPPPPP/8 w KQkq - 0 1";
    private final static String EN_PASSANT_FEN = "rnbqkbnr/p1pppppp/1p6/4P3/8/8/PPPP1PPP/RNBQKBNR b KQkq f6 2 1";

    /**
     * Private constructor to prevent instantiation.
     */
    private BoardFactory() {
    }

    /**
     * Create a new board with the initial piece placement.
     *
     * @return The new board.
     */
    public static Board createInitialBoard() {
        return parseFen(START_FEN);
    }

    /**
     * Create a new board with pieces only.
     *
     * @return The new board.
     */
    public static Board createPieceOnlyBoard() {
        return parseFen(PIECE_FEN);
    }

    /**
     * Create a new board with pawns only.
     *
     * @return The new board.
     */
    public static Board createPawnOnlyBoard() {
        return parseFen(PAWN_FEN);
    }

    /**
     * Create a new board with an en passant square.
     *
     * @return The new board.
     */
    public static Board createEnPassantBoard() {
        return parseFen(EN_PASSANT_FEN);
    }

    /**
     * Create a new board from a FEN string.
     *
     * @param fen
     * @return
     */
    public static Board createFromFen(String fen) {
        return parseFen(fen);
    }

    /**
     * Create a new board populated with the specified FEN placement and statuses.
     *
     * @param fen The FEN string to parse
     * @return A board setup according to the FEN string.
     */
    private static Board parseFen(String fen) {
        Board board = new Board();
        parsePiecePlacement(board, fen.split(" ")[0]);
        parseGameStatus(board, fen.substring(fen.indexOf(" ") + 1));
        return board;
    }

    /**
     * Parse the piece placement from a FEN string.
     *
     * @param placement The piece placement part of the FEN string.
     */
    private static void parsePiecePlacement(Board board, String placement) {
        String[] rows = placement.split("/");
        if (rows.length != 8) {
            throw new IllegalArgumentException("Invalid FEN: Piece placement does not contain 8 rows.");
        }

        // Parse the piece positions
        for (int i = 0; i < 8; i++) {
            String row = rows[7 - i]; // FEN starts from rank 8
            int col = 0;
            for (char c : row.toCharArray()) {
                if (Character.isDigit(c)) {
                    col += Character.getNumericValue(c);
                } else {
                    board.pieces().put(new Square(col, i), createPiece(board, getPieceType(c), getPieceColor(c)));
                    col++;
                }
            }
        }
    }

    /**
     * Parse the game status indicators from a FEN string.
     *
     * @param status The game status part of the FEN string.
     */
    private static void parseGameStatus(Board board, String status) {
        String[] fields = status.split(" ");
        board.setCurrentPlayer(fields[0].equals("w") ? PlayerColor.WHITE : PlayerColor.BLACK);
        board.setCastlingRights(WHITE_KINGSIDE, fields[1].contains("K"));
        board.setCastlingRights(WHITE_QUEENSIDE, fields[1].contains("Q"));
        board.setCastlingRights(BLACK_KINGSIDE, fields[1].contains("k"));
        board.setCastlingRights(BLACK_QUEENSIDE, fields[1].contains("q"));
        board.setEnPassantSquare(fields[2].equals("-") ? null : new Square(fields[2]));
        board.setHalfMoveClock(Integer.parseInt(fields[3]));
        board.setFullMoveCount(Integer.parseInt(fields[4]));
    }

    private static Piece createPiece(Board board, PieceType type, PlayerColor color) {
        return switch (type) {
            case PAWN -> new Pawn(board, color);
            case ROOK -> new Rook(board, color);
            case KNIGHT -> new Knight(board, color);
            case BISHOP -> new Bishop(board, color);
            case QUEEN -> new Queen(board, color);
            case KING -> new King(board, color);
        };
    }

    /**
     * Get the piece type from a FEN character.
     *
     * @param c The FEN character.
     * @return The piece type.
     */
    private static PieceType getPieceType(char c) {
        return switch (Character.toLowerCase(c)) {
            case 'p' -> PieceType.PAWN;
            case 'r' -> PieceType.ROOK;
            case 'n' -> PieceType.KNIGHT;
            case 'b' -> PieceType.BISHOP;
            case 'q' -> PieceType.QUEEN;
            case 'k' -> PieceType.KING;
            default -> throw new IllegalArgumentException("Invalid piece type: " + c);
        };
    }

    /**
     * Get the piece color from a FEN character.
     *
     * @param c The FEN character.
     * @return The piece color.
     */
    private static PlayerColor getPieceColor(char c) {
        return Character.isLowerCase(c) ? PlayerColor.BLACK : PlayerColor.WHITE;
    }
}
