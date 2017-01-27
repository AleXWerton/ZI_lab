import firstModule.FirstModule;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Руслан on 28.10.2016.
 */
public class DataInputThread extends Thread{
    BufferedReader bufferedReader;
    FirstModule firstModule = FirstModule.getInstance();

    DataInputThread() throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader("src\\InputData"));
    }
    @Override
    public void run() {
        while (true){
            try {
                String s =readPartFromFile();
                firstModule.addData(s);
                //System.out.println(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("File is empty");
                break;
            }

        }
    }
    public String readPartFromFile() throws Exception{

        String part="";
        for (int i = 0; i < 3; i++) {
            String oneLine = bufferedReader.readLine();
            if (oneLine==null) {
                interrupt();
                return "";
            }
            part+=oneLine+"\n";
        }
        part+=System.currentTimeMillis();
        //part.trim();
        return part;
    }
}

