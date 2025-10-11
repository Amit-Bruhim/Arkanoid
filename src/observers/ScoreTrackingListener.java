package observers;

import sprite.Ball;
import sprite.Block;
import general.Counter;

/**
 * observers.ScoreTrackingListener keeps track on the score.
 */
public class ScoreTrackingListener implements HitListener {
    // members
    private Counter currentScore;

    /**
     * constructor.
     *
     * @param scoreCounter the counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}