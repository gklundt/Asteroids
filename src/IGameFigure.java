
import java.awt.Graphics2D;
import java.awt.Shape;

public interface IGameFigure extends Shape, IObservable {

    void setRemove(boolean b);

    boolean getRemove();

    void render(Graphics2D graphics);

    void update();
    
    void addToSharedData();

}
