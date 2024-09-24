package collidableShapes;

import geometricshapes.Point;
import biuoop.DrawSurface;
import game.GameLevel;
import geometricshapes.Rectangle;
import removing.HitListener;
import removing.HitNotifier;
import spritesandvelocity.Ball;
import spritesandvelocity.Sprite;
import spritesandvelocity.Velocity;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * The type collidableShapes.Block.
 *
 * @author Raz Barda ID 208199299 EX6
 */
public class Block implements Collidable, Sprite, HitNotifier {
    /**
     * The Hit listeners.
     */
    private final List<HitListener> hitListeners;
    private final Rectangle rectangle;
    private final Color color;


    /**
     * Instantiates a new collidableShapes.Block.
     *
     * @param rectangle the rectangle
     * @param color     the color
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDX();
        double dy = currentVelocity.getDY();
        double blockLeftX = this.rectangle.getUpperLeft().getX();
        double blockRightX = this.rectangle.getUpperRight().getX();
        double blockTopY = this.rectangle.getUpperLeft().getY();
        double blockBottomY = this.rectangle.getLowerLeft().getY();

        // Check which side of the block was hit
        if (collisionPoint.getY() == blockTopY || collisionPoint.getY() == blockBottomY) {
            dy = -dy;
        } else if (collisionPoint.getX() == blockLeftX || collisionPoint.getX() == blockRightX) {
            dx = -dx;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * Draws the block on the given surface.
     *
     * @param surface the surface to draw the block on
     */
    public void drawOn(DrawSurface surface) {
        // set the block's color
        surface.setColor(this.color);

        // draw the block
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        // set the block's color
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * Remove from game.
     *
     * @param gameLevel game remove the block from the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * notify all the registered HitListener objects by calling their hitEvent method.
     *
     * @param hitter game remove the block from the game.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
