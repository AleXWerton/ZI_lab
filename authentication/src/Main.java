import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by AleXWerton
 */
public class Main {
    public static void main(String[] args) throws Exception {
        readLogOrReg();

    }
    public static void readLogOrReg() throws Exception {
        String inFile = "";
        FileReader reader = new FileReader("C:\\Users\\Руслан\\workspace\\Authentication\\src\\login-regestration");
        while (reader.ready())
            inFile+=(char)reader.read();
        String[] parsed = inFile.split(System.lineSeparator());

        if (parsed.length==2){
            login(parsed);
        }else if ((parsed.length==3))
            registration(parsed);
        else
            System.out.println("error");
    }

    private static void registration(String[] data) throws Exception{
        if (!data[1].equals(data[2])){
            System.out.println("Пароли не совпадают");
            return;
        }

        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Руслан\\workspace\\Authentication\\src\\data");
        byte[] users = new byte[fileInputStream.available()];
        fileInputStream.read(users);
        fileInputStream.close();
        System.out.println(new String(users));
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Руслан\\workspace\\Authentication\\src\\data");


        fileOutputStream.write(users);
        if (users.length>0)
            fileOutputStream.write('\n');
        String salt = String.valueOf(new Date().getTime());
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update((data[1]+salt).getBytes()); // Change this to "UTF-16" if needed
        byte[] digest = md.digest();
        fileOutputStream.write(digest);
        fileOutputStream.write((data[0]+'\n'+salt).getBytes());
        fileOutputStream.close();

    }

    public static void login(String[] data) throws Exception{
                FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Руслан\\workspace\\Authentication\\src\\data");
        byte[] hash = new byte[32];
        while (fileInputStream.available()>0) {
            fileInputStream.read(hash);

            String login = "";
            String salt = "";
            int currentByte=fileInputStream.read();
            while (currentByte!='\n'){
                login+=(char)currentByte;
                currentByte=fileInputStream.read();
            }
            currentByte=fileInputStream.read();
            while (currentByte!='\n'&&currentByte!=-1){
                salt+=(char)currentByte;
                currentByte=fileInputStream.read();
            }
            if (login.equals(data[0])){
                System.out.println("here");
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update((data[1]+salt).getBytes()); // Change this to "UTF-16" if needed=

                byte[] sByte = md.digest();

                if (new String(sByte).equals(new String(hash))) {
                    System.out.println("success");
                    return;
                }
            }

            //reader.readLine();

        }
        System.out.println("wrong login or password");

    }

}
