package removing;

import collidableShapes.Block;
import game.GameLevel;
import score.Counter;
import spritesandvelocity.Ball;

/**
 * The type Ball remover.
 * @author Raz Barda ID 208199299 EX6
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param gameLevel         - the game
     * @param removedBalls - the counter
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Decrease the count of remaining balls
        this.remainingBalls.decrease(1);
        // Remove the ball from the game
        this.gameLevel.removeSprite(hitter);
        hitter.removeFromGame(this.gameLevel);
    }
}
