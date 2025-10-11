package collide;

import geometry.basicshapes.Point;
import geometry.advancedshapes.Rectangle;
import sprite.Ball;

/**
 * collide.Collidable interface describes all the objects,
 * that other objects, can collide with.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the shape
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  the collision points
     * @param currentVelocity the current general.Velocity of the object
     * @param hitter          the ball that hit the block.
     * @return the new velocity.
     */

    general.Velocity hit(Ball hitter, Point collisionPoint, general.Velocity currentVelocity);
}