package game;

import geometricshapes.Line;
import geometricshapes.Point;
import geometricshapes.Rectangle;
import biuoop.DrawSurface;
import collidableShapes.Collidable;
import collidableShapes.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Game environment.
 *
 * @author Raz Barda ID 208199299 EX6
 */
public class GameEnvironment {
    private final List<Collidable> collectable = new ArrayList<>();


    /**
     * add the given collidable to the environment.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        collectable.add(c);
    }
    /**
     * @return a list
     * getCollisions -- return the list of the collidable objects
     */
    public List<Collidable> getCollisions() {
        return this.collectable;
    }
    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Create an empty list to hold all of the potential collisions
        List<CollisionInfo> collisions = new ArrayList<>();

        // Iterate over all of the collidables in the game environment
        for (Collidable c : this.collectable) {
            // Get the collision rectangle for this collidable
            Rectangle rect = c.getCollisionRectangle();

            // Find the intersection point between the trajectory and the collision rectangle
            Point intersection = trajectory.closestIntersectionToStartOfLine(rect);

            // If there was an intersection, add a collidableShapes.CollisionInfo object for this collision to the list
            if (intersection != null) {
                CollisionInfo info = new CollisionInfo(intersection, c);
                collisions.add(info);
            }
        }

        // If there were no collisions, return null
        if (collisions.isEmpty()) {
            return null;
        }

        // Find the closest collision by iterating over the list of collisions
        // and comparing distances to the start of the trajectory
        CollisionInfo closest = collisions.get(0);
        for (CollisionInfo c : collisions) {
            if (trajectory.start().distance(c.collisionPoint())
                    < trajectory.start().distance(closest.collisionPoint())) {
                closest = c;
            }
        }

        // Return the collidableShapes.CollisionInfo object for the closest collision
        return closest;
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        for (Collidable collidable : collectable) {
            collidable.drawOn(d);
        }
    }
    /**
     * @param c - the Collidabl
     * remove the Collidabl.
     */
    public void removeCollidable(Collidable c) {
        this.collectable.remove(c);
    }
}