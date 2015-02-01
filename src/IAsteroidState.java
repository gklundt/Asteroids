
import java.awt.Color;
import java.awt.Point;

public interface IAsteroidState {
    int getType();
    Point getAsteroidTrajectory();
    Color getAsteroidColor();
    int getMaxSpeed();
    int getBaseSize();
    void setAsteroidTrajectory(Point asteroidTrajectory);
}
