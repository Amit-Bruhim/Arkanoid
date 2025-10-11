package sprite;

import biuoop.DrawSurface;
import geometry.basicshapes.Point;
import geometry.advancedshapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class that describes a block.
 * block is a collidable rectangle.
 */
public class Block implements collide.Collidable, Sprite, observers.HitNotifier {
    // members
    private List<observers.HitListener> hitListeners;
    private Rectangle rectangle;
    private java.awt.Color color;

    /**
     * method that adds the block to the game.
     *
     * @param g the game.
     */
    public void addToGame(game.Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * method that gets the color of the ball.
     *
     * @return the color of the ball.
     */
    public Color getColor() {
        return this.color;
    }


    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<observers.HitListener> listeners =
                new ArrayList<observers.HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (observers.HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * notify the block that time passed.
     */
    public void timePassed() {
    }

    /**
     * the method draw the block on the surface.
     *
     * @param surface the surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getX(),
                (int) this.rectangle.getY(), (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rectangle.getX(),
                (int) this.rectangle.getY(), (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    /**
     * constructor.
     *
     * @param rectangle the rectangle
     * @param color     the color
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * method that return the shape of the block.
     *
     * @return the shape of the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * method that calculates the new velocity of the object,
     * that hit the block.
     *
     * @param collisionPoint  the collision points
     * @param currentVelocity the current general.Velocity of the object
     * @param hitter          the ball that hit the block.
     * @return the new velocity of the object
     */
    public general.Velocity hit(Ball hitter, Point collisionPoint,
                                general.Velocity currentVelocity) {
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        // get the (x,y) of the edges.
        Point[] corners = this.rectangle.getCorners(this.rectangle);
        double x1 = corners[0].getX();
        double x2 = corners[1].getX();
        double y1 = corners[0].getY();
        double y2 = corners[2].getY();
        // change the horizontal direction if required
        if (general.Utilities.doubleEquals(collisionPoint.getX(), x1)
                || general.Utilities.doubleEquals(collisionPoint.getX(), x2)) {
            currentVelocity.changeDX(-1 * currentVelocity.getDX());
        }
        // change the vertical direction if required
        if (general.Utilities.doubleEquals(collisionPoint.getY(), y1)
                || general.Utilities.doubleEquals(collisionPoint.getY(), y2)) {
            currentVelocity.changeDY(-1 * currentVelocity.getDY());
        }
        return currentVelocity;
    }

    /**
     * method that checks if a given ball's color match the block color.
     *
     * @param ball the given ball.
     * @return true - if there is a match, false - o.w.
     */
    public Boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }

    /**
     * method that remove the block from the game.
     *
     * @param game the game.
     */
    public void removeFromGame(game.Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    @Override
    public void addHitListener(observers.HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(observers.HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
