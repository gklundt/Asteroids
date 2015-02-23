
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Ship extends AbstractShip {


    
    
    private final int NOSECONE = 45;
    private final int SHIPLENGTH = 10;
    private final int NOSEOFFSET = 15;

    private final Color SHIPCOLOR;

    private final Point nose, left, right;

    private final ArrayList<IObserver> observers;

    private boolean remove;
    private final ISharedData sharedData;

    private double MissileStartX;
    private double MissileStartY;
    private int MissileTrajectory;
    
    public Ship(ISharedData sharedData) {
        this.observers = new ArrayList<>();
        this.sharedData = sharedData;

        SHIPCOLOR = new Color(0xffa500);
        nose = new Point(this.sharedData.getGameWidth() / 2,
                this.sharedData.getGameHeight() / 2);
        left = new Point(nose.x, nose.y);
        right = new Point(nose.x, nose.y);

        sb = new GlideShipBehavior();
        //sb = new NormalShipBehavior();
        mv = sb.getDefaultVector();
        
        setShipPoints();

    }

    @Override
    public double GetMissileStartX() {
        return MissileStartX;
    }

    @Override
    public double GetMissileStartY() {
        return MissileStartY;
    }

    @Override
    public int GetMissileTrajectory() {
        return MissileTrajectory;
    }

    private void setShipPoints() {

        left.setLocation(
                nose.x
                + (Math.cos(Math.toRadians(mv.facing)
                        + Math.toRadians(NOSECONE)) * SHIPLENGTH),
                nose.y
                + (Math.sin(Math.toRadians(mv.facing)
                        + Math.toRadians(NOSECONE)) * SHIPLENGTH));
        right.setLocation(
                nose.x
                + (Math.cos(Math.toRadians(mv.facing)
                        - Math.toRadians(NOSECONE)) * SHIPLENGTH),
                nose.y
                + (Math.sin(Math.toRadians(mv.facing)
                        - Math.toRadians(NOSECONE)) * SHIPLENGTH));

        this.npoints = 3;

        this.xpoints = new int[]{
            (int) Math.round(nose.x - Math.cos(Math.toRadians(mv.facing))
            * NOSEOFFSET), left.x, right.x};

        this.ypoints = new int[]{
            (int) Math.round(nose.y - Math.sin(Math.toRadians(mv.facing))
            * NOSEOFFSET), left.y, right.y};
    }

    private void fireMissile() {

        MissileStartX = Math.cos(Math.toRadians(mv.facing));
        MissileStartY = Math.sin(Math.toRadians(mv.facing));
        MissileStartX *= (NOSEOFFSET + 2 * 10);
        MissileStartY *= (NOSEOFFSET + 2 * 10);
        MissileStartX = nose.x - MissileStartX;
        MissileStartY = nose.y - MissileStartY;
        MissileTrajectory = mv.facing;

        NotifyObservers();
    }

    private void rotateRight() {
        mv = sb.rotateRight(mv);
        setShipPoints();
    }

    private void rotateLeft() {
        mv = sb.rotateLeft(mv);
        setShipPoints();
    }

    private void thrust() {
        mv = sb.thrust(mv);
        nose.setLocation(nose.x - (Math.cos(Math.toRadians(mv.direction)) * mv.speed),
                nose.y - (Math.sin(Math.toRadians(mv.direction)) * mv.speed));
        setShipPoints();
    }
    private void slow() {
        mv = sb.slow(mv);
        nose.setLocation(nose.x - (Math.cos(Math.toRadians(mv.direction)) * mv.speed),
                nose.y - (Math.sin(Math.toRadians(mv.direction)) * mv.speed));
        setShipPoints();
    }

    @Override
    public void render(Graphics2D graphics) {

        /* Do this to reset bounds */
        this.invalidate();

        /* Key handlers */
        if (this.sharedData.GetPressedKeys().contains(KeyEvent.VK_NUMPAD4)) {
            rotateLeft();
        }
        if (this.sharedData.GetPressedKeys().contains(KeyEvent.VK_NUMPAD5)) {
            thrust();
        }
        else{
            slow();
        }
        
        if (this.sharedData.GetPressedKeys().contains(KeyEvent.VK_NUMPAD6)) {
            rotateRight();
        }
        if (this.sharedData.GetPressedKeys().contains(KeyEvent.VK_SPACE)) {
            fireMissile();
        }

        /* Draw */
        graphics.setColor(SHIPCOLOR);
        graphics.drawPolygon(this);
    }

    @Override
    public void update() {

        /* Collision Detection */
        for (IGameFigure s : this.sharedData.GetGameFigures()) {
            if (s != this && s.intersects(this.getBounds2D())) {
                this.sharedData.setGameOver(true);
            }
        }

        /* Screen Wrap */
        if (nose.x < 0) {
            nose.setLocation(nose.x + this.sharedData.getGameWidth(), nose.y);
        }

        if (nose.x > this.sharedData.getGameWidth()) {
            nose.setLocation(1, nose.y);
        }

        if (nose.y < 0) {
            nose.setLocation(nose.x, nose.y + this.sharedData.getGameHeight());
        }

        if (nose.y > this.sharedData.getGameHeight()) {
            nose.setLocation(nose.x, 1);
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
        this.sharedData.GetGameFigures().add(this);
    }

}
