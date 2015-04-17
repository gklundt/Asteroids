
import java.util.List;
import java.util.Set;

public interface ISharedData {

    void update();

    List<IGameFigure> GetGameFigures();

    Set<Integer> GetPressedKeys();

    int getGameWidth();

    int getGameHeight();

    int getMovementType();

    boolean getGameOver();

    void setGameWidth(int width);

    void setGameHeight(int height);

    void setMovementType(int movementType);

    void setGameOver(boolean isGameOver);

    void createGameObjects();

    IScoreKeeper getScoreKeeper();

}
