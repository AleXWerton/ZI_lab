package RSA;

import dataPackage.DataWriterReader;

import java.math.BigInteger;

/**
 * Created by Руслан on 18.11.2016.
 */
public class RSAMemberTransmitter {
    private int e, n;


    public void readPublicKey(){
        String[] data = DataWriterReader.readData().split("\n");
        e = Integer.parseInt(data[0]);
        n = Integer.parseInt(data[1]);
    }

    public void sendEncodeData(String str){
        char[] strBytes = str.toCharArray();
        int[] encodeBytes = new int[strBytes.length];
        String send = "";
        for (int i = 0; i < strBytes.length; i++) {
            encodeBytes[i] = new BigInteger(String.valueOf((int)strBytes[i])).modPow(new BigInteger(String.valueOf(e)), new BigInteger(String.valueOf(n))).intValue();
            send+=String.valueOf(encodeBytes[i])+" ";
        }
        DataWriterReader.writeData(send);
    }
}
