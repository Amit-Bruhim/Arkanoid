package geometry.basicshapes;

import geometry.advancedshapes.Rectangle;
import general.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * class line has 2 fields: start and end, which indicates the start point
 * of the line and its end.
 */
public class Line {
    // the fields
    private Point start;
    private Point end;

    /**
     * constructor.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor.
     *
     * @param x1 the horizontal space of the start
     * @param y1 the vertical space of the start
     * @param x2 the horizontal space of the end
     * @param y2 the vertical space of the end
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * function that calculates the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

    /**
     * function that calculates the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = (x1 + x2) / 2;
        double y3 = (y1 + y2) / 2;
        return new Point(x3, y3);
    }

    /**
     * method that return the start point.
     *
     * @return the start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * method that return the end point.
     *
     * @return the end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * the function checks if this line intersect with other lines.
     *
     * @param other the other line.
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (intersectionWith(other) != null) {
            return isBelong(intersectionWith(other), other)
                    && isBelong(intersectionWith(other), this);
        }
        return isBelong(this.start, other)
                || isBelong(this.end, other);
    }

    /**
     * function that checks if a line is intersecting with 2 other lines.
     *
     * @param other1 the first other line
     * @param other2 the second other line
     * @return true if this 2 lines intersect with this line, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1)
                && this.isIntersecting(other2);
    }

    /**
     * function that checks the intersection points of this line.
     * with another line
     *
     * @param other the other line
     * @return the intersection point if the lines intersect,
     * and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // get the equations of the lines.
        double m1 = getSlope(this);
        double m2 = getSlope(other);
        double b1 = getIntercept(this, m1);
        double b2 = getIntercept(other, m2);
        // if they are parallel - return null.
        if (Point.doubleEquals(m1, m2)) {
            return null;
        }
        double x3;
        double y3;

        // edge cases - vertical lines.
        if (m1 == Double.MAX_VALUE) {
            x3 = this.start.getX();
            y3 = (m2 * x3) + b2;
        } else if (m2 == Double.MAX_VALUE) {
            x3 = other.start.getX();
            y3 = (m1 * x3) + b1;
            // otherwise
        } else {
            x3 = ((b2 - b1) / (m1 - m2));
            y3 = (m1 * x3) + b1;
        }
        return new Point(x3, y3);
    }

    /**
     * function that checks if 2 lines look the same.
     *
     * @param other the other line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * a method that calculates the slope of a line.
     *
     * @param line the line
     * @return the slope
     */
    public double getSlope(Line line) {
        double x1 = line.start.getX();
        double y1 = line.start.getY();
        double x2 = line.end.getX();
        double y2 = line.end.getY();
        if (Point.doubleEquals(x1, x2)) {
            return Double.MAX_VALUE;
        }
        return ((y1 - y2) / (x1 - x2));
    }

    /**
     * a method that calculates the 'b' in the equation y=mx+b of the line.
     *
     * @param line  the line
     * @param slope the slope of the line
     * @return the 'b'
     */
    public double getIntercept(Line line, double slope) {
        double x1 = line.start.getX();
        double y1 = line.start.getY();
        return y1 - (x1 * slope);
    }

    /**
     * a method that checks if a point is on the line-segment
     * (when we know for sure that the point is on the line).
     *
     * @param point the point
     * @param line  the line
     * @return true if the point is on the line segment, false-otherwise.
     */
    public boolean isBelong(Point point, Line line) {
        // get the (x,y)
        double x1 = line.start.getX();
        double y1 = line.start.getY();
        double x2 = line.end.getX();
        double y2 = line.end.getY();
        // double maxX = Math.max(x1, x2);
        // double maxY = Math.max(y1, y2);
        // double minX = Math.min(x1, x2);
        // double minY = Math.min(y1, y2);


        // check if it is on the line segment
        return point.getX() + Constants.EPSILON >= Math.min(x1, x2)
                && point.getX() <= Math.max(x1, x2) + Constants.EPSILON
                && point.getY() + Constants.EPSILON >= Math.min(y1, y2)
                && point.getY() <= Math.max(y1, y2) + Constants.EPSILON;
    }

    /**
     * method that gets the closest intersection point of the rectangle,
     * with the start of the line.
     *
     * @param rect the rectangle
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // get the intersection points.
        List<Point> list = new ArrayList<>();
        list = rect.intersectionPoints(this);
        if (list == null || list.isEmpty()) {
            return null;
        }
        double minimum = this.start.distance(list.get(0));
        Point answer = list.get(0);
        // iterate over the points, find their distance from the start point,
        // and find the closest.
        for (Point current : list) {
            double distance = this.start.distance(current);
            if (distance < minimum) {
                minimum = distance;
                answer = current;
            }
        }
        return answer;
    }
}