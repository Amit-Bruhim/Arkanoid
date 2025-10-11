package sprite;

import biuoop.DrawSurface;
import geometry.basicshapes.Line;
import geometry.basicshapes.Point;
import general.Velocity;
import game.GameEnvironment;
import game.Game;
import collide.CollisionInfo;

import java.awt.Color;

/**
 * class that describes a ball.
 * a ball has: center, radius, color and velocity.
 */
public class Ball implements Sprite {
    // members
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment game;

    /**
     * method that remove the ball from the game.
     *
     * @param game the game.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }

    /**
     * method that adds the ball to the game.
     *
     * @param g the game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * method that changes the color of the ball.
     *
     * @param color the new color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * method that notify the ball that time passed.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * method that set the ball to a game.
     *
     * @param game the game.
     */
    public void setGameEnvironment(GameEnvironment game) {
        this.game = game;
    }


    /**
     * method that set the horizontal space.
     *
     * @param x the horizontal space
     */
    public void setX(double x) {
        this.center.setX(x);
    }

    /**
     * method that set the vertical space.
     *
     * @param y the vertical space
     */
    public void setY(double y) {
        this.center.setY(y);
    }

    /**
     * constructor that builds a ball.
     *
     * @param center the center of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * constructor that builds a ball.
     *
     * @param x     the horizontal space
     * @param y     the vertical space
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * constructor that builds a ball.
     *
     * @param x        the horizontal space
     * @param y        the vertical space
     * @param r        the radius of the ball
     * @param color    the color of the ball
     * @param velocity the velocity of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color,
                Velocity velocity) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = velocity;
    }

    /**
     * method that gets the horizontal space.
     *
     * @return the horizontal space
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * method that gets the vertical space.
     *
     * @return the vertical space
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * method that gets the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * method that gets the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * method that draws the ball on the surface.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.getX(), (int) this.getY(), this.radius);
    }

    /**
     * method that sets the velocity of the ball.
     *
     * @param v the velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * method that sets the velocity of the ball.
     *
     * @param dx the horizontal change
     * @param dy the vertical change
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * method that get the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * function that make sure that the ball stays in a frame.
     *
     * @param x1 the x of the starting point of the frame
     * @param y1 the y of the starting point of the frame
     * @param x2 the x of the end point of the frame
     * @param y2 the y of the end point of the frame
     */
    public void stayInFrame(int x1, int y1, int x2, int y2) {
        // change the horizontal direction if required
        if (this.getX() + this.radius >= x2 || this.getX() - this.radius <= x1) {
            this.velocity.changeDX(-1 * this.velocity.getDX());
        }
        // change the vertical direction if required
        if (this.getY() + this.radius >= y2 || this.getY() - this.radius <= y1) {
            this.velocity.changeDY(-1 * this.velocity.getDY());
        }
    }

    /**
     * method that updates the location of the ball.
     */
    public void moveOneStep() {
        // get the next collision point
        Line trajectory = new Line(this.getX(), this.getY(),
                this.getX() + this.velocity.getDX(),
                this.getY() + this.velocity.getDY());
        CollisionInfo info = this.game.getClosestCollision(trajectory);
        // if there wasn't any collision, continue as normal.
        if (info == null || info.collisionObject() == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        // get the direction of the ball
        int xDirection = 1;
        if (this.velocity.getDX() < 0) {
            xDirection = -1;
        }
        int yDirection = 1;
        if (this.velocity.getDY() < 0) {
            yDirection = -1;
        }
        // move the ball to "almost" the hit point, but just slightly before it.
        this.setX(info.collisionPoint().getX() - (3 * xDirection));
        this.setY(info.collisionPoint().getY() - (3 * yDirection));
        this.velocity = info.collisionObject().hit(this, info.collisionPoint(),
                this.velocity);
    }

    /**
     * method that make sure that the ball stay outside the frames.
     *
     * @param frames the frames
     */
    public void stayOutside(int[][] frames) {
        // iterate over the frames
        for (int[] frame : frames) {
            // check if the ball entered the frame.
            if ((this.getX() + this.radius >= frame[0])
                    && (this.getX() - this.radius <= frame[2])
                    && (this.getY() + this.radius >= frame[1])
                    && (this.getY() - this.radius <= frame[3])) {
                // change the horizontal direction if required
                if ((this.getX() + this.radius >= frame[2])
                        || (this.getX() - this.radius <= frame[0])) {
                    this.velocity.changeDX(-1 * this.velocity.getDX());
                }
                // change the vertical direction if required
                if ((this.getY() + this.radius >= frame[3])
                        || (this.getY() - this.radius <= frame[1])) {
                    this.velocity.changeDY(-1 * this.velocity.getDY());
                }
            }
        }
    }

    /**
     * method that gets the x in the velocity.
     *
     * @return the x in the velocity.
     */
    public double getDX() {
        return this.velocity.getDX();
    }

    /**
     * method that gets the y in the velocity.
     *
     * @return the y in the velocity.
     */
    public double getDY() {
        return this.velocity.getDY();
    }
}