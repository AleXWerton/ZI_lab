package thirdModule;

import data.Data;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by AleXWerton.
 */
public class ThirdModule implements Runnable{
    private boolean stop = false;
    private static ThirdModule ourInstance = new ThirdModule();

    public static ThirdModule getInstance() {
        return ourInstance;
    }

    private ConcurrentLinkedQueue<Data> validatedData = new ConcurrentLinkedQueue<>();

    public void addData(Data data){
        //System.out.println("added");
        validatedData.add(data);
    }

    private ThirdModule() {
    }
    public void stop(){
        stop = true;
    }
    @Override
    public void run() {
        while (!stop){
            if (!validatedData.isEmpty()){
                validatedData.peek().setExitTime(System.currentTimeMillis());
                System.out.println(validatedData.poll());
            }
        }
    }
}
