package spritesandvelocity;

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * The interface spritesandvelocity.Sprite.
 *
 * @author Raz Barda ID 208199299 EX6
 */
public interface Sprite {
    /**
     * Draw the sprite on the screen.
     *
     * @param d the surface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Add to game.
     *
     * @param gameLevel the game
     */
    void addToGame(GameLevel gameLevel);
}
