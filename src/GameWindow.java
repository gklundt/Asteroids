
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameWindow extends AbstractGameWindow {

    private final JPanel buttonPanel;
    private final JButton startButton;
    private final JButton startOptButton;
    private final JButton quitButton;
    private final JButton resetButton;

    private IAnimator animator;
    private AbstractGamePanel gamePanel;
    private ISharedData sharedData;
    private Thread gameThread;

    public GameWindow() {

        setTitle("Asteroids");
        //setSize(1024, 768);
        int aspect = 5;
        setSize(320 * aspect, 181 * aspect);
        setLocation(100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());

        startButton = new JButton("Start Game");
        startOptButton = new JButton("Start Newton Game");
        quitButton = new JButton("Quit");
        resetButton = new JButton("Reset");

        buttonPanel = new JPanel();
        buttonPanel.add(quitButton);
        buttonPanel.add(startButton);
        buttonPanel.add(startOptButton);
        buttonPanel.add(resetButton);

        startButton.setFocusable(false);
        startOptButton.setFocusable(false);
        quitButton.setFocusable(false);
        resetButton.setFocusable(false);

        c.add(buttonPanel, BorderLayout.SOUTH);
        validate();

    }

    @Override
    public void addListeners() {
        startButton.addActionListener(this);
        startOptButton.addActionListener(this);
        quitButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (animator != null) {
            animator.SetRunning(false);
        }
        if (e.getSource() == this.startButton) {
            resetGame();
            startGame(1);
        } else if (e.getSource() == this.startOptButton) {
            resetGame();
            startGame(2);
        } else if (e.getSource() == this.resetButton) {
            resetGame();
            welcome();
        } else {
            quitGame();
        }

    }

    @Override
    public void resetGame() {

        if (this.sharedData != null) {
            this.sharedData.setGameOver(true);
        }
        if (this.sharedData != null) {
            this.sharedData = null;
        }
        if (this.gamePanel != null) {
            this.gamePanel = null;
        }
        if (this.animator != null) {
            this.animator = null;
        }
        if (this.gameThread != null) {
            while (this.gameThread.isAlive()) {
                this.validate();
            }
        }
        this.validate();

    }

    @Override
    public void startGame(int movementType) {

        Container c = this.getContentPane();
        this.sharedData = SharedDataFactory
                .GetSharedData(ISharedDataFactory.ASTEROIDS);
        this.sharedData.setMovementType(movementType);
        this.gamePanel = GameFactory.GetGamePanel(sharedData,
                IGameFactory.ASTEROIDS);
        this.animator = AnimatorFactory.GetAnimator(sharedData, gamePanel,
                IAnimatorFactory.ASTEROIDS);

        c.add(this.gamePanel, BorderLayout.CENTER);
        this.gamePanel.setFocusable(true);
        c.dispatchEvent(new FocusEvent(this.gamePanel, FocusEvent.FOCUS_FIRST));

        this.validate();

        gameThread = new Thread(animator);
        gameThread.start();
    }

    @Override
    public void quitGame() {
        System.exit(0);
    }

    @Override
    public void welcome() {

        Container c = this.getContentPane();
        this.sharedData = SharedDataFactory
                .GetSharedData(ISharedDataFactory.SPLASHSCREEN);
        this.gamePanel = GameFactory.GetGamePanel(sharedData,
                IGameFactory.SPLASHSCREEN);
        this.animator = AnimatorFactory.GetAnimator(sharedData, gamePanel,
                IAnimatorFactory.SPLASHSCREEN);

        c.add(this.gamePanel, BorderLayout.CENTER);
        this.gamePanel.setFocusable(true);
        c.dispatchEvent(new FocusEvent(this.gamePanel, FocusEvent.FOCUS_FIRST));

        this.validate();

        gameThread = new Thread(animator);
        gameThread.start();

    }

}
