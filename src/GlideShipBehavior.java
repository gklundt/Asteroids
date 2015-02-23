
public class GlideShipBehavior implements iShipBehavior {

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
        
        
        double x1 = (double) current.speed * Math.cos(Math.toRadians(current.direction));
        double y1 = (double) current.speed * Math.sin(Math.toRadians(current.direction));

        
        double x2 = 2 * Math.cos(Math.toRadians(current.facing));
        double y2 = 2 * Math.sin(Math.toRadians(current.facing));
        
        double r = Math.sqrt(Math.pow(x1 + x2, 2) + Math.pow(y1 + y2, 2));

        double t = Math.atan((y1 + y2) / (x1 + x2));

        boolean q1 = (y1 + y2) >= 0 && (x1 + x2) >= 0;
        boolean q2 = (y1 + y2) >= 0 && (x1 + x2) < 0;
        boolean q3 = (y1 + y2) < 0 && (x1 + x2) < 0;
        boolean q4 = (y1 + y2) < 0 && (x1 + x2) >= 0;

        if (q1) {current.direction = (int) Math.toDegrees(t);}
        if (q4) {current.direction = 360 + (int) Math.toDegrees(t);}
        if (q2) {current.direction = 180 + (int) Math.toDegrees(t);}
        if (q3) {current.direction = 180 + (int) Math.toDegrees(t);}

        current.speed = Math.abs(r) > SHIPSPEED ? SHIPSPEED : r;

        return current;
    }

    @Override
    public MovementVector slow(MovementVector current) {
        //current.speed = current.speed > 2 ? --current.speed : 2;
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
