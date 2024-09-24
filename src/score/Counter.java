package score;

/**
 * The type Counter.
 * @author Raz Barda ID 208199299 EX6
 */
public class Counter {
    private int count;

    /**
     * Instantiates a new Counter.
     *
     * @param num the num
     */
    public Counter(int num) {
        this.count = num;
    }

    /**
     * add number to current count.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * get current count.
     *
     * @return the value
     */
    public int getValue() {
        return this.count;
    }
}
