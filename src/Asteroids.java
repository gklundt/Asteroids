
public class Asteroids {

    public synchronized static void main(String[] args) {
/* This is a big fat comment*/
        AbstractGameWindow gameWindow = GameWindowFactory.GetGameWindow(GameWindowFactory.ASTEROIDS);
        gameWindow.setVisible(true);
        gameWindow.resetGame();
        gameWindow.welcome();

    }
}
