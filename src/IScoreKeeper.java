
public interface IScoreKeeper extends IObserver {

    public int getMissilesFiredCount();

    public int getMissilesHitCount();

    public int getLargeAsteroidCount();

    public int getMediumAsteroidCount();

    public int getSmallAsteroidCount();

}
