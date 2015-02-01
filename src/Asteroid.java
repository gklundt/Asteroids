
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

public final class Asteroid extends Polygon implements IGameFigure {

    private final IAsteroidState state;
    private final ISharedData sharedData;

    private final ArrayList<IObserver> observers;

    private boolean remove;

    public Asteroid(IAsteroidState as, ISharedData sharedData) {

        remove = false;
        this.state = as;
        this.sharedData = sharedData;
        observers = new ArrayList<>();
        observers.add(sharedData.getScoreKeeper());
        NotifyObservers();

    }

    public IAsteroidState getState() {
        return this.state;
    }

    @Override
    public void render(Graphics2D graphics) {

        invalidate();
        graphics.setColor(state.getAsteroidColor());
        graphics.drawPolygon(this);

    }

    @Override
    public void update() {

        double dx = state.getAsteroidTrajectory().getX();
        double dy = state.getAsteroidTrajectory().getY();

        /* bounce */
        for (int i = 0; i < npoints; ++i) {
            if (xpoints[i] >= sharedData.getGameWidth() && dx > 0) {
                dx = -1 * dx;
            }
            if (xpoints[i] <= 0 && dx < 0) {
                dx = -1 * dx;
            }
            if (ypoints[i] >= sharedData.getGameHeight() && dy > 0) {
                dy = -1 * dy;
            }
            if (ypoints[i] <= 0 && dy < 0) {
                dy = -1 * dy;
            }
        }
        state.getAsteroidTrajectory().setLocation(dx, dy);

        /* move */
        for (int i = 0; i < npoints; ++i) {
            xpoints[i] += dx;
            ypoints[i] += dy;

        }

    }

    @Override
    public void setRemove(boolean b) {
        remove = b;
        if (remove) {
            NotifyObservers();
        }
    }

    @Override
    public boolean getRemove() {

        return remove;
    }

    @Override
    public void AddObserver(IObserver obj) {
        this.observers.add(obj);
    }

    @Override
    public void NotifyObservers() {
        if (this.observers != null) {
            for (IObserver o : this.observers) {
                if (o != null) {
                    o.updateObserver(this);
                }
            }
        }
    }

    @Override
    public void addToSharedData() {
        sharedData.GetGameFigures().add(this);
    }
}
