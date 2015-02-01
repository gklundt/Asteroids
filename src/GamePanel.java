
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;

public class GamePanel extends AbstractGamePanel {

    public GamePanel(ISharedData sharedData) {
        super.sharedData = sharedData;
    }

    @Override
    public void render() {

        if (dbImage == null) {
            dbImage = createImage(this.getWidth(), this.getHeight());
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
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        sharedData.GetPressedKeys().add(e.getKeyCode());
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        sharedData.GetPressedKeys().remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not Used
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

        Graphics2D g = (Graphics2D) this.getGraphics();
        g.setColor(new Color(255, 255, 255));

        Font f = new Font("", 1, 72);
        GlyphVector gv = f.createGlyphVector(g.getFontRenderContext(),
                "GAME OVER");

        Rectangle2D gvr = gv.getVisualBounds();
        float gvx = (float) ((this.sharedData.getGameWidth() / 2) - (gvr
                .getWidth() / 2));
        float gvy = (float) ((this.sharedData.getGameHeight() / 2) + ((gvr
                .getHeight() / 2)));

        g.drawGlyphVector(gv, gvx, gvy);

    }
}
