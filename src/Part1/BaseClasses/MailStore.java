package Part1.BaseClasses;

import java.io.IOException;
import java.util.LinkedList;

/**
 * @author Albert Cañellas and Laura Romero.
 * MailStore class
 */
public abstract class MailStore {

    /**
     * Retrieve all the messages that are intended for a certain user.
     * @param username: whose massages will return.
     */
    public abstract LinkedList<Message> getMail(String username);

    /**
     * Send a new message to a user.
     */
    public abstract void sendMail(Message message) throws IOException;


}
