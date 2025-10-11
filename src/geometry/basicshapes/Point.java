package geometry.basicshapes;
import general.Constants;
/**
 * class point has 2 fields: x and y, that indicates its location on the space.
 */
public class Point {
    // the fields
    private double x;
    private double y;

    /**
     * method that set the horizontal space.
     *
     * @param x the horizontal space
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * method that set the vertical space.
     *
     * @param y the vertical space
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * constructor.
     *
     * @param x the horizontal space
     * @param y the vertical space
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * the function checks if 2 doubles are equals.
     *
     * @param num1 the first double
     * @param num2 the second double
     * @return true if they are equals. false, otherwise.
     */
    public static boolean doubleEquals(double num1, double num2) {
        return Math.abs(num1 - num2) < Constants.EPSILON;
    }

    /**
     * function that calculates the distance of this point other point.
     *
     * @param other the other point
     * @return the distance
     */
    public double distance(Point other) {
        double x1 = this.x;
        double y1 = this.y;
        double x2 = other.x;
        double y2 = other.y;
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

    /**
     * function that checks if 2 points are equal.
     *
     * @param other the other point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return doubleEquals(this.x, other.x) && doubleEquals(this.y, other.y);
    }

    /**
     * method that gets the horizontal space.
     *
     * @return the horizontal space
     */
    public double getX() {
        return this.x;
    }

    /**
     * method that gets the vertical space.
     *
     * @return the vertical space
     */
    public double getY() {
        return this.y;
    }
}