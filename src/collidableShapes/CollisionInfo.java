package collidableShapes;

import geometricshapes.Point;

/**
 * The type Collision info.
 *
 * @author Raz Barda ID 208199299 EX6
 */
public class CollisionInfo {
    /**
     * The Collision point.
     */
    private final Point collisionPoint;
    /**
     * The Collision object.
     */
    private final Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * the point at which the collision occurs.
     *
     * @return the point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     *
     * @return the collidable
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

}