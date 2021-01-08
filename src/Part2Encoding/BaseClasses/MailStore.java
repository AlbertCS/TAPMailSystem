package Part2Encoding.BaseClasses;

import Part1.BaseClasses.Message;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

/**
 * @author Albert Cañellas and Laura Romero.
 * MailStore class
 */
public interface MailStore {

    /**
     * Retrieve all the messages that are intended for a certain user.
     * @param username: whose massages will return.
     */
    public abstract LinkedList<Message> getMail(String username);

    /**
     * Send a new message to a user.
     */
    public abstract void sendMail(String subject, String body, String userName, String receiver) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException;

    public abstract void clearTheFile() throws IOException;

}
