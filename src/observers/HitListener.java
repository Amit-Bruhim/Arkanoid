package observers;

import sprite.Ball;
import sprite.Block;

/**
 * Objects that want to be notified of hit events, should implement the
 * observers.HitListener interface.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the sprite.Ball that's doing the hitting.
     *
     * @param beingHit the object being hit.
     * @param hitter   the sprite.Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
