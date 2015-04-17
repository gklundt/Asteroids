
import java.awt.Polygon;


public abstract class AbstractShip extends Polygon implements IGameFigure {

    protected iShipBehavior sb;
    protected MovementVector mv;
    
    public abstract double GetMissileStartX();

    public abstract double GetMissileStartY();

    public abstract double GetMissileTrajectory();
    
    protected abstract void rotateRight();
    protected abstract void rotateLeft();
    protected abstract void thrust();
    protected abstract void slow();
}
