package animation;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * The class responsible for running the animations.
 * Initializes the GUI window and handles the animation loop.
 *
 * @author Raz Barda ID 208199299 EX6
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;

    /**
     * Instantiates a new Animation runner.
     *
     * @param framesPerSecond the frames per second
     */
    public AnimationRunner(int framesPerSecond) {
        // Create a new GUI window
        this.gui = new GUI("game", 800, 600);
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * Get the GUI object.
     *
     * @return the GUI
     */
    public GUI getGUI() {
        return this.gui;
    }

    /**
     * Run the animation.
     * Starts the animation loop and updates the GUI window.
     *
     * @param animation the animation to run
     */
    public void run(Animation animation) {
        long millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            // Timing
            long startTime = System.currentTimeMillis();
            // Get the drawing surface from the GUI
            DrawSurface d = gui.getDrawSurface();
            // Perform one frame of the animation on the drawing surface
            animation.doOneFrame(d);
            // Show the updated drawing surface on the GUI window
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long millisecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (millisecondLeftToSleep > 0) {
                try {
                    // Sleep to control the frames per second
                    Thread.sleep(millisecondLeftToSleep);
                } catch (InterruptedException e) {
                    // Handle interrupted sleep if needed
                }
            }
        }
    }
}
