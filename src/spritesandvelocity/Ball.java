package spritesandvelocity;
import geometricshapes.Line;
import geometricshapes.Point;
import biuoop.DrawSurface;
import collidableShapes.Collidable;
import collidableShapes.CollisionInfo;
import game.GameLevel;
import game.GameEnvironment;

import java.awt.Color;

/**
 * The type spritesandvelocity.Ball.
 *
 * @author Raz Barda ID 208199299 EX6
 */
public class Ball implements Sprite {
    private GameEnvironment environment = new GameEnvironment();
    private Point center;
    private final int radius;
    private final Color color;
    private Velocity velocity;

    /**
     * Instantiates a new spritesandvelocity.Ball.
     *
     * @param center the center
     * @param radius the radius
     * @param color  the color
     */
    public Ball(Point center, int radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        int x = getX();
        int y = getY();
        int size = getSize();
        Color color = getColor();
        surface.setColor(color);
        surface.fillCircle(x, y, size);
    }

    @Override
    public void timePassed() {
        // Move the ball one step
        moveOneStep();
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * The method "moveOneStep" updates the position of the ball for one time step based on its current velocity.
     * Moves the ball one step forward based on its velocity and the boundaries of the screen.
     * If the ball hits the boundaries, its velocity is changed accordingly.
     */
    public void moveOneStep() {
        // Compute the ball trajectory
        Line trajectory = new Line(this.center, this.velocity.applyToPoint(this.center));
        // Check if the trajectory hits anything in the game environment
        CollisionInfo collision = this.environment.getClosestCollision(trajectory);

        // If no collision, move the ball to the end of the trajectory
        if (collision == null) {
            this.center = this.velocity.applyToPoint(this.center);
        } else {
            // Otherwise, there is a collision
            Point collisionPoint = collision.collisionPoint();
            Collidable collisionObject = collision.collisionObject();
            //  System.out.println("hello");
            // Move the ball just slightly before the collision point
            this.center = new Point(collisionPoint.getX() - this.velocity.getDX(),
                    collisionPoint.getY() - this.velocity.getDY());

            // Notify the hit object that a collision occurred and get the new velocity
            // Update the velocity to the new velocity returned by the hit() method
            this.velocity = collisionObject.hit(this, collisionPoint, this.velocity);
        }
    }

    /**
     * Set the game environment for the ball.
     *
     * @param environment the environment
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }
}
