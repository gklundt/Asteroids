
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

public final class Missile extends Ellipse2D.Double implements IGameFigure {

    private final ISharedData sharedData;
    private final IMissileData missileData;
    
    private final int missileTrajectory;
    private final int missileSpeed;
    private boolean remove;

    private final ArrayList<IObserver> observers;

    public Missile(IMissileData missileData, ISharedData sharedData) {
        this.sharedData = sharedData;
        this.missileData = missileData;
        this.remove = false;
        this.x = missileData.getMissileStartX();
        this.y = missileData.getMissileStartY();
        this.width = missileData.getMissileSize();
        this.height = missileData.getMissileSize();
        this.missileSpeed = this.missileData.getMissileSpeed();
        this.missileTrajectory = this.missileData.getMissileTrajectory();
        observers = new ArrayList<>();
        this.AddObserver(this.sharedData.getScoreKeeper());
        this.NotifyObservers();
    }

    @Override
    public void render(Graphics2D graphics) {

        /* Draw */
        graphics.setColor(new Color(new Random().nextInt(176) + 80,
                new Random().nextInt(176) + 80, new Random().nextInt(176) + 80));
        graphics.draw(this);

    }

    @Override
    public void update() {

        /* Collision Detection */
        for (IGameFigure s : this.sharedData.GetGameFigures()) {
            if (s != this && s.intersects(this.getBounds2D())) {
                s.setRemove(true);
                this.setRemove(true);
                this.NotifyObservers();
            }
        }

        this.x -= (Math.cos(Math.toRadians(missileTrajectory)) * missileSpeed);
        this.y -= (Math.sin(Math.toRadians(missileTrajectory)) * missileSpeed);

        if (this.x > this.sharedData.getGameWidth()
                || this.y > this.sharedData.getGameHeight() || this.x < 0
                || this.y < 0) {
            this.setRemove(true);
        }

    }

    @Override
    public void setRemove(boolean b) {
        remove = b;

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
        for (IObserver o : this.observers) {
            o.updateObserver(this);
        }
    }

    @Override
    public void addToSharedData() {
        sharedData.GetGameFigures().add(this);
    }

}
