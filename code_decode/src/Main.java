import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/**
 * Created by Руслан on 07.10.2016.
 */
public class Main {
    public static  Map<Character, Double> freqEn = new HashMap<>();
    static {
        freqEn.put('a', 0.0804);
        freqEn.put('b', 0.0154);
        freqEn.put('c', 0.0306);
        freqEn.put('d', 0.0399);
        freqEn.put('e', 0.1251);
        freqEn.put('f', 0.0230);
        freqEn.put('g', 0.0196);
        freqEn.put('h', 0.0549);
        freqEn.put('i', 0.0726);
        freqEn.put('j', 0.0016);
        freqEn.put('k', 0.0067);
        freqEn.put('l', 0.0414);
        freqEn.put('m', 0.0253);
        freqEn.put('n', 0.0709);
        freqEn.put('o', 0.0760);
        freqEn.put('p', 0.0200);
        freqEn.put('q', 0.0011);
        freqEn.put('r', 0.0612);
        freqEn.put('s', 0.0654);
        freqEn.put('t', 0.0925);
        freqEn.put('u', 0.0271);
        freqEn.put('v', 0.0099);
        freqEn.put('w', 0.0192);
        freqEn.put('x', 0.0019);
        freqEn.put('y', 0.0173);
        freqEn.put('z', 0.0009);
    }

    public static  Map<Character, Double> freqRu = new HashMap<>();
    static {
        freqRu.put('а', 0.07998);
        freqRu.put('б', 0.01592);
        freqRu.put('в', 0.04533);
        freqRu.put('г', 0.01687);
        freqRu.put('д', 0.02977);
        freqRu.put('е', 0.08496);
        freqRu.put('ж', 0.0094);
        freqRu.put('з', 0.01641);
        freqRu.put('и', 0.07367);
        freqRu.put('й', 0.01208);
        freqRu.put('к', 0.03486);
        freqRu.put('л', 0.04343);
        freqRu.put('м', 0.03203);
        freqRu.put('н', 0.067);
        freqRu.put('о', 0.10983);
        freqRu.put('п', 0.02804);
        freqRu.put('р', 0.04746);
        freqRu.put('с', 0.05473);
        freqRu.put('т', 0.06318);
        freqRu.put('у', 0.02615);
        freqRu.put('ф', 0.00267);
        freqRu.put('х', 0.00966);
        freqRu.put('ц', 0.00486);
        freqRu.put('ч', 0.0145);
        freqRu.put('ш', 0.00718);
        freqRu.put('щ', 0.00361);
        freqRu.put('ъ', 0.00037);
        freqRu.put('ы', 0.01898);
        freqRu.put('ь', 0.01735);
        freqRu.put('э', 0.00331);
        freqRu.put('ю', 0.00639);
        freqRu.put('я', 0.02001);
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
            if (!letterSum.containsKey(encryptText.charAt(i))&&freqRu.containsKey(encryptText.charAt(i))){
                letterSum.put(encryptText.charAt(i), 1);
                sumOfAllLetters++;
            }else if (freqRu.containsKey(encryptText.charAt(i))) {
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
