package animation;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 * @author Raz Barda ID 208199299 EX6
 */
public interface Animation {
    /**
     * is in charge of the logic.
     *
     * @param d the d
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    boolean shouldStop();
}