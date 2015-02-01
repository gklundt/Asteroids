
public class GameAnimator implements IAnimator {

    private final ISharedData sharedData;
    private final AbstractGamePanel gamePanel;
    private boolean running;

    public GameAnimator(ISharedData sharedData, AbstractGamePanel gamePanel) {
        this.sharedData = sharedData;
        this.gamePanel = gamePanel;
    }

    @Override
    public void SetRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {

        long t1, t2;
        gamePanel.initializeSharedData();
        running = true;
        while (running) {
            t1 = System.currentTimeMillis();
            this.sharedData.update();
            this.gamePanel.render();
            this.gamePanel.printScreen();
            running = !this.sharedData.getGameOver();
            t2 = System.currentTimeMillis();
            try {
                if (t2 - t1 < 50) {
                    Thread.sleep(50 - (t2 - t1));
                }
            } catch (InterruptedException e) {

            }
        }
        this.gamePanel.showGameOver();
    }
}
