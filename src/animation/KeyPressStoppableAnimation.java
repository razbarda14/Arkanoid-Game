package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 * KeyPressStoppableAnimation class - in charge of what to do in case of pressed key.
 *
 * @author Raz Barda ID 208199299 EX6
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean stop;
    private boolean isAlreadyPressed;
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;

    /**
     * Constructor.
     *
     * @param sensor    - KeyboardSensor.
     * @param key       - String - the key pressed in the keyboard.
     * @param animation - Animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.stop = false;
        this.isAlreadyPressed = true;
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(key)) {
            if (this.isAlreadyPressed) {
                this.isAlreadyPressed = false;
            } else {
                this.stop = true;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}