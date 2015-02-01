
public class AsteroidFactory implements IAsteroidFactory {

    public static Asteroid getAsteroid(IAsteroidState asteroidState, ISharedData sharedData) {
        return new Asteroid(asteroidState, sharedData);
    }

}
