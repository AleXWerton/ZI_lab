import RSA.RSAMemberReceiver;
import RSA.RSAMemberTransmitter;
import diffieHellman.Member;

import java.math.BigInteger;

/**
 * Created by AleXWerton
 */
public class Main {
    static Member member1 = new Member(7, 26, 11);
    static Member member2 = new Member(18);

    static RSAMemberReceiver rsaMemberReceiver = new RSAMemberReceiver(29, 23);
    static RSAMemberTransmitter rsaMemberTransmitter = new RSAMemberTransmitter();

    public static void main(String[] args) {
        String s = new String("Hello Word");
        String sDecode;
        rsaMemberReceiver.sendPublicKey();
        rsaMemberTransmitter.readPublicKey();
        rsaMemberTransmitter.sendEncodeData(s);
        sDecode=rsaMemberReceiver.getAndEncodeData();
        System.out.println(sDecode);

        for (int i = 0; i < sDecode.length(); i++) {
            System.out.print((int)sDecode.charAt(i)+" ");
        }
        //keyExchangeDiffie();
    }

    //public static void
    public static void keyExchangeDiffie() {
        member1.sendPublicKey();
        member2.readPublicKey();
        member1.sendEncodeSecretKey();
        member2.readEncodeSecretCode();
        member2.sendEncodeSecretKey();
        member1.readEncodeSecretCode();
        member1.findCommonSecretKey();
        member2.findCommonSecretKey();
    }


}
