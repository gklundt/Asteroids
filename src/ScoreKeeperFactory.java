
public class ScoreKeeperFactory implements IScoreKeeperFactory {

    public static IScoreKeeper getScoreKeeper(int t) {
        switch (t) {
            case ASTEROIDS:
                return new ScoreKeeper();
            default:
                return null;
        }
    }
}
