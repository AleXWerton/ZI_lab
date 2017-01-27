package diffieHellman;

import dataPackage.DataWriterReader;

import java.math.BigInteger;


/**
 * Created by Руслан on 17.11.2016.
 */
public class Member {
    private int aPkey;
    private int nPkey;
    private int personalSecretKey;
    private int commonSecretKey;
    private int receivedEncodeSecretCode;

    public Member(int secretKey) {
        this.personalSecretKey = secretKey;
    }

    public Member(int aPkey, int nPkey, int secretKey) {
        this.aPkey = aPkey;
        this.nPkey = nPkey;
        this.personalSecretKey = secretKey;
    }

    public void sendPublicKey(){
        if (aPkey==0||nPkey==0) {
            System.out.println("aPkey = "+aPkey+" nPkey = "+nPkey);
            return;
        }
        String data = ""+aPkey+"\n"+nPkey;
        DataWriterReader.writeData(data);
    }
    public void readPublicKey(){
        String[] data = DataWriterReader.readData().split("\n");
        aPkey = Integer.parseInt(data[0]);
        nPkey = Integer.parseInt(data[1]);
    }
    public void sendEncodeSecretKey(){
        int result = new BigInteger(String.valueOf(aPkey)).modPow(new BigInteger(String.valueOf(personalSecretKey)), new BigInteger(String.valueOf(nPkey))).intValue();
        DataWriterReader.writeData(String.valueOf(result));
    }

    public void readEncodeSecretCode(){
        receivedEncodeSecretCode = Integer.parseInt(DataWriterReader.readData());
    }
    public void findCommonSecretKey(){
        commonSecretKey = new BigInteger(String.valueOf(receivedEncodeSecretCode)).modPow(new BigInteger(String.valueOf(personalSecretKey)), new BigInteger(String.valueOf(nPkey))).intValue();
        System.out.println(commonSecretKey);
    }

}
