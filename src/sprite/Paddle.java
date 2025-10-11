package sprite;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import geometry.basicshapes.Point;
import geometry.advancedshapes.Rectangle;
import game.Game;

import java.awt.Color;

/**
 * The sprite.Paddle is the player in the game.
 * It is a rectangle that is controlled by the arrow keys,
 * and moves according to the player key presses.
 */
public class Paddle implements Sprite, collide.Collidable {
    // members
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;

    /**
     * constructor.
     *
     * @param keyboard  the KeyboardSensor
     * @param rectangle the shape
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rectangle) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
    }

    /**
     * move the paddle left.
     */
    public void moveLeft() {
        // edge case - the paddle passed the border.
        if (this.rectangle.getX() - 7 + this.rectangle.getWidth() < 0) {
            this.rectangle.setUpperLeft(general.Constants.BIG_FRAME_WIDTH,
                    this.rectangle.getY());
            return;
        }

        // otherwise.
        this.rectangle.setUpperLeft(this.rectangle.getX() - 7,
                this.rectangle.getY());
    }

    /**
     * move the paddle right.
     */
    public void moveRight() {
        // edge case - the paddle passed the border.
        if (this.rectangle.getX() + 7
                > general.Constants.BIG_FRAME_WIDTH) {
            this.rectangle.setUpperLeft(-1 * this.rectangle.getWidth(),
                    this.rectangle.getY());
            return;
        }

        // otherwise.
        this.rectangle.setUpperLeft(this.rectangle.getX() + 7,
                this.rectangle.getY());
    }

    /**
     * method that tells the paddle that time passed.
     * the paddle will move to the right or to the left,
     * if required.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * the method draw the paddle on the surface.
     *
     * @param d the surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle((int) this.rectangle.getX(),
                (int) this.rectangle.getY(), (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    /**
     * method that return the shape of the paddle.
     *
     * @return the shape of the paddle.
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
        // get the original Speed.
        double originalSpeed = Math.sqrt(Math.pow(currentVelocity.getDX(), 2)
                + Math.pow(currentVelocity.getDY(), 2));
        int angle = 0;
        double xCollision = collisionPoint.getX();

        // divide the paddle for 5 sections, and get a different angle
        // for each one.
        if (xCollision >= rectangle.getX()
                && xCollision < rectangle.getX() + rectangle.getWidth() * 0.2) {
            angle = 300;
        }
        if (xCollision >= rectangle.getX() + rectangle.getWidth() * 0.2
                && xCollision < rectangle.getX() + rectangle.getWidth() * 0.4) {
            angle = 330;
        }
        if (xCollision >= rectangle.getX() + rectangle.getWidth() * 0.4
                && xCollision < rectangle.getX() + rectangle.getWidth() * 0.6) {
            currentVelocity.changeDY(-1 * currentVelocity.getDY());
            return currentVelocity;
        }
        if (xCollision >= rectangle.getX() + rectangle.getWidth() * 0.6
                && xCollision < rectangle.getX() + rectangle.getWidth() * 0.8) {
            angle = 30;
        }
        if (xCollision >= rectangle.getX() + rectangle.getWidth() * 0.8
                && xCollision <= rectangle.getX() + rectangle.getWidth()) {
            angle = 60;
        }
        return general.Velocity.fromAngleAndSpeed(angle, originalSpeed);
    }

    /**
     * method that adds the paddle to the game.
     *
     * @param g the game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}