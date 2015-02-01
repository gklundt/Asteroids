
import java.awt.Color;
import java.awt.Point;

public class AsteroidStateSmall implements IAsteroidState {

    private final int MAXSPEED = 8;
    private final int BASE_ASTEROID_SIZE = 25;
    private final int COLOR = 0xdeb887;
    private final Color AsteroidColor;
    private final int Type;

    private Point AsteroidTrajectory;

    public AsteroidStateSmall() {
        this.Type = AsteroidStateFactory.SMALL_ASTEROID;
        this.AsteroidColor = new Color(COLOR);
    }

    @Override
    public int getType() {
        return Type;
    }

    @Override
    public Point getAsteroidTrajectory() {
        return AsteroidTrajectory;
    }

    @Override
    public void setAsteroidTrajectory(Point asteroidTrajectory) {
        AsteroidTrajectory = asteroidTrajectory;
    }

    @Override
    public Color getAsteroidColor() {
        return AsteroidColor;
    }

    @Override
    public int getMaxSpeed() {
        return MAXSPEED;
    }

    @Override
    public int getBaseSize() {
        return BASE_ASTEROID_SIZE;
    }

}
