package geometry.advancedshapes;

import geometry.basicshapes.Line;
import geometry.basicshapes.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * class that describes a rectangle.
 * a rectangle has:
 */
public class Rectangle {
    // members
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * method that get the x parameter of the upper left corner of the
     * rectangle.
     *
     * @return the x parameter of the upper left corner of the rectangle.
     */
    public double getX() {
        return this.upperLeft.getX();
    }

    /**
     * method that get the y parameter of the upper left corner of the
     * rectangle.
     *
     * @return the y parameter of the upper left corner of the rectangle.
     */
    public double getY() {
        return this.upperLeft.getY();
    }

    /**
     * constructor that builds a rectangle.
     *
     * @param upperLeft the upper left corner of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * method that Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line the line
     * @return the list
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // get the corners
        Point[] corners = getCorners(this);

        // get the edges of the rectangle.
        Line upEdge = new Line(corners[0], corners[1]);
        Line bottomEdge = new Line(corners[2], corners[3]);
        Line leftEdge = new Line(corners[0], corners[2]);
        Line rightEdge = new Line(corners[1], corners[3]);

        // get the intersection points with the edges.
        Point upPoint = line.intersectionWith(upEdge);
        Point bottomPoint = line.intersectionWith(bottomEdge);
        Point leftPoint = line.intersectionWith(leftEdge);
        Point rightPoint = line.intersectionWith(rightEdge);

        // add them to the list.
        List<Point> list = new ArrayList<>();
        if (upPoint != null && line.isIntersecting(upEdge)) {
            list.add(upPoint);
        }
        if (bottomPoint != null && line.isIntersecting(bottomEdge)) {
            list.add(bottomPoint);
        }
        if (leftPoint != null && line.isIntersecting(leftEdge)) {
            list.add(leftPoint);
        }
        if (rightPoint != null && line.isIntersecting(rightEdge)) {
            list.add(rightPoint);
        }
        return list;
    }

    /**
     * method that gets the corners of the rectangle.
     *
     * @param rectangle the rectangle.
     * @return the corners of the rectangle.
     */
    public Point[] getCorners(Rectangle rectangle) {
        Point upperLeft = rectangle.getUpperLeft();
        Point upperRight = new Point(
                upperLeft.getX() + rectangle.getWidth(), upperLeft.getY());
        Point bottomLeft = new Point(
                upperLeft.getX(), upperLeft.getY() + rectangle.getHeight());
        Point bottomRight = new Point(
                upperRight.getX(), bottomLeft.getY());
        return new Point[]{upperLeft, upperRight, bottomLeft, bottomRight};
    }

    /**
     * method that return the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * method that return the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * method that return the upper left corner of the rectangle.
     *
     * @return the upper left corner of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * method that sets the upper left corner.
     *
     * @param x the new x
     * @param y the new y
     */
    public void setUpperLeft(double x, double y) {
        this.upperLeft.setX(x);
        this.upperLeft.setY(y);
    }
}