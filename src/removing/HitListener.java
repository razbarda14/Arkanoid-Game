package removing;

import collidableShapes.Block;
import spritesandvelocity.Ball;

/**
 * @author Raz Barda ID 208199299 EX6
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */

    void hitEvent(Block beingHit, Ball hitter);
}
