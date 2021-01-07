package Part3.BaseClasses;

import Part3.Decorator.EncryptDecorator;
import Part3.BaseClasses.MailStore;
import Part3.BaseClasses.Message;
import Part3.Decorator.DecryptDecorator;
import Part3.strategy.OperationDecrypt;
import Part3.strategy.OperationEncrypt;
import Part3.strategy.OperationReverse;
import Part3.strategy.Strategy;

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
public class MailStoreFile implements MailStore {

    private File file = new File("mails.txt");
    private String fileName = "mails.txt";
    private Strategy strategyR = new OperationReverse();
    private Strategy strategyE = new OperationEncrypt();
    private Strategy strategyD = new OperationDecrypt();


    public void clearMailStore() throws IOException {
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

    @Override
    public void sendMail(Message message1) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            Message message = new EncryptDecorator(message1.getSubject(),  message1.getBody(),  message1.getSender(),  message1.getReceiver(), strategyR, strategyE);
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


}
