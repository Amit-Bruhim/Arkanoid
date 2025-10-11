package observers;

import biuoop.DrawSurface;
import geometry.basicshapes.Point;
import geometry.advancedshapes.Rectangle;
import sprite.Sprite;
import general.Counter;
import java.awt.Color;

/**
 * observers.ScoreIndicator is a score board that shows how many points the player
 * achieved.
 */
public class ScoreIndicator implements Sprite {
    // members
    private Color backgroundColor;
    private Color textColor;
    private Counter score;
    private Rectangle board;

    /**
     * constructor.
     *
     * @param score the counter of the points.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
        Point p3 = new Point(0, 0);
        this.board = new Rectangle(p3, general.Constants.BIG_FRAME_WIDTH,
                30);
        textColor = Color.black;
        backgroundColor = Color.white;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(backgroundColor);
        d.fillRectangle((int) this.board.getX(),
                (int) this.board.getY(), (int) this.board.getWidth(),
                (int) this.board.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.board.getX(),
                (int) this.board.getY(), (int) this.board.getWidth(),
                (int) this.board.getHeight());
        d.setColor(textColor);
        d.drawText(350, 20, "score:" + score.getValue(), 20);
    }

    @Override
    public void timePassed() {

    }
}
