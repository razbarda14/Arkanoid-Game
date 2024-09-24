package game;

import animation.CountdownAnimation;
import biuoop.KeyboardSensor;
import animation.Animation;
import animation.PauseScreen;
import animation.AnimationRunner;
import geometricshapes.Point;
import geometricshapes.Rectangle;
import biuoop.DrawSurface;
import collidableShapes.Block;
import collidableShapes.Collidable;
import levels.LevelInformation;
import levels.LevelName;
import removing.BallRemover;
import removing.BlockRemover;
import score.Counter;
import score.ScoreIndicator;
import score.ScoreTrackingListener;
import spritesandvelocity.Ball;
import spritesandvelocity.Sprite;
import spritesandvelocity.SpriteCollection;
import spritesandvelocity.Velocity;
import spritesandvelocity.Paddle;

import java.util.List;
import java.awt.Color;

/**
 * The type Game.
 *
 * @author Raz Barda ID 208199299 EX6
 */
public class GameLevel implements Animation {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int RADIUS = 5;
    private static final Point TOP = new Point(0, 30);
    private static final Point LEFT = new Point(0, 30);
    private static final Point RIGHT = new Point(790, 30);
    private static final int WIDTH_TOP = 800;
    private static final int HEIGHT_TOP = 10;
    private static final int WIDTH_RIGHT = 10;
    private static final int HEIGHT_RIGHT = 600;
    private static final int WIDTH_LEFT = 10;
    private static final int HEIGHT_LEFT = 600;
    private static final int PADDLE_HEIGHT = 10;
    private static final int LEFT_BOUND = WIDTH_LEFT;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter remainingBlocks = new Counter(0);
    private final Counter remainingBalls = new Counter(0);
    private final Counter score;
    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;
    private final LevelInformation information;
    private final ScoreIndicator scoreIndicator;

    /**
     * Instantiates a new Game.
     *
     * @param information the information
     * @param runner      the runner
     * @param score       the score
     */
    public GameLevel(LevelInformation information, AnimationRunner runner, Counter score) {
        this.information = information;
        this.runner = runner;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.keyboard = this.runner.getGUI().getKeyboardSensor();
        this.scoreIndicator = new ScoreIndicator(score);
        this.score = score;
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove collidable.
     *
     * @param c - the Collidabl remove the Collidabl.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s - the Sprite remove the Sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Create ball.
     */
    public void createBall() {
        Ball[] balls = new Ball[information.numberOfBalls()];
        Velocity[] velocity = new Velocity[information.numberOfBalls()];
        for (int i = 0; i < information.numberOfBalls(); i++) {
            balls[i] = new Ball(new Point(400, SCREEN_HEIGHT - 50), RADIUS, Color.GREEN);
            velocity[i] = information.initialBallVelocities().get(i);
            balls[i].setVelocity(velocity[i].getDX(), velocity[i].getDY());
            balls[i].addToGame(this);
            balls[i].setGameEnvironment(environment);
            this.remainingBalls.increase(1);
            this.sprites.addSprite(balls[i]);
        }
    }

    /**
     * Create block.
     */
    public void createBlock() {
        // Create the BlockRemover and add it as a hit listener to blocks
        BlockRemover blockRemove = new BlockRemover(this, this.remainingBlocks);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);
        List<Block> blocks = this.information.blocks();

        for (int i = 0; i < information.numberOfBlocksToRemove(); i++) {
            blocks.get(i).addToGame(this);
            blocks.get(i).addHitListener(blockRemove);
            blocks.get(i).addHitListener(scoreListener);
            this.remainingBlocks.increase(1);
        }
        // Create the border blocks (top, right, bottom, left) and add them to the game
        Block topBorder = new Block(new geometricshapes.Rectangle(TOP, WIDTH_TOP, HEIGHT_TOP), Color.GRAY);
        topBorder.addToGame(this);
        this.environment.addCollidable(topBorder);
        Block rightBorder = new Block(new geometricshapes.Rectangle(RIGHT, WIDTH_RIGHT, HEIGHT_RIGHT), Color.GRAY);
        rightBorder.addToGame(this);
        this.environment.addCollidable(rightBorder);
        Block leftBorder = new Block(new geometricshapes.Rectangle(LEFT, WIDTH_LEFT, HEIGHT_LEFT), Color.GRAY);
        leftBorder.addToGame(this);
        this.environment.addCollidable(leftBorder);
    }

    /**
     * Create paddle.
     */
    public void createPaddle() {
        // Create the paddle
        int paddleY = SCREEN_HEIGHT - PADDLE_HEIGHT;
        int paddleX = (information.paddleWidth());
        Paddle paddle = new Paddle(new Rectangle(new Point(380, paddleY), information.paddleWidth(), paddleY),
                this.runner.getGUI().getKeyboardSensor(), LEFT_BOUND, SCREEN_WIDTH - WIDTH_RIGHT, Color.YELLOW,
                paddleY, paddleX);
        paddle.addToGame(this);
        addSprite(paddle);
        this.environment.addCollidable(paddle);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        createBlock();
        createBall();
        createLevelName();
        createScoreIndicator();
        // Create the death-region block
        Block deathRegion = new Block(new Rectangle(new Point(0,
                SCREEN_HEIGHT + 10), SCREEN_WIDTH, 10), Color.RED);

        // Register the BallRemover as a listener of the death-region block
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);
        scoreIndicator.addToGame(this);
        createPaddle();
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        // Run the countdown animation
        CountdownAnimation countdownAnimation = new CountdownAnimation(2, 3, this.sprites, information);
        this.runner.run(countdownAnimation);
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //pause the game if we press p
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        if (this.remainingBlocks.getValue() > 0 && this.remainingBalls.getValue() > 0) {
            information.drawBackground(d);
            this.sprites.drawAllOn(d);
            this.sprites.notifyAllTimePassed();
            this.environment.drawOn(d);
            if (this.remainingBlocks.getValue() == 0) {
                score.increase(100);
            }
        } else {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !running;
    }

    /**
     * create the level name.
     */
    public void createLevelName() {
        Sprite levelName = new LevelName(this.information.levelName());
        this.sprites.addSprite(levelName);
    }

    /**
     * Remaining balls int.
     *
     * @return the number of balls in the game.
     */
    public int remainingBalls() {
        return remainingBalls.getValue();
    }

    /**
     * Remaining blocks' int.
     *
     * @return the number of blocks in the game.
     */
    public int remainingBlocks() {
        return remainingBlocks.getValue();
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score.getValue();
    }

    /**
     * create Score Indicator.
     */
    public void createScoreIndicator() {
        Sprite scoreIndicator = new ScoreIndicator(this.score);
        this.sprites.addSprite(scoreIndicator);
    }
}
