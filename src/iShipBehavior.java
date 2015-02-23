public interface iShipBehavior {

    public static final int ROTATION = 15;
    public static final int SHIPSPEED = 10;

    MovementVector getDefaultVector();
    MovementVector rotateRight(MovementVector current);
    MovementVector rotateLeft(MovementVector current);
    MovementVector thrust(MovementVector current);
    MovementVector slow(MovementVector current);

}
