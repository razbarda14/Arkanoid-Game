package geometricshapes;

/**
 * The type animation.Point.
 * The animation.Point class represents a point on a two-dimensional plane.
 *
 * @author Raz Barda ID 208199299 EX6
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * Constructs a new animation.Point with given coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     * distance -- return the distance of this point to the other point
     *
     * @param other the other
     * @return the double
     */
    public double distance(Point other) {
        double disx = this.x - other.x;
        double disy = this.y - other.y;
        return Math.sqrt(disx * disx + disy * disy);
    }

    /**
     * Equals boolean.
     * equals -- return true is the points are equal, false otherwise
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Point other) {
        return DoubleEqualityChecker.areEqual(other.x, other.y) && DoubleEqualityChecker.areEqual(this.x, this.y);
    }

    /**
     * Gets x.
     *
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y value of this point
     */
    public double getY() {
        return this.y;
    }
}