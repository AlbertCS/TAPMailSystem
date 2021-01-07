package Part2Encoding.BaseClasses;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * @author Albert Cañellas and Laura Romero.
 * MailBox class
 */
public class MailBox implements Iterable<Message> {

    private LinkedList<Message> receivedMessages = new LinkedList<>();

    private MailStore mailStore;

    private User user;

    public MailBox(MailStore mailStore, User user) {
        this.mailStore = mailStore;
        this.user = user;
    }

    public void update(Comparator comparator){
        System.out.println("\nUpdating messages...");
        receivedMessages = mailStore.getMail(user.getUserName()).stream().collect(Collectors.toCollection(LinkedList::new));
        Collections.sort(receivedMessages, comparator);

    }

    public void listMail() {
        System.out.println("\nMessage list for "+user.getUserName()+":");
        for (Message msg:receivedMessages) {
            System.out.println(msg);
        }
    }

    public void sendMail(String subject, String body, String receiver) throws IOException {
        mailStore.sendMail(subject, body, user.getUserName(), receiver);
        System.out.println("\nMail Decorator Send");
    }

    public void getMail(Comparator comparator){
        Collections.sort(receivedMessages, comparator);
        listMail();
    }


    public LinkedList<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Iterator<Message> iterator() {
        return new MessageIterator();
    }

    private class MessageIterator implements Iterator<Message> {

        int index;

        @Override
        public boolean hasNext() {
            return index < receivedMessages.size();
        }

        @Override
        public Message next() {

            if(this.hasNext())
                return receivedMessages.get(index++);

            return null;
        }
    }


}
