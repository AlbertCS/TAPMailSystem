package Part3.BaseClasses;

import Part3.BaseClasses.MailStore;
import Part3.BaseClasses.Message;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Albert Cañellas and Laura Romero.
 * MailStoreMem class, the mailStore that saves to memory.
 */
public class MailStoreMem implements MailStore {

    List<Message> messages = new LinkedList<>();

    public MailStoreMem() {

    }

    @Override
    public void sendMail(Message message) {
        messages.add(message);
    }

    @Override
    public void clearMailStore() throws IOException {
        messages.clear();
    }

    @Override
    public LinkedList<Message> getMail(String username) {
        //messages.stream().filter(message -> message.getReceiver().equalsIgnoreCase(username)).forEach(System.out::println);
        return messages.stream().filter(message -> message.getReceiver().equalsIgnoreCase(username)).collect(Collectors.toCollection(LinkedList::new));
    }



}
