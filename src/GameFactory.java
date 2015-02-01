
public class GameFactory implements IGameFactory {

    public static AbstractGamePanel GetGamePanel(ISharedData sharedData, int panelType) {
        switch (panelType) {
            case ASTEROIDS:
                return new GamePanel(sharedData);
            case SPLASHSCREEN:
                return new SplashPanel(sharedData);
            default:
                return null;
        }
    }

}
