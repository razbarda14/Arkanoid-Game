package score;

import collidableShapes.Block;
import removing.HitListener;
import spritesandvelocity.Ball;
/**
 * The type Score tracking listener.
 * @author Raz Barda ID 208199299 EX6
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Increase the score by 5 for hitting a block
        currentScore.increase(5);
    }
}
