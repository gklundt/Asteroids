
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public final class GameSharedData implements ISharedData {

    private final List<IGameFigure> gameFigures;
    private final Set<Integer> pressedKeys;
    private static int GAMEWIDTH;
    private static int GAMEHEIGHT;

    private boolean gameOver;

    private final IScoreKeeper scoreKeeper;
    private final IMissileData missileData;

    public GameSharedData() {
        this.gameFigures = Collections
                .synchronizedList(new ArrayList<IGameFigure>());
        this.pressedKeys = Collections.synchronizedSet(new HashSet<Integer>());
        this.gameOver = false;
        this.scoreKeeper = ScoreKeeperFactory.getScoreKeeper(IScoreKeeperFactory.ASTEROIDS);
        this.missileData = MissileDataFactory.getMissileData(IMissileDataFactory.ASTEROIDS);

    }

    @Override
    public void update() {

        /* A Missile has been fired. */
        if (missileData.getMissileFired()) {
            MissileFactory.createMissile(this.missileData, this);
            missileData.setMissileFired(false);
        }


        /* Collection of dead objects */
        ArrayList<IGameFigure> rem = new ArrayList<>();
        for (IGameFigure gf : this.gameFigures) {
            if (gf.getRemove()) {
                rem.add(gf);
            }
        }

        for (IGameFigure gf : rem) {
            if (gf instanceof Asteroid) {
                RandomAsteroidFactory.createAsteroids((Asteroid) gf, this);
            }
        }

        this.gameFigures.removeAll(rem);

        /* Update all game objects */
        for (IGameFigure gf : this.gameFigures) {
            gf.update();
        }

        /* Make sure there are still asteroids */
        boolean gameFiguresHasAsteroids = false;
        for (IGameFigure gf : this.gameFigures) {
            if (gf instanceof Asteroid) {
                gameFiguresHasAsteroids = true;
                break;
            }
        }

        if (!gameFiguresHasAsteroids) {
            RandomAsteroidFactory.createAsteroids(IRandomAsteroidFactory.NORMAL, this);
        }

    }

    @Override
    public List<IGameFigure> GetGameFigures() {
        return gameFigures;
    }

    @Override
    public Set<Integer> GetPressedKeys() {
        return pressedKeys;
    }

    @Override
    public int getGameWidth() {
        return GAMEWIDTH;
    }

    @Override
    public int getGameHeight() {
        return GAMEHEIGHT;
    }

    @Override
    public void setGameWidth(int width) {
        GAMEWIDTH = width;
    }

    @Override
    public void setGameHeight(int height) {
        GAMEHEIGHT = height;
    }

    @Override
    public void setGameOver(boolean isGameOver) {
        this.gameOver = isGameOver;
    }

    @Override
    public boolean getGameOver() {
        return this.gameOver;
    }

    @Override
    public void createGameObjects() {
        IGameFigure ship = ShipFactory.createShip(this, IShipFactory.DEFAULTSHIP);
        ship.AddObserver(missileData);
        RandomAsteroidFactory.createAsteroids(IRandomAsteroidFactory.NORMAL, this);
        ScoreBoardFactory.createScoreBoard(this);
    }

    @Override
    public IScoreKeeper getScoreKeeper() {
        return this.scoreKeeper;
    }
}
