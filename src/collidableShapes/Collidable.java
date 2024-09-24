package collidableShapes;

import geometricshapes.Point;
import geometricshapes.Rectangle;
import biuoop.DrawSurface;
import spritesandvelocity.Ball;
import spritesandvelocity.Velocity;
import java.awt.Color;
/**
 * The interface collidableShapes.Collidable.
 *
 * @author Raz Barda ID 208199299 EX6
 */
public interface Collidable {

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity.
     *
     * @param hitter          the hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Draw on.
     *
     * @param surface the surface
     */
    void drawOn(DrawSurface surface);

    /**
     * @return Color.
     */
    Color getColor();
}