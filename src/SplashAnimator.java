
public class SplashAnimator implements IAnimator {

    private final ISharedData sharedData;
    private final AbstractGamePanel gamePanel;
    private boolean running;

    public SplashAnimator(ISharedData sharedData, AbstractGamePanel gamePanel) {
        this.sharedData = sharedData;
        this.gamePanel = gamePanel;
    }

    @Override
    public void SetRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {

        gamePanel.initializeSharedData();
        running = true;
        while (running) {
            this.sharedData.update();
            this.gamePanel.render();
            this.gamePanel.printScreen();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }
        }
    }
}
