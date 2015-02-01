
public class ScoreBoardFactory implements IScoreBoardFactory {

    public static IGameFigure createScoreBoard(ISharedData sharedData) {
        IGameFigure s= new ScoreBoard(sharedData);
        s.addToSharedData();
        return s;
    }

}
