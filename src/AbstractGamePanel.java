
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public abstract class AbstractGamePanel extends JPanel implements KeyListener {

    protected Image dbImage;
    protected Graphics2D graphics;
    protected ISharedData sharedData;

    public abstract void initializeSharedData();

    public abstract void render();

    public void printScreen() {
        Graphics2D g;
        try {
            g = (Graphics2D) this.getGraphics();
            if ((g != null) && (dbImage != null)) {
                g.drawImage(dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync();
            if (g != null) {
                g.dispose();
            }
        } catch (Exception e) {
            System.out.println("Graphics error: " + e);
        }
    }

    public abstract void showGameOver();

}
