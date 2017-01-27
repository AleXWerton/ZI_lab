package dataPackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by AleXWerton
 */
public class DataWriterReader {
    private static String path = ".\\src\\dataPackage\\Data";

    public static void writeData(String data) {
        FileWriter fileWriter;
        try {
            fileWriter= new FileWriter(path);
            fileWriter.write(data);
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static String readData() {
        String result = "";
        FileReader reader;
        try {
            reader = new FileReader(path);
            while (reader.ready()) {
                result += (char)reader.read();
            }
            //result.substring(0,result.length()-2);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
