package spritesandvelocity;

import geometricshapes.Line;
import geometricshapes.Point;
import geometricshapes.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidableShapes.Collidable;
import game.GameLevel;

import java.awt.Color;


/**
 * The type spritesandvelocity.Paddle.
 * The type callableShapes.Block.
 *
 * @author Raz Barda ID 208199299 EX6
 */
public class Paddle implements Sprite, Collidable {
    private final int paddleHeight;
    private final int paddleWidth;
    private static final int PADDLE_SPEED = 5;
    private Rectangle paddleRectangle;
    private final biuoop.KeyboardSensor keyboard;
    private final double leftBound;
    private final double rightBound;
    private final Color color;

    /**
     * Instantiates a new spritesandvelocity.Paddle.
     *
     * @param paddleRectangle the paddle rectangle
     * @param keyboard        the keyboard
     * @param leftBound       the left bound
     * @param rightBound      the right bound
     * @param color           the color
     * @param paddleHeight    the paddle height
     * @param paddleWidth     the paddle width
     */
    public Paddle(Rectangle paddleRectangle, KeyboardSensor keyboard, int leftBound, int rightBound, Color color,
                  int paddleHeight, int paddleWidth) {
        this.paddleRectangle = paddleRectangle;
        this.keyboard = keyboard;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.color = color;
        this.paddleHeight = paddleHeight;
        this.paddleWidth = paddleWidth;
    }


    /**
     * Move the paddle left.
     */
    public void moveLeft() {
        // Check if the paddle is already at the left bound
        if (paddleRectangle.getUpperLeft().getX() - PADDLE_SPEED >= leftBound) {
            // Update the paddle's location
            Point newUpperLeft = new Point(paddleRectangle.getUpperLeft().getX() - PADDLE_SPEED,
                    paddleRectangle.getUpperLeft().getY());
            paddleRectangle = new Rectangle(newUpperLeft, paddleWidth, paddleHeight);
        }
    }

    /**
     * Move the paddle right.
     */
    public void moveRight() {
        // Check if the paddle is already at the right bound
        if (paddleRectangle.getUpperLeft().getX() + PADDLE_SPEED + paddleWidth <= rightBound) {
            // Update the paddle's location
            Point newUpperLeft = new Point(paddleRectangle.getUpperLeft().getX() + PADDLE_SPEED,
                    paddleRectangle.getUpperLeft().getY());
            paddleRectangle = new Rectangle(newUpperLeft, paddleWidth, paddleHeight);
        }
    }

    /**
     * Implement the timePassed method from the spritesandvelocity.Sprite interface.
     * Move the paddle left or right if the corresponding key is pressed.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Add the paddle to the game as a sprite and a collidable object.
     *
     * @param gameLevel the game to add the paddle to
     */
    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }


    /**
     * Implement the drawOn method from the spritesandvelocity.Sprite interface.
     * Draw the paddle on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the paddle
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.ORANGE);
        d.fillRectangle((int) paddleRectangle.getUpperLeft().getX(), (int) paddleRectangle.getUpperLeft().getY(),
                paddleWidth, paddleHeight);
        d.setColor(java.awt.Color.BLACK);
        d.drawRectangle((int) paddleRectangle.getUpperLeft().getX(), (int) paddleRectangle.getUpperLeft().getY(),
                paddleWidth, paddleHeight);
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * Get the collision rectangle of the paddle.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddleRectangle;
    }

    /**
     * generates an array of upper Line subLines.
     *
     * @param start  start Point of the upper Line
     * @param w      width of each Line (5 Lines)
     * @return toRet Line[] - array of 5 sub Lines
     */
    private Line[] genUpperLine(Point start, double w) {
        Line[] toRet = new Line[5];
        double yVal = start.getY();
        double leftEdge = start.getX();
        double widOfLine = w / 5;
        for (int i = 0; i < 5; i++) {
            double eps = 0.000000000001;
            toRet[i] = new Line(leftEdge + (widOfLine * (i + 1)), yVal, leftEdge + (widOfLine * (i + 2)) - eps,
                    yVal);
        }
        return toRet;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDX(), dy = currentVelocity.getDY();
        Line hitPoint = new Line(collisionPoint, collisionPoint);
        Line[] upperLine;
        Line[] recLines = this.getCollisionRectangle().genRecLines();
        //Ball hits upper Line of the Rectangle.
        upperLine = genUpperLine(recLines[0].start(), this.getCollisionRectangle().getWidth());
        if (hitPoint.isIntersecting(upperLine[0])) {
            //intersection with 1st part.
            //axe system is reversed, thus 150 = 300.
            return Velocity.fromAngleAndSpeed(150, 420);
        }
        //intersection with 2nd part, axe system is reversed, thus 120 = 330.
        if (hitPoint.isIntersecting(upperLine[1])) {
            return Velocity.fromAngleAndSpeed(120, 420);
        }
        //intersection with 4th part, axe system is reversed, thus 30 = 60;
        if (hitPoint.isIntersecting(upperLine[3])) {
            return Velocity.fromAngleAndSpeed(60, 420);
        }
        //intersection with 5th part, axe system is reversed, thus 60 = 30.
        if (hitPoint.isIntersecting(upperLine[4])) {
            return Velocity.fromAngleAndSpeed(30, 420);
        }
        //intersection with 3rd part, default behavior like a block.
        dy = -1 * dy;
        return new Velocity(dx, dy);
    }
}

