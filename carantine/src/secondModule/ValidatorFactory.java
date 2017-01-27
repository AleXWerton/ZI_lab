package secondModule;

import data.Data;
import data.Int;
import data.Str;

/**
 * Created by Руслан on 28.10.2016.
 */
public class ValidatorFactory {
    private Data data;

    public static AbstractValidator getValidator(Data data) throws IllegalAccessException {
        if (data instanceof Int)
            return new IntValidator();
        else if (data instanceof Str)
            return new StrValidator();
        else
            throw new IllegalArgumentException();
    }
}
