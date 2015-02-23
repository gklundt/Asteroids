
public interface IMissileData extends IObserver {

    boolean getMissileFired();

    void setMissileFired(boolean b);

    double getMissileStartX();

    double getMissileStartY();

    double getMissileTrajectory();

    int getMissileSize();
    
    int getMissileSpeed();

}
