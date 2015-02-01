
import java.awt.Point;
import java.util.Random;

public class RandomAsteroidFactory implements IRandomAsteroidFactory {

    public static void createAsteroids(int Difficulty, ISharedData sharedData) {

        int asteroidCount = new Random().nextInt(Difficulty) + 5;
        for (int i = 0; i <= asteroidCount; ++i) {

            /* random starting location */
            int x = 0;
            int y = 0;

            while (!((x > 0 && x < sharedData.getGameWidth() * .45)
                    || (x > sharedData.getGameWidth() * .55 && x < sharedData.getGameWidth()))) {
                x = new Random().nextInt(sharedData.getGameWidth());
            }
            while (!((y > 0 && y < sharedData.getGameHeight() * .45)
                    || (y > sharedData.getGameHeight() * .55 && y < sharedData.getGameHeight()))) {
                y = new Random().nextInt(sharedData.getGameHeight());
            }

            /* create the asteroid and add it to the game data */
            getAsteroid(new Random().nextInt(3) + 1, sharedData, x, y);

        }

    }

    public static void createAsteroids(Asteroid refAsteroid, ISharedData sharedData) {

        int AsteroidType = refAsteroid.getState().getType();
        int asteroidCount = 2;

        /* start at the center of the destroyed asteroid */
        int x = Math.round(Math.round(refAsteroid.getBounds2D().getCenterX()));
        int y = Math.round(Math.round(refAsteroid.getBounds2D().getCenterY()));

        /* create the asteroids and add them to game data */
        for (int i = 0; i < asteroidCount; ++i) {

            switch (AsteroidType) {
                case IAsteroidStateFactory.LARGE_ASTEROID:
                    getAsteroid(IAsteroidStateFactory.MEDIUM_ASTEROID, sharedData, x, y);
                    break;
                case IAsteroidStateFactory.MEDIUM_ASTEROID:
                    getAsteroid(IAsteroidStateFactory.SMALL_ASTEROID, sharedData, x, y);
                    break;
                default:
                    break;
            }
        }

    }

    private static void getAsteroid(int type, ISharedData sharedData, int x, int y) {

        /* create the state */
        IAsteroidState as = AsteroidStateFactory.getAsteroidState(type);

        /* randomize trajectory */
        as.setAsteroidTrajectory(new Point(new Random().nextInt(as.getMaxSpeed()) + 1,
                new Random().nextInt(as.getMaxSpeed()) + 1));
        
        /* flip a coin to set initial direction */
        if (new Random().nextInt(10) > 5) {
            as.getAsteroidTrajectory().x = -1 * as.getAsteroidTrajectory().x;
        }
        if (new Random().nextInt(10) > 5) {
            as.getAsteroidTrajectory().y = -1 * as.getAsteroidTrajectory().y;
        }

        /* create the asteroid */
        Asteroid a = AsteroidFactory.getAsteroid(as, sharedData);

        /* set the astroid shape and location */
        int base = as.getBaseSize();
        int range = base / 4;
        int half = range * 2;
        int unit = base / 8;

        int[] xpoints = {
            x + new Random().nextInt(range) + unit,
            x + new Random().nextInt(range) + half + unit,
            x + new Random().nextInt(range) + base - unit,
            x + new Random().nextInt(range) + base - unit,
            x + new Random().nextInt(range) + half + unit,
            x + new Random().nextInt(range) + unit,
            x + new Random().nextInt(range) - unit,
            x + new Random().nextInt(range) - unit};
        int[] ypoints = {
            y + new Random().nextInt(range) - unit,
            y + new Random().nextInt(range) - unit,
            y + new Random().nextInt(range) + unit,
            y + new Random().nextInt(range) + half + unit,
            y + new Random().nextInt(range) + base - unit,
            y + new Random().nextInt(range) + base - unit,
            y + new Random().nextInt(range) + half + unit,
            y + new Random().nextInt(range) + unit};

        a.npoints = xpoints.length;
        a.xpoints = xpoints;
        a.ypoints = ypoints;

        /* Add asteroid to game data */
        a.addToSharedData();

    }

}
