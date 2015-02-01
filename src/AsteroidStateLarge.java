
import java.awt.Color;
import java.awt.Point;

public class AsteroidStateLarge implements IAsteroidState {

    private final int MAXSPEED = 4;
    private final int BASE_ASTEROID_SIZE = 80;
    private final int COLOR = 0xffd700;
    private final Color AsteroidColor;
    private final int Type;

    private Point AsteroidTrajectory;

    public AsteroidStateLarge() {
        this.Type = AsteroidStateFactory.LARGE_ASTEROID;
        this.AsteroidColor = new Color(COLOR);
    }

    ;
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
