
public class AnimatorFactory implements IAnimatorFactory {

    public static IAnimator GetAnimator(ISharedData sharedData, AbstractGamePanel gamePanel, int animatorType) {
        switch (animatorType) {
            case ASTEROIDS:
                return new GameAnimator(sharedData, gamePanel);
            case SPLASHSCREEN:
                return new SplashAnimator(sharedData, gamePanel);
            default:
                return null;
        }

    }

}
