
public class SharedDataFactory implements ISharedDataFactory {

    public static ISharedData GetSharedData(int sharedDataType) {
        switch (sharedDataType) {
            case ASTEROIDS:
                return new GameSharedData();
            case SPLASHSCREEN:
                return new SplashSharedData();
            default:
                return null;
        }
    }

}
