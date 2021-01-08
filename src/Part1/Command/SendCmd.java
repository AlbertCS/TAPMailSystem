package Part1.Command;

import Part1.BaseClasses.MailBox;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Albert Cañellas and Laura Romero.
 * SendCmd Class
 */
public class SendCmd implements Command{

    private MailBox mailBox;
    private String subject;
    private String body;
    private String receiver;

    public SendCmd(MailBox mailBox, String receiver, String subject, String body) {
        this.mailBox = mailBox;
        this.subject = subject;
        this.body = body;
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        try {
            mailBox.sendMail(subject, body, receiver);
        } catch (IOException | NoSuchPaddingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
