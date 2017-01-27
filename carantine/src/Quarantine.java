import firstModule.FirstModule;
import secondModule.SecondModule;
import thirdModule.ThirdModule;

import java.io.FileNotFoundException;

/**
 * Created by AleXWerton.
 */
public class Quarantine {
    public static void main(String[] args) throws FileNotFoundException {
        DataInputThread dataInputThread = new DataInputThread();
        Thread module1 = new Thread(FirstModule.getInstance());
        Thread module2 = new Thread(SecondModule.getInstance());
        Thread module3 = new Thread(ThirdModule.getInstance());
        module1.start();
        module2.start();
        module3.start();
        dataInputThread.start();
    }
}
