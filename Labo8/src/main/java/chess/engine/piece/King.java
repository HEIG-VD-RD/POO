package chess.engine.piece;

import chess.PlayerColor;
import chess.engine.board.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import static chess.PieceType.*;
import static chess.PlayerColor.*;
import static chess.engine.board.CastlingType.*;
import static chess.engine.board.CastlingType.BLACK_KINGSIDE;

/**
 * King piece.
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public class King extends Piece {
    /**
     * Constructor.
     *
     * @param board The board the king belongs to.
     * @param color The color of the king.
     */
    public King(Board board, PlayerColor color) {
        super(board, KING, color);
    }

    /**
     * Checks if the king can move to the given square without considering the other pieces.
     *
     * @param move The move to be checked.
     * @return True if the king can move to the given square, false otherwise.
     */
    @Override
    public boolean pseudoMove(Move move) {
        return move.distance() == 1;
    }

    /**
     * Checks if the king can move to the given square.
     *
     * @param move The move to be checked.
     * @return True if the king can move to the given square, false otherwise.
     */
    @Override
    public MoveResult move(Move move) {
        MoveResult result = new MoveResult();
        if (color() == WHITE) {
            result.addLostCastlingRight(WHITE_QUEENSIDE);
            result.addLostCastlingRight(WHITE_KINGSIDE);
        } else {
            result.addLostCastlingRight(BLACK_QUEENSIDE);
            result.addLostCastlingRight(BLACK_KINGSIDE);
        }

        if (isCastling(move)) {
            result.setValid(true);
            CastlingType castlingType = CastlingType.valueOf(move);
            assert (castlingType != null); // We know there is a type because isCastling checked for it.
            var rookFrom = castlingType.squares().getLast();
            var rookTo = castlingType.squares().get(1);
            var rookMove = new Move(rookFrom, rookTo);
            result.addMovedPiece(rookMove);

            // Remove all the castling rights
            result.addLostCastlingRight(WHITE_QUEENSIDE);
            result.addLostCastlingRight(WHITE_KINGSIDE);
            result.addLostCastlingRight(BLACK_QUEENSIDE);
            result.addLostCastlingRight(BLACK_KINGSIDE);

            return result;
        }

        boolean legal = super.move(move).isValid()
                && pseudoMove(move)
                && !squareUnderAttack(move.to())
                && !hasAllyOn(move.to());

        result.setValid(legal);
        return result;
    }

    /**
     * Verifies if the king can castle on a given side.
     *
     * @param move The move to be checked.
     * @return True if the king can castle on the given side, false otherwise.
     */
    public boolean isCastling(Move move) {
        if (!Castling.isCastlingMove(move)) {
            return false;
        }

        var side = CastlingType.valueOf(move);
        return side != null && board().getCastlingRight(side)
            && side.squares().stream().noneMatch(this::squareUnderAttack)
            && side.innerSquares().stream().allMatch(this::squareFree);
    }

    /**
     * Checks if the king is in check.
     *
     * @return True if the king is in check, false otherwise.
     */
    public boolean isInCheck() {
        return !attackers().isEmpty();
    }

    /**
     * Checks if the king is in checkmate.
     *
     * @return True if the king is in checkmate, false otherwise.
     */
    public boolean isInCheckmate() {
        return !attackers().isEmpty() && (isStuck() || isSurrounded()) && !canDefend();
    }

    /**
     * Checks if the king can move to any of its neighboring squares.
     *
     * @return True if the king is stuck, false otherwise.
     */
    public boolean isStuck() {
        for (var square : position().neighbors()) {
            if (!squareUnderAttack(square)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the king is surrounded.
     *
     * @return True if the king is surrounded, false otherwise.
     */
    public boolean isSurrounded() {
        for (var square : position().neighbors()) {
            if (squareFree(square)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Possible attackers of the king.
     *
     * @return A map of the pieces that can attack the king.
     */
    public HashMap<Square, Piece> attackers() {
        HashMap<Square, Piece> attackers = new HashMap<>();
        for (var piece : board().pieces(board().nextPlayer()).entrySet()) {
            if (piece.getValue().pseudoMove(new Move(piece.getKey(), position()))) {
                attackers.put(piece.getKey(), piece.getValue());
            }
        }
        return attackers;
    }

    /**
     * Verify whether any ally can move.
     *
     * @return True if any ally can move, false otherwise.
     */
    public boolean noAllyCanMove() {
        for (var pieceEntry : board().pieces(color()).entrySet()) {
            Square currentSquare = pieceEntry.getKey();
            Piece currentPiece = pieceEntry.getValue();
            for (var targetSquare : Square.allSquares()) {
                Move potentialMove = new Move(currentSquare, targetSquare);
                if (currentPiece.move(potentialMove).isValid()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Possible defenders of the king.
     *
     * @return A map of the pieces that can defend the king.
     */
    public ArrayList<Move> defendingMoves() {
        var defenders = new ArrayList<Move>();
        for (var attacker : attackers().entrySet()) {
            // Check if we can intercept the attack.
            for (var square : new Move(attacker.getKey(), position()).innerPath()) {
                for (var defender : board().pieces(color()).entrySet().stream()
                                           .filter(p -> p.getValue().type() != KING)
                                           .collect(Collectors.toSet())) {
                    Move potentialMove = new Move(defender.getKey(), square);
                    if (!potentialMove.from().equals(potentialMove.to()) && defender.getValue().pseudoMove(potentialMove)) {
                        defenders.add(potentialMove);
                    }
                }
            }
            // Check if we can capture the attacker.
            for (var defender : board().pieces(color()).entrySet()) {
                Move potentialMove = new Move(defender.getKey(), attacker.getKey());
                if (!potentialMove.from().equals(potentialMove.to()) && defender.getValue().pseudoMove(potentialMove)) {
                    // Check if the attacker is defended.
                    if (board().pieces(attacker.getValue().color()).entrySet().stream()
                            .anyMatch(p -> p.getValue().pseudoMove(new Move(p.getKey(), attacker.getKey())))) {
                        continue;
                    }
                    defenders.add(potentialMove);
                }
            }
        }

        return defenders;
    }

    /**
     * Verify whether a given square is under attack.
     *
     * @param square The square to be checked.
     * @return True if the square is under attack, false otherwise.
     */
    public boolean squareUnderAttack(Square square) {
        var opponentColor = color() == WHITE ? BLACK : WHITE;
        var opponents = board().pieces(opponentColor).entrySet();
        for (var piece : opponents) {
            var move = new Move(piece.getKey(), square);
            if (piece.getValue().type() == PAWN) {
                // We need to consider that a pawn attacks diagonally
                if (move.from().isDiagonalTo(square) && move.distance() == 1) {
                    return true;
                }
            } else if (piece.getValue().pseudoMove(move)) {
                // Check that there is no piece on the inner path besides the king
                if (move.innerPath().stream()
                        .anyMatch(s -> board().pieces().get(s) != null && board().pieces().get(s).type() != KING)) {
                    continue;
                }

                // If any opponent can move to the square, it is under attack, unless it is a pawn that is simply moving forward.
                if (piece.getValue().type() == PAWN && move.to().x() == piece.getKey().x()) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Verify whether a given square is free.
     *
     * @param square The square to be checked.
     * @return True if the square is free, false otherwise.
     */
    public boolean squareFree(Square square) {
        return board().pieces().get(square) == null;
    }

    /**
     * Verify whether the king can defend itself.
     *
     * @return True if the king can defend itself, false otherwise.
     */
    public boolean canDefend() {
        if (attackers().size() > 1) {
            return false;
        }

        return !defendingMoves().isEmpty();
    }

    /**
     * Checks if the king is in stalemate.
     *
     * @return True if the king is in stalemate, false otherwise.
     */
    public boolean isInStalemate() {
        return !isInCheck() && isStuck() && noAllyCanMove();
    }
}
