package game;

import collide.Collidable;
import collide.CollisionInfo;
import general.Utilities;
import geometry.basicshapes.Line;
import geometry.basicshapes.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * the game.GameEnvironment class will be a collection of things
 * the ball can collide with.
 */
public class GameEnvironment {
    // members
    private List<Collidable> obstacles;

    /**
     * constructor.
     */
    public GameEnvironment() {
        this.obstacles = new ArrayList<>();
    }

    /**
     * method that gets the list of the obstacles.
     *
     * @return the list of the obstacles.
     */
    public List<Collidable> getObstacles() {
        return this.obstacles;
    }

    /**
     * method that adds a given collidable object to the game environment.
     *
     * @param c the collidable object.
     */
    public void addCollidable(Collidable c) {
        this.obstacles.add(c);
    }

    /**
     * method that removes a given collidable object from the game environment.
     *
     * @param c the collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.obstacles.remove(c);
    }

    /**
     * method that calculates the Collision Info of the Closest Collision
     * of the object on the trajectory.
     *
     * @param trajectory the trajectory of the object.
     * @return If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo info = new CollisionInfo();
        List<Point> intersections = new ArrayList<>();
        // for every obstacle - find the closest intersection point.
        for (Collidable obstacle : this.obstacles) {
            Point intersection = trajectory.
                    closestIntersectionToStartOfLine(obstacle.getCollisionRectangle());
            // add the intersection point to the list.
            if (intersection != null) {
                intersections.add(intersection);
            }
        }
        // if there are not any intersection points - return null.
        if (intersections.isEmpty()) {
            return null;
        }
        // get the closest point to the start, from all of those points.
        Point collisionPoint = Utilities.getClosestPoint(intersections,
                trajectory);
        info.setCollisionPoint(collisionPoint);
        Collidable closestObstacle;
        // search for the collidable object that match the point.
        for (Collidable obstacle : this.obstacles) {
            // check it is not null
            if (trajectory.
                    closestIntersectionToStartOfLine(
                            obstacle.getCollisionRectangle()) != null) {
                // check if the collidable object match the point
                if ((Utilities.doubleEquals(collisionPoint.getX(), trajectory.
                        closestIntersectionToStartOfLine(
                                obstacle.getCollisionRectangle()).getX())
                        && (Utilities.doubleEquals(collisionPoint.getY(),
                        trajectory.
                                closestIntersectionToStartOfLine(
                                        obstacle.getCollisionRectangle()).getY())))) {
                    closestObstacle = obstacle;
                    info.setCollisionObject(closestObstacle);
                    break;
                }
            }
        }
        return info;
    }
}
