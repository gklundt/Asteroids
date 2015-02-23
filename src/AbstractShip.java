
import java.awt.Polygon;


public abstract class AbstractShip extends Polygon implements IGameFigure {

    protected iShipBehavior sb;
    protected MovementVector mv;
    
    public abstract double GetMissileStartX();

    public abstract double GetMissileStartY();

    public abstract int GetMissileTrajectory();
}
