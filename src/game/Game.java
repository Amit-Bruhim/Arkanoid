package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import collide.Collidable;
import general.Constants;
import geometry.basicshapes.Point;
import geometry.advancedshapes.Rectangle;
import observers.BallRemover;
import observers.BlockRemover;
import observers.ScoreIndicator;
import observers.ScoreTrackingListener;
import sprite.Sprite;
import sprite.Block;
import sprite.Paddle;
import sprite.Ball;
import sprite.SpriteCollection;
import general.Counter;
import general.Velocity;


import java.awt.Color;
import java.util.Random;

/**
 * game.Game hold the sprites and the collidables,
 * and will be in charge of the animation.
 */
public class Game {
    // members
    private Counter counterBalls;
    private Counter score;
    private Counter counterBlocks;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;


    /**
     * constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Arkanoid", general.Constants.BIG_FRAME_WIDTH,
                general.Constants.BIG_FRAME_HEIGHT);
        this.counterBalls = new Counter();
        this.counterBlocks = new Counter();
        this.score = new Counter();
    }

    /**
     * method that adds collide.Collidable object to the game.
     *
     * @param c the collide .Collidable object.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * method that adds a new sprite to the list.
     *
     * @param s the new sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and sprite.Ball (and sprite.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        this.setBalls();
        this.setBorders();
        this.setBlocks();
        setPaddle();

    }

    /**
     * method that adds the paddle to the game.
     */
    public void setPaddle() {
        Point p1 = new Point(100, 565);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        Rectangle r1 = new Rectangle(p1, 80, 25);
        Paddle paddle = new Paddle(keyboard, r1);
        paddle.addToGame(this);
    }

    /**
     * method that adds the blocks to the screen.
     */
    public void setBlocks() {
        // set the starting blocks and their amount
        Random rand = new Random();
        int x = 50;
        int y = 40;
        int blocksAmount = 12;
        BlockRemover p88 = new BlockRemover(this, counterBlocks);
        ScoreTrackingListener myScore = new ScoreTrackingListener(score);
        // iterate over the amount of lines
        for (int i = 0; i < 6; i++) {
            x = 50 + (58 * i);
            Color color = new Color(rand.nextInt(256),
                    rand.nextInt(256), rand.nextInt(256));
            // create the blocks
            for (int j = 0; j < blocksAmount; j++) {
                Point p = new Point(x, y);
                Rectangle r = new Rectangle(p, 58, 30);
                Block block = new Block(r, color);
                block.addToGame(this);
                block.addHitListener(p88);
                p88.increaseCounterBy1();
                block.addHitListener(myScore);
                x += 58;
            }
            y += 30;
            blocksAmount -= 2;
        }
    }

    /**
     * method that adds the balls to the screen.
     */
    public void setBalls() {
        // first ball
        Random rand = new Random();
        int angle1 = rand.nextInt(360);
        Velocity velocity1 = Velocity.fromAngleAndSpeed(angle1, 6);
        Ball ball1 = new Ball(300, 505, 5, Color.red, velocity1);
        ball1.setGameEnvironment(this.environment);
        ball1.addToGame(this);
        counterBalls.increase(1);
        // second ball
        int angle2 = rand.nextInt(360);
        Velocity velocity2 = Velocity.fromAngleAndSpeed(angle2, 6);
        Ball ball2 = new Ball(400, 400, 5, Color.blue, velocity2);
        ball2.setGameEnvironment(this.environment);
        ball2.addToGame(this);
        counterBalls.increase(1);
        // third ball
        int angle3 = rand.nextInt(360);
        Velocity velocity3 = Velocity.fromAngleAndSpeed(angle3, 6);
        Ball ball3 = new Ball(550, 350, 5, Color.green, velocity3);
        ball3.setGameEnvironment(this.environment);
        ball3.addToGame(this);
        counterBalls.increase(1);
    }

    /**
     * method that add blocks at the border of the screen.
     */
    public void setBorders() {
        // set the borders:
        // right border
        Point p1 = new Point(general.Constants.BIG_FRAME_WIDTH - 10, 0);
        Rectangle r1 = new Rectangle(p1, 10, general.Constants.BIG_FRAME_HEIGHT - 35);
        Block b1 = new Block(r1, Color.gray);
        // left border
        Point p2 = new Point(0, 0);
        Rectangle r2 = new Rectangle(p2, 10, Constants.BIG_FRAME_HEIGHT - 35);
        Block b2 = new Block(r2, Color.gray);
        Point p3 = new Point(10, 30);
        Rectangle r3 = new Rectangle(p3, Constants.BIG_FRAME_WIDTH - 10 - 10,
                10);
        Block b3 = new Block(r3, Color.gray);
        Point p4 = new Point(0, Constants.BIG_FRAME_HEIGHT - 10);
        Rectangle r4 = new Rectangle(p4, Constants.BIG_FRAME_WIDTH,
                10);
        Block b4 = new Block(r4, Color.gray);
        BallRemover p88 = new BallRemover(this, counterBalls);
        b4.addHitListener(p88);
        Point p5 = new Point(0, Constants.BIG_FRAME_HEIGHT - 35);
        Rectangle r5 = new Rectangle(p5, 1,
                25);
        Block b5 = new Block(r5, Color.white);
        Point p6 = new Point(Constants.BIG_FRAME_WIDTH - 1,
                Constants.BIG_FRAME_HEIGHT - 35);
        Rectangle r6 = new Rectangle(p6, 1,
                25);
        Block b6 = new Block(r6, Color.white);
        // add the borders to the game
        b1.addToGame(this);
        b2.addToGame(this);
        b3.addToGame(this);
        b4.addToGame(this);
        b5.addToGame(this);
        b6.addToGame(this);
        ScoreIndicator board = new ScoreIndicator(score);
        this.addSprite(board);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (counterBalls.getValue() == 0 || counterBlocks.getValue() == 0) {
                if (counterBlocks.getValue() == 0) {
                    this.score.increase(100);
                }
                gui.close();
                return;
            }
        }
    }

    /**
     * method that removes collide.Collidable object from the game.
     *
     * @param c the collide.Collidable object.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * method that removes sprite object from the sprite collection.
     *
     * @param s the sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}