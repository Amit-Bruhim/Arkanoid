package general;

import geometry.basicshapes.Point;

/**
 * general.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * method that sets the horizontal change.
     *
     * @param dx the horizontal change
     */
    public void changeDX(double dx) {
        this.dx = dx;
    }

    /**
     * method that gets dx.
     *
     * @return dx
     */
    public double getDX() {
        return this.dx;
    }

    /**
     * method that gets dy.
     *
     * @return dy
     */
    public double getDY() {
        return this.dy;
    }

    /**
     * method that sets the vertical change.
     *
     * @param dy the vertical change
     */
    public void changeDY(double dy) {
        this.dy = dy;
    }


    /**
     * constructor.
     *
     * @param dx the horizontal change
     * @param dy the vertical change
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * method the change the velocity from angle and speed to dx and dy.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity in terms of dx and dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(-angle + 90));
        double dy = -1 * speed * Math.sin(Math.toRadians(-angle + 90));
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p the point
     * @return point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}