package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import score.Counter;

import java.awt.Polygon;
import java.awt.Color;

/**
 * An end screen animation displayed after the game ends.
 * Shows the final score and whether the player won or lost.
 * author Raz Barda ID 208199299 EX6
 */
public class EndScreen implements Animation {
    private boolean shouldStop;
    private final Counter score;
    private final boolean isWin;
    private final KeyboardSensor keyboard;

    /**
     * Constructor.
     *
     * @param score    - The player's score.
     * @param isWin    - Indicates whether the player won or lost.
     * @param keyboard - The keyboard sensor for detecting key presses.
     */
    public EndScreen(Counter score, boolean isWin, KeyboardSensor keyboard) {
        this.shouldStop = false;
        this.score = score;
        this.isWin = isWin;
        this.keyboard = keyboard;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.isWin) {
            // Display "Game Over" and the score
            d.drawText(d.getWidth() / 8, d.getHeight() / 6, "Game Over. Your score is "
                    + this.score.getValue(), 50);
            // Drawing a sad smiley face
            Color yellow = new Color(255, 235, 58);
            d.setColor(yellow);

            // Draw the face (circle)
            int faceX = 250;
            int faceY = 350;
            int faceRadius = 200;
            d.fillCircle(faceX, faceY, faceRadius);

            // Draw the eyes (circles)
            int eyeRadius = 30;
            int eyeY = faceY - faceRadius / 4;
            int leftEyeX = faceX - faceRadius / 2;
            int rightEyeX = faceX + faceRadius / 2;
            d.setColor(Color.BLACK);
            d.fillCircle(leftEyeX, eyeY, eyeRadius);
            d.fillCircle(rightEyeX, eyeY, eyeRadius);

            // Draw the mouth (line)
            int mouthStartX = faceX - faceRadius / 2;
            int mouthEndX = faceX + faceRadius / 2;
            int mouthY = faceY + faceRadius / 3;
            d.drawLine(mouthStartX, mouthY, mouthEndX, mouthY);

        } else {
            // Display "You Win!" and the score
            d.drawText(d.getWidth() / 8, d.getHeight() / 2, "You Win! Your score is "
                    + this.score.getValue(), 50);

            // Drawing a crown shape
            Color color1 = new Color(255, 235, 58);
            d.setColor(color1);
            int[] x = {250, 230, 400};
            int[] y = {350, 550, 550};
            Polygon p = new Polygon(x, y, 3);
            d.fillPolygon(p);
            int[] x2 = {400, 300, 500};
            int[] y2 = {350, 550, 550};
            Polygon p2 = new Polygon(x2, y2, 3);
            d.fillPolygon(p2);
            int[] x3 = {550, 400, 570};
            int[] y3 = {350, 550, 550};
            Polygon p3 = new Polygon(x3, y3, 3);
            d.fillPolygon(p3);
            Color color2 = new Color(255, 213, 0, 255);
            d.setColor(color2);
            d.fillCircle(250, 350, 10);
            d.fillCircle(400, 350, 10);
            d.fillCircle(550, 350, 10);
            d.setColor(Color.black);
            d.fillCircle(250, 350, 5);
            d.fillCircle(400, 350, 5);
            d.fillCircle(550, 350, 5);
        }

        // Check if the SPACE key is pressed to stop the animation
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.shouldStop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return shouldStop;
    }
}
