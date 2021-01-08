package Part2.BaseClasses;

import Part1.BaseClasses.User;
import Part2.observer.MailFilter;
import Part1.BaseClasses.Message;

import Part1.BaseClasses.MailStore;


import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Albert Cañellas and Laura Romero.
 * MailBox class
 */
public class MailBox extends Part1.BaseClasses.MailBox implements Iterable<Message> {

    private LinkedList<Part1.BaseClasses.Message> receivedMessages = new LinkedList<>();
    private LinkedList<Part1.BaseClasses.Message> spamMessages = new LinkedList<>();
    private LinkedList<MailFilter> mailFilters = new LinkedList<>();



    private MailStore mailStore;
    private User user;

    public MailBox(MailStore mailStore, User user) {
        super(mailStore, user);
        this.mailStore = mailStore;
        this.user = user;
    }

    public void update(Comparator comparator){
        System.out.println("\nUpdating messages...");
        receivedMessages = mailStore.getMail(user.getUserName()).stream().collect(Collectors.toCollection(LinkedList::new));
        Collections.sort(receivedMessages, comparator);
        notifyAllMailFilters();

    }

    public void attach(MailFilter mailFilter){
        mailFilters.add(mailFilter);
    }

    public void notifyAllMailFilters(){
        for(MailFilter mailFilter : mailFilters){
            mailFilter.update(receivedMessages, spamMessages);
        }
    }

    public void listMail() {
        System.out.println("\nMessage list for "+user.getUserName()+":");
        for (Message msg:receivedMessages) {
            System.out.println(msg);
        }
    }

    public void listSpam() {
        System.out.println("\nMessage spam list for "+user.getUserName()+":");
        for (Message msg:spamMessages) {
            System.out.println(msg);
        }
    }

    public void sendMail(String subject, String body, String receiver) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException {
        mailStore.sendMail(new Message(subject, body, user.getUserName(), receiver));
        System.out.println("\nMail Send");
    }

    public void getMail(Comparator comparator){
        Collections.sort(receivedMessages, comparator);
        listMail();
    }

    public void filterUserMail(String user2){
        receivedMessages.stream().filter(message -> message.getSender().equals(user2)).forEach(System.out::println);
    }

    public void filterSubjectWord(String subject){
        receivedMessages.stream().filter(message -> message.getSubject().contains(subject)).forEach(System.out::println);
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

            if(index < receivedMessages.size()){
                return true;
            }
            return false;
        }

        @Override
        public Message next() {

            if(this.hasNext()){
                return receivedMessages.get(index++);
            }
            return null;
        }
    }


}
