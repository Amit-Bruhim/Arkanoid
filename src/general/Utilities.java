package general;

import geometry.basicshapes.Line;
import geometry.basicshapes.Point;

import java.util.ArrayList;
import java.util.List;


/**
 * this class contain all the general methods.
 */
public class Utilities {
    /**
     * method that takes a list of points and a line,
     * and return the closest point.
     *
     * @param list       the list
     * @param trajectory the line
     * @return the closest point
     */
    public static Point getClosestPoint(List<Point> list, Line trajectory) {
        // iterate over the points and find their distance.
        List<Double> distance = new ArrayList<>();
        for (Point point : list) {
            distance.add(trajectory.start().distance(point));
        }

        // iterate over the distances and find the smallest.
        int minIndex = 0;
        for (int i = 0; i < distance.size(); i++) {
            if (distance.get(i) < distance.get(minIndex)) {
                minIndex = i;
            }
        }
        return list.get(minIndex);
    }

    /**
     * method that prints a line.
     *
     * @param line the line.
     */
    public static void printLine(Line line) {
        System.out.println(line.start().getX() + "," + line.start().getY()
                + "," + line.end().getX() + "," + line.end().getY());
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

}

