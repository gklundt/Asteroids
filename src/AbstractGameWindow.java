
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public abstract class AbstractGameWindow extends JFrame implements
        ActionListener {

    public abstract void startGame(int movementType);

    public abstract void quitGame();

    public abstract void resetGame();

    public abstract void welcome();
    
    public abstract void addListeners();

}
