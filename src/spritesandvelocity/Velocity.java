package spritesandvelocity;

import geometricshapes.Point;

/**
 * The type spritesandvelocity.Velocity.
 * spritesandvelocity.Velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @author Raz Barda ID 208199299 EX6
 */

public class Velocity {
    // the change in position on the x-axis
    private final double dx;
    // the change in position on the y-axis
    private final double dy;

    /**
     * Constructor for spritesandvelocity.Velocity.
     *
     * @param dx the change in position on the x-axis
     * @param dy the change in position on the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Getter for the dx value.
     *
     * @return the dx value of the velocity
     */
    public double getDX() {
        return this.dx;
    }

    /**
     * Getter for the dy value.
     *
     * @return the dy value of the velocity
     */
    public double getDY() {
        return this.dy;
    }

    /**
     * Returns a new point with coordinates (x+dx, y+dy).
     *
     * @param p the original point
     * @return the new point after applying the velocity
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        return new Point(newX, newY);
    }

    /**
     * From angle and speed velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // double a = Math.toRadians(angle);
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        double dy = -1 * Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }

    /**
     * Gets angle.
     *
     * @return the angle
     */
    public double getAngle() {
        return Math.toDegrees(Math.atan2(-1 * this.dy, this.dx));
    }
}


