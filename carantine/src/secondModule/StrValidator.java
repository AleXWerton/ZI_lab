package secondModule;

import data.Data;
import data.Str;

/**
 * Created by AleXWerton
 */
public class StrValidator extends AbstractValidator {
    @Override
    public boolean validate(Data data) {
        Str str =(Str)data;
        if (str.getValue().contains("а"))
            return false;
        else return true;
    }
}

