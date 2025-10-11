package collide;

import geometry.basicshapes.Point;

/**
 * class collide.CollisionInfo contain all the relevant information
 * about the collision: the point and the object of the collision.
 */
public class CollisionInfo {
    // members
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor.
     */
    public CollisionInfo() {
    }

    /**
     * constructor.
     *
     * @param collisionPoint  the collision geometry.basicshapes.Point.
     * @param collisionObject the collision Object.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * method that gets the collision geometry.basicshapes.Point.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * method that gets the collidable object.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * method that sets the collision geometry.basicshapes.Point.
     *
     * @param collisionPoint the new collision geometry.basicshapes.Point.
     */
    public void setCollisionPoint(Point collisionPoint) {
        this.collisionPoint = collisionPoint;
    }

    /**
     * method that sets the Collision Object.
     *
     * @param collisionObject the new Collision Object.
     */
    public void setCollisionObject(Collidable collisionObject) {
        this.collisionObject = collisionObject;
    }
}