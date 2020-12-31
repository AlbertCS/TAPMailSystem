package Part1.BaseClasses;

import Part2.observer.SpamUserFilter;

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
    MailStore mailStore;
    User user;

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
    public java.util.Iterator<Message> iterator() {
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
