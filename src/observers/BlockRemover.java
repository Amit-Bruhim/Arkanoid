package observers;

import sprite.Ball;
import sprite.Block;
import game.Game;
import general.Counter;

/**
 * a observers.BlockRemover is in charge of removing blocks from the game, as well as
 * keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param game            the game that the blocks appear in.
     * @param remainingBlocks counter that holds the amount of remaining Blocks.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * method thar increase general.Counter By 1.
     */
    public void increaseCounterBy1() {
        remainingBlocks.increase(1);
    }

    /**
     * Blocks that are hit should be removed
     * from the game.
     *
     * @param beingHit the object being hit.
     * @param hitter   the sprite.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.setColor(beingHit.getColor());
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}