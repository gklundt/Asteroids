
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public final class SplashSharedData implements ISharedData {

    private final List<IGameFigure> gameFigures;
    private static int GAMEWIDTH;
    private static int GAMEHEIGHT;
    private static int MOVEMENTTYPE;

    public SplashSharedData() {
        this.gameFigures = Collections
                .synchronizedList(new ArrayList<IGameFigure>());

    }

    @Override
    public void update() {

        /* Update all game objects */
        for (IGameFigure gf : this.gameFigures) {
            gf.update();
        }

    }

    @Override
    public List<IGameFigure> GetGameFigures() {
        return gameFigures;
    }

    @Override
    public Set<Integer> GetPressedKeys() {
        return null;
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
    public int getMovementType() {
        return MOVEMENTTYPE;
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
    public void setMovementType(int movementType) {
        MOVEMENTTYPE = movementType;
    }
    
    @Override
    public void setGameOver(boolean isGameOver) {
        // not implemented
    }

    @Override
    public boolean getGameOver() {
        return false;
    }

    @Override
    public void createGameObjects() {
        RandomAsteroidFactory.createAsteroids(IRandomAsteroidFactory.HARD, this);
    }

    @Override
    public IScoreKeeper getScoreKeeper() {
        return null;
    }
}
