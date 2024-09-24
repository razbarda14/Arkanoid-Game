package geometricshapes;

import java.util.ArrayList;
import java.util.List;

/**
 * The type animation.Rectangle.
 *
 * @author Raz Barda ID 208199299 EX6
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Constructor to create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a list of intersection points with the specified line.
     *
     * @param line the line
     * @return the java . util . list
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();
        // Calculate the four corners of the animation.Rectangle
        Point upperLeft = this.upperLeft;
        Point upperRight = this.getUpperRight();
        Point lowerLeft = this.getLowerLeft();
        Point lowerRight = this.getLowerRight();

        // Calculate the four sides of the animation.Rectangle
        Line top = new Line(upperLeft, upperRight);
        Line left = new Line(upperLeft, lowerLeft);
        Line bottom = new Line(lowerLeft, lowerRight);
        Line right = new Line(upperRight, lowerRight);

        // Check for intersection between the animation.Rectangle and the animation.Line
        if (top.isIntersecting(line)) {
            intersections.add(top.intersectionWith(line));
        }
        if (left.isIntersecting(line)) {
            intersections.add(left.intersectionWith(line));
        }
        if (bottom.isIntersecting(line)) {
            intersections.add(bottom.intersectionWith(line));
        }
        if (right.isIntersecting(line)) {
            intersections.add(right.intersectionWith(line));
        }

        return intersections;
    }


    /**
     * Returns the width of the rectangle
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets lower left.
     *
     * @return the lower left
     */
    public Point getLowerLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY() + height);
    }

    /**
     * Gets lower right.
     *
     * @return the lower right
     */
    public Point getLowerRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * Gets upper right.
     *
     * @return the upper right
     */
    public Point getUpperRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY());
    }
    /**
     * generates an array of 4 lines representing the inputted Rectangle.
     * @return array of Lines representing the Rectangle
     */
    public Line[] genRecLines() {
        Line[] lines = new Line[4];
        Point upperRight = getUpperRight();
        Point lowerLeft = getLowerLeft();
        Point lowerRight = getLowerRight();
        //0 - upper, 1 - bottom, 2 - left, 3 - right.
        lines[0] = new Line(upperLeft.getX(), upperLeft.getY(), upperRight.getX(), upperRight.getY());
        lines[1] = new Line(lowerLeft.getX(), lowerLeft.getY(), lowerRight.getX(), lowerRight.getY());
        double eps = 0.0001;
        lines[2] = new Line(upperLeft.getX(), upperLeft.getY() + eps, lowerLeft.getX(), lowerLeft.getY() - eps);
        lines[3] = new Line(upperRight.getX(), upperRight.getY() + eps, lowerRight.getX(), lowerRight.getY() - eps);
        return lines;
    }
}
