package Part2Encoding.BaseClasses;

import Part2Encoding.Decorator.DecryptDecorator;
import Part2Encoding.Decorator.EncryptDecorator;
import Part2Encoding.strategy.OperationDecrypt;
import Part2Encoding.strategy.OperationEncrypt;
import Part2Encoding.strategy.OperationReverse;
import Part2Encoding.strategy.Strategy;

import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * @author Albert Cañellas and Laura Romero.
 * MailStoreFile class, the mailStore that saves to a file.
 */
public class MailStoreFile extends MailStore {

    private File file = new File("mails.txt");
    private String fileName = "mails.txt";
    private Strategy strategyR = new OperationReverse();
    private Strategy strategyE = new OperationEncrypt();
    private Strategy strategyD = new OperationDecrypt();


    public void clearTheFile() throws IOException {
        FileWriter fwOb = new FileWriter("mails.txt", false);
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }

    /**
     * Constructor MailStoreFile
     */
    public MailStoreFile() {
    }

    @Override
    public void sendMail(String subject, String body, String userName, String receiver) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            Message message = new EncryptDecorator(subject,  body,  userName,  receiver, strategyR, strategyE);
            writer.append(message.saveFile());
            writer.close();
        }catch (IOException s){
            System.out.println("Error: "+s);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    @Override
    public  LinkedList<Message> getMail(String username) {
        String line;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss ZZZ", Locale.getDefault());
        LinkedList<Message> receivedMessages = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokens = new StringTokenizer(line, ";");
                while (tokens.hasMoreTokens()){
                    String subject = tokens.nextToken();
                    String body = tokens.nextToken();
                    String sender = tokens.nextToken();
                    String receiver = tokens.nextToken();
                    String dateSend = tokens.nextToken();
                    if(receiver.equalsIgnoreCase(username)){
                        receivedMessages.add(new DecryptDecorator(subject, body, sender, receiver, dateSend, strategyD, strategyR));
                    }
                }
            }
        }catch (IOException s){
            System.out.println("Error: "+s);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return receivedMessages;
    }


}