package observers;

import sprite.Ball;
import sprite.Block;
import game.Game;
import general.Counter;

/**
 * observers.BallRemover will be in charge of removing balls
 * and updating an available - balls counter.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param game           the game that the balls appear in.
     * @param remainingBalls the amount of remaining Balls.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * method thar increase general.Counter By 1.
     */
    public void increaseCounterBy1() {
        remainingBalls.increase(1);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        this.remainingBalls.decrease(1);
    }

}
