package chess.engine.board;

import chess.PieceType;
import chess.PlayerColor;
import chess.engine.piece.King;
import chess.engine.piece.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.PlayerColor.BLACK;

/**
 * Chess board. Acts as the model for the application.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public class Board {
    /**
     * The list of previous full moves.
     */
    private final List<FullMove> moveHistory = new ArrayList<>();
    /**
     * The pieces on the board. The key is the position of the piece on the board (e.g. "A1") and the value is the piece.
     */
    private HashMap<Square, Piece> pieces = new HashMap<>();
    /**
     * The square on which an en passant capture is possible.
     */
    private Square enPassantSquare = null;
    /**
     * The current player.
     */
    private PlayerColor currentPlayer = PlayerColor.WHITE;
    /**
     * The current game state.
     *
     * @see GameState
     */
    private GameState gameState = GameState.NORMAL;
    /**
     * The castling rights of the players.
     *
     * @see CastlingType
     */
    private Castling castling = new Castling(true, true, true, true);
    /**
     * The number of half moves since the last capture or pawn advance.
     */
    private int halfMoveClock = 0;
    /**
     * The last half move.
     */
    private Move lastHalfMove = null;

    /**
     * The last full move.
     */
    private FullMove lastFullMove = null;

    /**
     * The number of full moves since the start of the game.
     */
    private int fullMoveCount = 1;

    /**
     * The squares that are attacked by the current player.
     */
    public HashMap<Square, Piece> pieces(PlayerColor color) {
        return pieces().entrySet().stream().filter(entry -> entry.getValue().color() == color)
                       .collect(HashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);
    }

    /**
     * Get the pieces on the board.
     *
     * @return The pieces on the board.
     */
    public HashMap<Square, Piece> pieces() {
        return pieces;
    }
    
    /**
     * Get the castling rights.
     */
    public boolean getCastlingRight(CastlingType type) {
        return castling.get(type);
    }

    /**
     * Set the castling rights.
     */
    public void setCastlingRights(CastlingType type, boolean value) {
        castling.set(type, value);
    }

    /**
     * Get the castling rights.
     *
     * @return The castling rights.
     */
    public Castling castlingRights() {
        return castling;
    }

    /**
     * Advance to the next turn by incrementing the full move count and changing the current player.
     */
    public void nextTurn() {
        incrementFullMoveCount();
        setCurrentPlayer(currentPlayer() == PlayerColor.WHITE ? BLACK : PlayerColor.WHITE);
    }

    /**
     * Increment the number of full moves since the start of the game.
     */
    public void incrementFullMoveCount() {
        fullMoveCount++;
    }

    /**
     * Set the current player.
     *
     * @param player The player to set.
     */
    public void setCurrentPlayer(PlayerColor player) {
        currentPlayer = player;
    }

    /**
     * Get the current player.
     *
     * @return The current player.
     */
    public PlayerColor currentPlayer() {
        return currentPlayer;
    }

    /**
     * Set the number of half moves since the last capture or pawn advance.
     *
     * @param value The number of half moves since the last capture or pawn advance.
     */
    public void setHalfMoveClock(int value) {
        halfMoveClock = value;
    }

    /**
     * Increment the number of half moves since the last capture or pawn advance.
     */
    public void incrementHalfMoveClock() {
        halfMoveClock++;
    }

    /**
     * Set the last half move. Also updates the full move list and increments the threefold repetition counter.
     *
     * @param move The last half move.
     */
    public void setLastHalfMove(Move move) {
        final var fullMove = new FullMove(lastHalfMove, move, castling, enPassantSquare());
        moveHistory.add(fullMove);
        setLastFullMove(fullMove);
        lastHalfMove = move;
    }

    /**
     * Get the square on which an en passant capture is possible.
     *
     * @return The square on which an en passant capture is possible.
     */
    public Square enPassantSquare() {
        return enPassantSquare;
    }

    /**
     * Set the last full move.
     *
     * @param value The last full move.
     */
    public void setLastFullMove(FullMove value) {
        lastFullMove = value;
    }

    /**
     * Set the number of full moves since the start of the game.
     *
     * @param value The number of full moves since the start of the game.
     */
    public void setFullMoveCount(int value) {
        fullMoveCount = value;
    }

    /**
     * Check if the current player is in check.
     *
     * @return True if the current player is in check, false otherwise.
     */
    public boolean isCheck() {
        return king(currentPlayer()).isInCheck();
    }

    /**
     * Get the king of a player.
     *
     * @param color The color of the player.
     * @return The king of the player.
     */
    public King king(PlayerColor color) {
        return (King) pieces().values().stream()
                              .filter(piece -> piece.type() == PieceType.KING && piece.color() == color).findFirst()
                              .orElse(null);
    }

    /**
     * Get the color of the next player.
     *
     * @return The color of the next player.
     */
    public PlayerColor nextPlayer() {
        return currentPlayer() == PlayerColor.WHITE ? BLACK : PlayerColor.WHITE;
    }

    /**
     * Check if the current player is in checkmate.
     *
     * @return True if the current player is in checkmate, false otherwise.
     */
    public boolean isCheckmate() {
        return king(currentPlayer()).isInCheckmate();
    }

    /**
     * Check if the current player is in stalemate.
     *
     * @return True if the current player is in stalemate, false otherwise.
     */
    public boolean isDraw() {
        // Some type of draws depend on a king being present and others don't.
        return (king(currentPlayer()) != null && isStalemate()) || (king(
                currentPlayer()) != null && isInsufficientMaterial()) || isThreefoldRepetition() || isFivefoldRepetition() || isFiftyMoveRule();
    }

    /**
     * Check if the current player is in stalemate.
     *
     * @return True if the current player is in stalemate, false otherwise.
     */
    private boolean isStalemate() {
        return king(currentPlayer()).isInStalemate();
    }

    /**
     * Check if the current position has been repeated three times throughout the history of the game.
     *
     * @return True if the current position has been repeated three times, false otherwise.
     */
    private boolean isThreefoldRepetition() {
        return lastFullMove != null && moveHistory.stream().filter(lastFullMove::equals).count() >= 3;
    }

    /**
     * Check if the current position has been repeated five times during the last five fullmoves.
     *
     * @return True if the current position has been repeated five times, false otherwise.
     */
    private boolean isFivefoldRepetition() {
        int size = moveHistory.size();
        if (size < 11) {
            return false;
        }

        FullMove lastMove = moveHistory.get(size - 1);
        for (int i = 2; i <= 10; i += 2) {
            if (!moveHistory.get(size - 1 - i).equals(lastMove)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Check if the fifty move rule has been reached.
     *
     * @return True if the fifty move rule has been reached, false otherwise.
     */
    private boolean isFiftyMoveRule() {
        return halfMoveClock >= 50;
    }

    /**
     * Check if the current position has insufficient material to checkmate.
     *
     * @return True if the current position has insufficient material to checkmate, false otherwise.
     */
    private boolean isInsufficientMaterial() {
        if (pieces().size() == 2 && pieces().containsValue(king(PlayerColor.WHITE)) && pieces().containsValue(
                king(BLACK))) {
            // King vs king
            return true;
        } else if (pieces().size() == 3 && pieces().containsValue(king(PlayerColor.WHITE)) && pieces().containsValue(
                king(BLACK))) {
            // King vs king and bishop
            // King vs king and knight
            return pieces().values()
                           .stream()
                           .anyMatch(piece -> piece.type() == PieceType.BISHOP) || pieces().values().stream()
                                   .anyMatch(piece -> piece.type() == PieceType.KNIGHT);
        } else if (pieces().size() == 4) {
            // King and bishop vs king and bishop with the bishops on the same color.
            if (pieces().values().stream()
                        .allMatch(piece -> piece.type() == PieceType.BISHOP || piece.type() == PieceType.KING)) {
                  HashMap<Square, Piece> bishops = pieces().entrySet().stream()
                        .filter(entry -> entry.getValue().type() == PieceType.BISHOP)
                        .collect(HashMap::new,(m, entry) -> m.put(entry.getKey(), entry.getValue()), HashMap::putAll);
                if (bishops.size() == 2) {
                    Piece bishop1 = bishops.values().stream().findFirst().orElse(null);
                    Piece bishop2 = bishops.values().stream().skip(1).findFirst().orElse(null);
                    Square bishop1Square = bishops.keySet().stream().findFirst().orElse(null);
                    Square bishop2Square = bishops.keySet().stream().skip(1).findFirst().orElse(null);

                    // Linter yells if we don't assert.
                    assert bishop2 != null;
                    assert bishop2Square != null;
                    return bishop1.color() != bishop2.color() && bishop1Square.isLight() == bishop2Square.isLight();
                }
            }
        }
        return false;
    }

    /**
     * Clone the board.
     *
     * @return The cloned board.
     */
    @Override
    public Board clone() {
        Board clone = new Board();
        clone.setPieces((HashMap<Square, Piece>) pieces().clone());
        clone.setEnPassantSquare(enPassantSquare());
        clone.setCurrentPlayer(currentPlayer());
        clone.castling = castling.clone();
        clone.halfMoveClock = halfMoveClock;
        clone.fullMoveCount = fullMoveCount;
        clone.setGameState(gameState());
        return clone;
    }

    /**
     * Set the pieces on the board.
     *
     * @param pieces The pieces on the board.
     */
    public void setPieces(HashMap<Square, Piece> pieces) {
        this.pieces = pieces;
    }

    /**
     * Set the square on which an en passant capture is possible.
     *
     * @param enPassantSquare The square on which an en passant capture is possible.
     */
    public void setEnPassantSquare(Square enPassantSquare) {
        this.enPassantSquare = enPassantSquare;
    }

    /**
     * Set the current game state.
     *
     * @param gameState The current game state.
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Get the current game state.
     *
     * @return The current game state.
     */
    public GameState gameState() {
        return gameState;
    }
}
