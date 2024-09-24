package geometricshapes;

/**
 * The type Double equality checker.
 * The type collidableShapes.Block.
 *
 * @author Raz Barda ID 208199299 EX6
 */
public class DoubleEqualityChecker {
    private static final double THRESHOLD = 1E-05;

    /**
     * Are equal boolean.
     *
     * @param x the x
     * @param y the y
     * @return the boolean
     */
    public static boolean areEqual(double x, double y) {
        return Math.abs(x - y) < THRESHOLD;
    }
}
