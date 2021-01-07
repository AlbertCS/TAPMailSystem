package Part3.BaseClasses;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MailSystem {

    private LinkedList<User> users = new LinkedList<>();
    private LinkedList<MailBox> mailBoxes = new LinkedList<>();
    private MailStore mailStore;

    public MailSystem(MailStore mailStore) {
        this.mailStore = mailStore;
    }

    public MailBox createUser(String userName, String name, int birthYear){
        User user = new User(userName, name, birthYear);
        MailBox mailBox = new MailBox(mailStore, user);
        users.add(user);
        mailBoxes.add(mailBox);
        return mailBox;
    }

    public boolean userExist(String userName){
        return mailBoxes.stream().anyMatch(mailBox -> mailBox.getUser().getUserName().equals(userName));
    }

    public MailBox getMailBox(String userName){
        return mailBoxes.stream().filter(mailBox -> mailBox.getUser().getUserName().equals(userName)).collect(CustomCollector.toSingleton());
    }

    public void updateAllMessages(Comparator comparator){
        mailBoxes.stream().forEach(mailBox -> mailBox.update(comparator));
    }

    public void getAllMessages(){
        mailBoxes.stream().forEach(MailBox::listMail);
    }

    public void getAllUsers(){
        System.out.println("\nAll users:");
        users.stream().forEach(user -> System.out.println(user.getUserName()));
    }

    public LinkedList<Message> filterAllMessagesSingleWord(){
        LinkedList<Message> messages =
                mailBoxes.stream().map(MailBox::getReceivedMessages).flatMap(l -> l.stream()).collect(Collectors.toCollection(LinkedList::new));
        return messages.stream().filter(message -> message.getSubject().split("\\w+").length == 0).collect(Collectors.toCollection(LinkedList::new));
    }



    public LinkedList<Message> filterAllMessagesYear(Predicate predicate){

        LinkedList<Message> messages =
                mailBoxes.stream().map(MailBox::getReceivedMessages).flatMap(l -> l.stream()).collect(Collectors.toCollection(LinkedList::new));
        LinkedList<User> users2000 =
                (LinkedList<User>) users.stream().filter(predicate).collect(Collectors.toCollection(LinkedList::new));
        //users.stream().filter( user -> user.getBirthYear() > 2000).collect(Collectors.toCollection(LinkedList::new));
        LinkedList<Message> messagesResu= new LinkedList<>();
        for(User user:users2000){
            messages.stream().filter(message -> message.getSender().equals(user.getUserName())).sequential().collect(Collectors.toCollection(() -> messagesResu));
        }
        return messagesResu;
    }

    public void countMessages(){
        long num = mailBoxes.stream().map(MailBox::getReceivedMessages).flatMap(l -> l.stream()).count();
        System.out.println("\nThe total number of messages is " +num);
    }

    public  void  avgMessages(){
        long num = mailBoxes.stream().map(MailBox::getReceivedMessages).flatMap(l -> l.stream()).count();
        System.out.println("\nThe average of messages is " +(num/ users.size()));
    }

    public Map<String, List<Message>> grupSubject(){
        LinkedList<Message> messages =
                mailBoxes.stream().map(MailBox::getReceivedMessages).flatMap(l -> l.stream()).collect(Collectors.toCollection(LinkedList::new));
        Map<String, List<Message>> bySubject
                = messages.stream()
                .collect(Collectors.groupingBy(Message::getSubject));
        return bySubject;
    }

    public int countWords(String userName){
        LinkedList<Message> messages =
                mailBoxes.stream().map(MailBox::getReceivedMessages).flatMap(l -> l.stream())
                        .filter(message -> message.getSender().equalsIgnoreCase(userName)).collect(Collectors.toCollection(LinkedList::new));
        int words=0;
        for(Message message:messages){
            words += message.getBody().split("\\s+").length;
        }
        return words;
    }




}
