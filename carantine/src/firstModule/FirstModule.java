package firstModule;

import secondModule.SecondModule;
import data.Data;
import data.Int;
import data.Str;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Руслан on 28.10.2016.
 */
public class FirstModule implements Runnable {
    SecondModule secondModule = SecondModule.getInstance();
    boolean stop = false;
    private static FirstModule ourInstance = new FirstModule();

    public static FirstModule getInstance() {
        return ourInstance;
    }

    private FirstModule() {
    }

    private ConcurrentLinkedQueue<String> data = new ConcurrentLinkedQueue<>();

    public void addData(String s){
        //System.out.println("AddDataMod1");
        data.add(s);

    }
    private void processBlock(String block){

        String[] partsOfBlock = block.split("\n");
        for (int i = 0; i < partsOfBlock.length-1; i++) {
            secondModule.addData(getType(Long.parseUnsignedLong(partsOfBlock[partsOfBlock.length-1]), partsOfBlock[i]));
        }

    }

    private Data getType(long enterTime, String data){
        try {
            return new Int(enterTime, Integer.parseInt(data));
        }catch (NumberFormatException ex){
            return new Str(enterTime, data);
        }
    }

    public void stop(){
        stop = true;
    }
    @Override
    public void run() {
        while (!stop){
            if (!data.isEmpty()){
                processBlock(data.poll());
            }
        }
    }
}
