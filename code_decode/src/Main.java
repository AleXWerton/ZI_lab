import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/**
 * Created by AleXWerton
 */
public class Main {

    public static  Map<Character, Double> frequency = new HashMap<>();
    static {
        frequency.put('а', 0.07998);
        frequency.put('б', 0.01592);
        frequency.put('в', 0.04533);
        frequency.put('г', 0.01687);
        frequency.put('д', 0.02977);
        frequency.put('е', 0.08496);
        frequency.put('ж', 0.0094);
        frequency.put('з', 0.01641);
        frequency.put('и', 0.07367);
        frequency.put('й', 0.01208);
        frequency.put('к', 0.03486);
        frequency.put('л', 0.04343);
        frequency.put('м', 0.03203);
        frequency.put('н', 0.067);
        frequency.put('о', 0.10983);
        frequency.put('п', 0.02804);
        frequency.put('р', 0.04746);
        frequency.put('с', 0.05473);
        frequency.put('т', 0.06318);
        frequency.put('у', 0.02615);
        frequency.put('ф', 0.00267);
        frequency.put('х', 0.00966);
        frequency.put('ц', 0.00486);
        frequency.put('ч', 0.0145);
        frequency.put('ш', 0.00718);
        frequency.put('щ', 0.00361);
        frequency.put('ъ', 0.00037);
        frequency.put('ы', 0.01898);
        frequency.put('ь', 0.01735);
        frequency.put('э', 0.00331);
        frequency.put('ю', 0.00639);
        frequency.put('я', 0.02001);
        double sum = 0;

    }

    public static void main(String[] args) throws Exception {
        //System.out.println(coding());
        System.out.println(decrypt(coding()));

        //System.out.println((char)('a'+25));
    }
    int а;


    public static String coding() throws Exception{
        StringBuilder text = new StringBuilder(getTextFromFile(".\\src\\forCoding").toLowerCase());
        Map<Character, Character> letterCoding = new HashMap<>();
        for (int i = 31, letterFrom = 'а', letterTo = 'я'; i >= 0; i--, letterFrom++, letterTo--) {
            letterCoding.put((char)letterFrom, (char)letterTo);
        }
        for (int i = 0; i < text.length(); i++) {
            if (letterCoding.containsKey(text.charAt(i))){
                text.setCharAt(i, letterCoding.get(text.charAt(i)));
            }
        }
        putTextToFile(".\\src\\encryptText", String.valueOf(text));
        return String.valueOf(text);
    }

    private static void putTextToFile(String path, String text) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(path);
            fileWriter.write(text);
            fileWriter.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    public static String decrypt(String encryptText){
        String decryptText = "";
        int sumOfAllLetters=0;
        Map<Character, Integer> letterSum = new HashMap<>();
        for (int i = 0; i < encryptText.length(); i++) {
            if (!letterSum.containsKey(encryptText.charAt(i))&&frequency.containsKey(encryptText.charAt(i))){
                letterSum.put(encryptText.charAt(i), 1);
                sumOfAllLetters++;
            }else if (frequency.containsKey(encryptText.charAt(i))) {
                letterSum.put(encryptText.charAt(i), letterSum.get(encryptText.charAt(i)) + 1);
                sumOfAllLetters++;
            }
        }
        //double sum=0;
        Map<Character, Double> freqInText = new HashMap<>();
        for (Map.Entry<Character, Integer> entry: letterSum.entrySet()){
            freqInText.put(entry.getKey(), ((double)entry.getValue()/sumOfAllLetters));
            //sum+=((double)entry.getValue()/sumOfAllLetters);
        }
        for (int i = 0; i < encryptText.length(); i++) {
            if (freqInText.containsKey(encryptText.charAt(i))) {
                decryptText+=getNLetters(5,encryptText.charAt(i), freqInText);
            }else
                decryptText+=encryptText.charAt(i);
        }



        return decryptText;
    }

    public static String getNLetters(int n, char forThisLetter, Map<Character, Double> freqInText){
        double[] delta = new double[n];
        char[] letters  = new char[n];
        String result="";
        Map<Double, Character> sortedFreq = new TreeMap<Double, Character>();
        for (Map.Entry<Character, Double> entry: freqRu.entrySet()){
            sortedFreq.put(Math.abs(entry.getValue()-freqInText.get(forThisLetter)), entry.getKey());
        }
        Iterator<Double> freqIterator = sortedFreq.keySet().iterator();
        for (int i = 0; i < 5; i++) {
            result += sortedFreq.get(freqIterator.next());
        }
        if (n!=1) {
            result = "[" + result;
            result += "]";
        }
        return result;
    }

    public static String getTextFromFile(String path)throws Exception{
        FileReader fileReader = new FileReader(path);
        String textFromFile="";
        while (fileReader.ready()){
            textFromFile += (char) fileReader.read();
        }
        return textFromFile;
    }
}
