package geometricshapes;

import java.util.List;

/**
 * The type animation.Line.
 *
 * @author Raz Barda ID 208199299 EX6
 */
public class Line {
    /**
     * The Start.
     */
   private final Point start;
    /**
     * The End.
     */
    private final Point end;

    /**
     * Instantiates a new animation.Line.
     * Constructors
     *
     * @param start the start
     * @param end   the end
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new animation.Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        // Create two animation.Point objects using the coordinates provided
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Length double.
     * Return the length of the line
     *
     * @return the double
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Middle point.
     * Returns the middle point of the line
     *
     * @return the point
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Start point.
     * Returns the start point of the line
     *
     * @return the point
     */
    public Point start() {
        return start;
    }

    /**
     * End point.
     * Returns the end point of the line
     *
     * @return the point
     */
    public Point end() {
        return end;
    }

    /**
     * Is intersecting boolean.
     * Returns true if the lines intersect, false otherwise
     *
     * @param other the other
     * @return the boolean
     */
    public boolean isIntersecting(Line other) {
        return intersectionWith(other) != null;
    }


    /**
     * Intersection with point.
     * Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other the other
     * @return the point
     */
    public Point intersectionWith(Line other) {
        //The method first extracts the coordinates of the four endpoints of the two lines,
        //using the getX() and getY() methods of the animation.Point class.
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        double x3 = other.start().getX();
        double y3 = other.start().getY();
        double x4 = other.end().getX();
        double y4 = other.end().getY();
        //calculates the denominator of the two linear equations formed by the two lines
        double denominator = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        // Lines are parallel or coincident
        if (DoubleEqualityChecker.areEqual(denominator, 0.0)) {
            // If they have the same angle, it checks whether the two endpoints
            // are the same, and returns the common endpoint as the intersection point. Otherwise, it returns null.
            if (isSameAngle(other)) {
                if (start.equals(other.end())) {
                    return start;
                }
                if (end.equals(other.start())) {
                    return end;
                }
            } else {
                if (start.equals(other.start())) {
                    return start;
                }
                if (end.equals(other.end())) {
                    return end;
                }
            }
            return null;
        }

        double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denominator;
        double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denominator;
        //If both ua and ub are between 0 and 1, the intersection point is inside both line segments,
        if (ua >= 0 && ua <= 1 && ub >= 0 && ub <= 1) {
            // Lines intersect
            double intersectionX = x1 + ua * (x2 - x1);
            double intersectionY = y1 + ua * (y2 - y1);
            return new Point(intersectionX, intersectionY);
        }

        return null;
    }

    /**
     * isSameAngle is a helper method that returns a boolean
     * value indicating whether the given animation.Line object has the same angle as the current animation.Line object.
     *
     * @param other the other
     * @return the angle
     */
    public boolean isSameAngle(Line other) {
        Point p1 = this.start();
        Point p2 = this.end();
        Point p3 = other.start();
        Point p4 = other.end();
        //calculates the angle between the two lines using the arctan function
        double angle1 = Math.atan2(p2.getY() - p1.getY(), p2.getX() - p1.getX());
        double angle2 = Math.atan2(p4.getY() - p3.getY(), p4.getX() - p3.getX());
        return DoubleEqualityChecker.areEqual(angle1, angle2);
    }

    /**
     * Equals boolean.
     * equals -- return true is the lines are equal, false otherwise
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect the rect
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // Get the list of intersection points between the line and the rectangle.
        List<Point> intersections = rect.intersectionPoints(this);

        // If no intersection points exist, return null.
        if (intersections.isEmpty()) {
            return null;
        }

        // Initialize the closest intersection point and its distance from the start of the line.
        Point closestIn = intersections.get(0);
        double closestDis = start().distance(closestIn);

        // Iterate over all intersection points and update the closest intersection
        // point and its distance from the start of the line.
        for (Point placeP : intersections) {
            double distance = start().distance(placeP);
            if (distance < closestDis) {
                closestIn = placeP;
                closestDis = distance;
            }
        }

        // Return the closest intersection point.
        return closestIn;
    }
}