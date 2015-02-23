public interface iShipBehavior {

    public static final int ROTATION = 15;
    public static final double SHIPSPEED = 15;

    MovementVector getDefaultVector();
    MovementVector rotateRight(MovementVector current);
    MovementVector rotateLeft(MovementVector current);
    MovementVector thrust(MovementVector current);
    MovementVector slow(MovementVector current);

}
