package chess.engine;

import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;

/**
 * Mock view for testing. Does nothing.
 */
public class MockView implements ChessView {
    private static MockView instance = null;

    private MockView() {
    }

    public static MockView getInstance() {
        if (instance == null) {
            instance = new MockView();
        }
        return instance;
    }

    @Override
    public void startView() {
    }

    @Override
    public void removePiece(int x, int y) {
    }

    @Override
    public void putPiece(PieceType type, PlayerColor color, int x, int y) {
    }

    @Override
    public void displayMessage(String msg) {
    }

    @Override
    public <T extends UserChoice> T askUser(String title, String question, T... possibilities) {
        return null;
    }
}