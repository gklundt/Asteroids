
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;

public class SplashPanel extends AbstractGamePanel {

    public SplashPanel(ISharedData sharedData) {
        super.sharedData = sharedData;
    }

    @Override
    public void render() {

        if (dbImage == null) {
            dbImage = createImage(getWidth(), getHeight());
            if (dbImage == null) {
                System.out.println("dbImage is null");
                return;
            } else {
                graphics = (Graphics2D) dbImage.getGraphics();
            }
        }

        graphics.setBackground(new Color(34, 34, 34));
        graphics.clearRect(0, 0, sharedData.getGameWidth(), sharedData.getGameHeight());

        synchronized (sharedData.GetGameFigures()) {
            for (IGameFigure gf : this.sharedData.GetGameFigures()) {
                gf.render(graphics);
            }

        }

        graphics.setColor(new Color(255, 255, 255));
        Font f = new Font("", 1, 72);
        GlyphVector gv = f.createGlyphVector(graphics.getFontRenderContext(),
                "Asteroids");

        Rectangle2D gvr = gv.getVisualBounds();
        float gvx = (float) ((this.sharedData.getGameWidth() / 2) - (gvr
                .getWidth() / 2));
        float gvy = (float) ((this.sharedData.getGameHeight() / 2) + ((gvr
                .getHeight() / 2)));
        graphics.drawGlyphVector(gv, gvx, gvy);

        f = new Font("", 1, 14);
        gv = f.createGlyphVector(graphics.getFontRenderContext(),
                "Spacebar to Fire : Left,Thrust,Right: Num(4,5,6)");

        gvr = gv.getVisualBounds();
        gvx = (float) ((this.sharedData.getGameWidth() / 2) - (gvr
                .getWidth() / 2));
        gvy = (float) ((this.sharedData.getGameHeight() / 2) + ((gvr
                .getHeight() / 2))) + 45;
        graphics.drawGlyphVector(gv, gvx, gvy);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // not implemented
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // not implemented
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not implemented
    }

    @Override
    public void initializeSharedData() {

        sharedData.setGameWidth(getWidth());
        sharedData.setGameHeight(getHeight());
        addKeyListener(this);
        sharedData.createGameObjects();

    }

    @Override
    public void showGameOver() {
        // not implemented
    }

}
