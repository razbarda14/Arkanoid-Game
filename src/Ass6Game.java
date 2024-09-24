import animation.AnimationRunner;
import game.GameFlow;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.LevelInformation;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * The main class for running the game.
 * Initializes and starts the game with specified levels.
 * Supports running multiple levels in a specific order.
 *
 * @author Raz Barda ID 208199299 EX6
 */
public class Ass6Game {
    /**
     * The entry point of the application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        // Set the number of levels and create a list to store the level information
        int numOfLevels = 3;
        List<LevelInformation> levels = new ArrayList<>();
        LevelInformation[] nameLevels = new LevelInformation[3];

        // Initialize the level information objects
        nameLevels[0] = new Level1();
        nameLevels[1] = new Level2();
        nameLevels[2] = new Level3();

        if (args.length == 0) {
            // If no arguments are provided, add all levels to the list
            levels.addAll(Arrays.asList(nameLevels).subList(0, numOfLevels));
        } else {
            for (String arg : args) {
                // Add the specified levels based on the arguments
                if (arg.equals("1")) {
                    levels.add(nameLevels[0]);
                }
                if (arg.equals("2")) {
                    levels.add(nameLevels[1]);
                }
                if (arg.equals("3")) {
                    levels.add(nameLevels[2]);
                }
            }
            if (levels.isEmpty()) {
                // If no valid levels are specified, add all levels to the list
                levels.addAll(Arrays.asList(nameLevels).subList(0, numOfLevels));
            }
        }

        // Create the animation runner and game flow objects
        AnimationRunner runner = new AnimationRunner(50);
        GameFlow gameflow = new GameFlow(runner, runner.getGUI().getKeyboardSensor());

        // Run the levels using the game flow
        gameflow.runLevels(levels);

        // Exit the game
        System.exit(0);
    }
}
