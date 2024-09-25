import chess.ChessController;
import chess.ChessView;
import chess.engine.Controller;
import chess.views.gui.GUIView;

/**
 * Jeu d'échecs
 *
 * @author Rafael Dousse <rafael.dousse@heig-vd.ch>
 * @author Aubry Mangold <aubry.mangold@heig-vd.ch>
 */
public class StudentChess {
    public static void main(String[] args) {
        ChessController controller = new Controller();
        ChessView view = new GUIView(controller);
        controller.start(view);
    }
}