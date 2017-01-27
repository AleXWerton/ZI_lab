package data;

/**
 * Created by Руслан on 28.10.2016.
 */
public class Str extends Data{
    private String value;

    public Str(long enterTime, String value) {
        super(enterTime);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString()+"Str{" +
                "value='" + value + '\'' +
                '}';
    }
}
