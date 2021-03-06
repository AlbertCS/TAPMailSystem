package Part1.BaseClasses;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * @author Albert Cañellas and Laura Romero.
 * MailStoreFile class, the mailStore that saves to a file.
 */
public class MailStoreFile implements MailStore{

    private File file = new File("mails.txt");
    private String fileName = "mails.txt";

    /**
     * Constructor MailStoreFile
     */
    public MailStoreFile() {

    }

    @Override
    public void sendMail(Message message) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.append(message.saveFile());
            writer.close();
        }catch (IOException s){
            System.out.println("Error: "+s);
        }
    }


    @Override
    public  LinkedList<Message> getMail(String username) {
        String line;
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
                        receivedMessages.add(new Message(subject, body, sender, receiver, dateSend));
                    }
                }
            }
        }catch (IOException s){
            System.out.println("Error: "+s);
        }
        return receivedMessages;
    }

    public void clearMailStore() throws IOException {
        FileWriter fwOb = new FileWriter("mails.txt", false);
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }


}
