package data;

/**
 * Created by AleXWerton
 */
public class Int extends Data {
    private int value;

    public Int(long enterTime, int value) {

        super(enterTime);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString()+"Int{" +
                "value=" + value +
                '}';
    }
}
