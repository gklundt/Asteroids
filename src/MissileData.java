
public class MissileData implements IMissileData {

    private static final int MISSILEHOLDMS = 50;
    private static final int MISSILESPEED = 20;
    private static final int MISSILESIZE = 5;

    private long MissileLaunchTime;
    private double MissileStartX;
    private double MissileStartY;
    private int MissileTrajectory;
    private boolean MissileFired;

    public MissileData() {
        this.MissileLaunchTime = 0;

    }

    @Override
    public boolean getMissileFired() {
        return this.MissileFired;
    }

    @Override
    public double getMissileStartX() {
        return MissileStartX;
    }

    @Override
    public double getMissileStartY() {
        return MissileStartY;
    }

    @Override
    public int getMissileTrajectory() {
        return MissileTrajectory;
    }

    @Override
    public int getMissileSize() {
        return MISSILESIZE;
    }

    @Override
    public int getMissileSpeed() {
        return MISSILESPEED;
    }

    @Override
    public void setMissileFired(boolean b) {
        if (b && System.currentTimeMillis() - this.MissileLaunchTime < MISSILEHOLDMS) {
            return;
        }
        this.MissileLaunchTime = System.currentTimeMillis();
        MissileFired = b;

    }

    @Override
    public void updateObserver(Object obj) {
        if (obj instanceof IShip) {
            IShip ship = (IShip) obj;
            MissileStartX = ship.GetMissileStartX();
            MissileStartY = ship.GetMissileStartY();
            MissileTrajectory = ship.GetMissileTrajectory();
            setMissileFired(true);
        }
    }

}
