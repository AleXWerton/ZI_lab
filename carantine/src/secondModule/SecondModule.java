package secondModule;

import data.Data;
import thirdModule.ThirdModule;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by AleXWerton
 */
public class SecondModule implements Runnable {
    boolean stop = false;
    public ConcurrentLinkedQueue<Data> data = new ConcurrentLinkedQueue<>();
    ThirdModule thirdModule = ThirdModule.getInstance();
    private static SecondModule ourInstance = new SecondModule();

    public static SecondModule getInstance() {
        return ourInstance;
    }

    private SecondModule() {
    }


    public void addData(Data data) {

        this.data.add(data);
    }

    public void stop() {
        stop = true;
    }


    @Override
    public void run() {
        while (!stop) {
            //System.out.println(data.size());
            if (!data.isEmpty()) {

                try {
                    if (ValidatorFactory.getValidator(data.peek()).validate(data.peek())) {
                        thirdModule.addData(data.poll());
                    } else
                        data.poll();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
