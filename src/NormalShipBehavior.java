
public class NormalShipBehavior implements iShipBehavior {

    @Override
    public MovementVector rotateRight(MovementVector current) {
                current.facing = (current.facing + ROTATION) % 360;
                return current;
    }

    @Override
    public MovementVector rotateLeft(MovementVector current) {
                current.facing = ((360 + current.facing) - ROTATION) % 360;
                return current;
    }

    @Override
    public MovementVector thrust(MovementVector current) {
        current.direction = current.facing;
        current.speed = SHIPSPEED;
        return current;
    }

    @Override
    public MovementVector slow(MovementVector current) {
        current.speed = 0;
        return current;
    }

    @Override
    public MovementVector getDefaultVector() {
        MovementVector mv = new MovementVector();
            mv.speed = 0;
            mv.direction = 0;
            mv.facing = 0;
        return mv;
    }
    
}
