package secondModule;

import data.Data;
import data.Int;

/**
 * Created by Руслан on 28.10.2016.
 */
public class IntValidator extends AbstractValidator {
    @Override
    public boolean validate(Data data) {
        Int anInt = (Int) data;
        if (anInt.getValue()>10&&anInt.getValue()<10000)
            return true;
        return false;
    }
}
