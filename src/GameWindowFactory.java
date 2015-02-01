
public class GameWindowFactory implements IGameWindowFactory {

    public static AbstractGameWindow GetGameWindow(int gameWindowType) {
        switch (gameWindowType) {
            case ASTEROIDS:
                AbstractGameWindow g = new GameWindow();
                g.addListeners();
                return g;
            default:
                return null;
        }
    }
}
