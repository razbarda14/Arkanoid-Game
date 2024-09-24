package spritesandvelocity;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of sprites.
 * The type callableShapes.Block.
 * @author Raz Barda ID 208199299 EX6
 */
public class SpriteCollection {
    private final List<Sprite> sprites;

    /**
     * Constructor.
     * Initializes the collection to an empty ArrayList.
     */
    public SpriteCollection() {
        sprites = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Notifies all sprites that time has passed.
     */
    public void notifyAllTimePassed() {
        // Create a copy of the sprites list
        List<Sprite> spritesCopy = new ArrayList<>(sprites);
        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all sprites on a given surface.
     * @param d the surface to draw the sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
    /**
     * @param s - sprite
     * remove this sprite from the sprite list.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }
}
