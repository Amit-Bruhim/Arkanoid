package general;

/**
 * general.Counter is a simple class that is used for counting things.
 */
public class Counter {
    private int amount;

    /**
     * constructor.
     */
    public Counter() {
        amount = 0;
    }

    /**
     * add number to current count.
     *
     * @param number the number.
     */
    public void increase(int number) {
        amount += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number
     */
    public void decrease(int number) {
        amount -= number;
    }

    /**
     * get current count.
     *
     * @return the current count.
     */
    public int getValue() {
        return amount;
    }
}