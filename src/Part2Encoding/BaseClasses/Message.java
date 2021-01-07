package Part2Encoding.BaseClasses;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Albert Cañellas and Laura Romero.
 * Message class.
 */
public class Message {
    private String subject;
    private String body;
    private String sender;
    private String receiver;
    private String creationDate;

    /**
     * Constructor for message.
     * @param subject
     * @param body
     * @param sender
     * @param receiver
     */
    public Message(String subject, String body, String sender, String receiver) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.receiver = receiver;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss ZZZ");
        this.creationDate = simpleDateFormat.format(new Date());
    }

    // Second constructor
    public Message(String subject, String body, String sender, String receiver, String creationDate) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.receiver = receiver;
        this.creationDate = creationDate;
    }


    @Override
    public String toString() {
        return  subject+";"+body+";"+sender+";"+receiver+";"+creationDate;
    }
    public String saveFile() { return  subject+";"+body+";"+sender+";"+receiver+";"+creationDate+"\n"; }

    public String getCreationDate() {
        return creationDate;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }
}
