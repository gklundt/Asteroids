
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.font.GlyphVector;

public class ScoreBoard extends Polygon implements IGameFigure {

    private GlyphVector gv;
    private Graphics2D g;
    private final ISharedData sharedData;
    private final StringBuilder score;
    private final IScoreKeeper scoreKeeper;

    ScoreBoard(ISharedData sharedData) {
        score = new StringBuilder();
        this.sharedData = sharedData;
        scoreKeeper = sharedData.getScoreKeeper();
    }

    @Override
    public void setRemove(boolean b) {
        // not used
    }

    @Override
    public boolean getRemove() {
        return false;
    }

    @Override
    public void render(Graphics2D graphics) {

        this.g = graphics;
        graphics.setColor(new Color(255, 255, 255));

        Font f = new Font("", 1, 10);
        gv = f.createGlyphVector(g.getFontRenderContext(), score.toString());

        graphics.drawGlyphVector(gv, 1, f.getSize());

    }

    @Override
    public void update() {

        int f, h, l, m, s;
        float r, t;
        f = scoreKeeper.getMissilesFiredCount();
        h = scoreKeeper.getMissilesHitCount();
        r = h > 0 ? (float)h / (float)f : 0;
        l = scoreKeeper.getLargeAsteroidCount();
        m = scoreKeeper.getMediumAsteroidCount();
        s = scoreKeeper.getSmallAsteroidCount();
        t = 100 * h * r;

        score.delete(0, score.length());
        score.append(String.format("Fired: %d ", f));
        score.append(String.format("Hit: %d ", h));
        score.append(String.format("Ratio: %4.2f ", r));
        score.append(String.format("Large: %d ", l));
        score.append(String.format("Medium: %d ", m));
        score.append(String.format("Small: %d ", s));
        score.append(String.format("Score: %4.2f ", t));

    }

    @Override
    public void AddObserver(IObserver obj) {
        // not implemented
    }

    @Override
    public void NotifyObservers() {
        // not implemented
    }

    @Override
    public void addToSharedData() {
        sharedData.GetGameFigures().add(this);
    }

}
