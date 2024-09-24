package game;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import score.Counter;

import java.util.List;

/**
 * The GameFlow class manages the flow of the game, including running levels and displaying the end screen.
 * @author Raz Barda ID 208199299 EX6
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final Counter score = new Counter(0);

    private boolean isWin = true;

    /**
     * Constructs a new GameFlow.
     *
     * @param animationRunner The animation runner used to run the game animations.
     * @param keyboardSensor  The keyboard sensor used to detect key presses.
     */
    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboardSensor) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
    }

    /**
     * Runs the given list of levels.
     *
     * @param levels The list of levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            // Create a new GameLevel for the current level
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.score);

            // Initialize the level
            level.initialize();

            // Run the level until all blocks are destroyed or no balls are left
            while (level.remainingBlocks() != 0 && level.remainingBalls() != 0) {
                level.run();
            }

            // Check if the player lost by running out of balls
            if (level.remainingBalls() == 0) {
                this.isWin = false;
                break;
            }
        }

        // Create and display the end screen based on the player's score and win status
        EndScreen endScreen = new EndScreen(this.score, this.isWin, this.keyboardSensor);
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space", endScreen));
    }
}
