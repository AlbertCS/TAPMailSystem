package Part1.BaseClasses;

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
    public abstract void sendMail(Message message) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException;


    void clearMailStore() throws IOException;
}
