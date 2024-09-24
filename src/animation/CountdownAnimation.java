package animation;

import levels.LevelInformation;
import spritesandvelocity.SpriteCollection;
import score.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

import biuoop.Sleeper;

/**
 * A countdown animation that counts down from a specified number to 0.
 * Displays the count on the screen.
 * author Raz Barda ID 208199299 EX6s
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private final Counter countFrom;
    private final SpriteCollection gameScreen;
    private final LevelInformation information;

    /**
     * Constructor.
     *
     * @param numOfSeconds - The duration of the countdown animation in seconds.
     * @param countFrom    - The number to count down from.
     * @param gameScreen   - The sprite collection representing the game screen.
     * @param info         - The level information.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, LevelInformation info) {
        this.gameScreen = gameScreen;
        // Convert seconds to milliseconds
        this.numOfSeconds = numOfSeconds * 500;
        this.countFrom = new Counter(countFrom);
        this.information = info;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int startCount = 3;
        information.drawBackground(d);
        Sleeper sleeper = new Sleeper();
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.GREEN);
        // Draw the count in the middle of the screen
        d.drawText(370, 350, String.valueOf(this.countFrom.getValue()), 100);
        if (countFrom.getValue() != startCount) {
            // Sleep for the specified duration
            sleeper.sleepFor((long) numOfSeconds);
        }
        // Decrease the count by 1
        this.countFrom.decrease(1);
    }

    @Override
    public boolean shouldStop() {
        // Stop the animation when count reaches -1
        return this.countFrom.getValue() == -1;
    }
}
