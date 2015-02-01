
public class AsteroidStateFactory implements IAsteroidStateFactory {

    public static IAsteroidState getAsteroidState(int stateType) {
        switch (stateType) {
            case LARGE_ASTEROID:
                return new AsteroidStateLarge();
            case MEDIUM_ASTEROID:
                return new AsteroidStateMedium();
            case SMALL_ASTEROID:
                return new AsteroidStateSmall();
            default:
                return null;
        }
    }

}
