
public class ScoreKeeper implements IScoreKeeper {

    private int NumberOfMissilesFired;
    private int NumberOfMissilesHit;
    private int NumberOfLargeAsteroids;
    private int NumberOfMediumAsteroids;
    private int NumberOfSmallAsteroids;

    public ScoreKeeper() {
        NumberOfMissilesFired = 0;
        NumberOfMissilesHit = 0;
        NumberOfLargeAsteroids = 0;
        NumberOfMediumAsteroids = 0;
        NumberOfSmallAsteroids = 0;
    }

    @Override
    public void updateObserver(Object obj) {

        if (obj instanceof Missile) {
            Missile object = (Missile) obj;
            if (object.getRemove()) {
                NumberOfMissilesHit++;
            } else {
                NumberOfMissilesFired++;
            }
        }

        if (obj instanceof Asteroid) {
            Asteroid object = (Asteroid) obj;
            if (!object.getRemove()) {
                switch (object.getState().getType()) {
                    case IAsteroidStateFactory.LARGE_ASTEROID:
                        NumberOfLargeAsteroids++;
                        break;
                    case IAsteroidStateFactory.MEDIUM_ASTEROID:
                        NumberOfMediumAsteroids++;
                        break;
                    case IAsteroidStateFactory.SMALL_ASTEROID:
                        NumberOfSmallAsteroids++;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public int getMissilesFiredCount() {
        return NumberOfMissilesFired;
    }

    @Override
    public int getMissilesHitCount() {
        return NumberOfMissilesHit;
    }

    @Override
    public int getLargeAsteroidCount() {
        return NumberOfLargeAsteroids;
    }

    @Override
    public int getMediumAsteroidCount() {
        return NumberOfMediumAsteroids;
    }

    @Override
    public int getSmallAsteroidCount() {
        return NumberOfSmallAsteroids;
    }
}
