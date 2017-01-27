package RSA;

import dataPackage.DataWriterReader;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by AleXWerton.
 */
public class RSAMemberReceiver {
    private int p;
    private int q;
    private int n; //Модуль
    private int fi; //функция Эйлера
    private int e; //Открытая экспонента
    private int d; //закрытй ключ

    public static void main(String[] args) {

    }

    public RSAMemberReceiver(int p, int q) {
        this.p = p;
        this.q = q;
        n = p * q;
        fi = (q - 1) * (p - 1);
        e = findE(fi);
        System.out.println("e="+e+" fi="+ fi);
        d = findD(e, fi);
    }

    private int findE(int fi) {
        boolean[] isPrime = findPrime(fi);
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i < fi; i++) {
            if (isPrime[i]) {
                if (nod(i, fi) == 1) {
                    primes.add(i);
                }
            }
        }
        Collections.shuffle(primes);
        return primes.get(0);
    }

    private int findD(int e, int fi) {
        ArrayList<Integer> variants = new ArrayList<>();
        for (int i = fi + 1; i < 1000000; i += fi) {
            if (i % e == 0) {
                variants.add(i / e);
            }
        }
        Collections.shuffle(variants);
        //System.out.println(variants);
        return variants.get(0);
    }

    public void sendPublicKey(){
        String data = ""+e+"\n"+n;
        DataWriterReader.writeData(data);
    }

    public String getAndEncodeData(){
        String[] encodeBytes = DataWriterReader.readData().split(" ");
        char[] decodeBytes = new char[encodeBytes.length];
        for (int i = 0; i < encodeBytes.length; i++) {
            decodeBytes[i] = (char) new BigInteger(String.valueOf(encodeBytes[i])).modPow(new BigInteger(String.valueOf(d)), new BigInteger(String.valueOf(n))).intValue();
            //System.out.println(new BigInteger(String.valueOf(encodeBytes[i])).modPow(new BigInteger(String.valueOf(e)), new BigInteger(String.valueOf(n))).intValue());
        }
        return new String(decodeBytes);
    }

    private boolean[] findPrime(int max) {
        boolean[] isPrime = new boolean[max];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        for (int i = 2; i * i < max; i++)
            if (isPrime[i])
                for (int j = i * i; j < max; j += i)
                    isPrime[j] = false;
        return isPrime;
    }

    private int nod(int a, int b) {
        while (a != b) {
            if (a > b)
                a = a - b;
            else
                b = b - a;
        }
        return a;
    }
}
