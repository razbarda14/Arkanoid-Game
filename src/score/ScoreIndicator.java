package score;

import biuoop.DrawSurface;
import game.GameLevel;
import spritesandvelocity.Sprite;

/**
 * The type Score indicator.
 * @author Raz Barda ID 208199299 EX6
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param scoreCounter the score counter
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.score = scoreCounter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // Draw the score on the screen at the desired position
        d.drawText(350, 20, "Score: " + score.getValue(), 20);
    }

    /**
     * No need for any action when time passes
     */
    public void timePassed() {

    }

    /**
     * Add to game.
     *
     * @param gameLevel the game
     */
    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
