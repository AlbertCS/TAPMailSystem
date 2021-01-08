package Part1.BaseClasses;



import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Albert Cañellas and Laura Romero.
 *  MailSystem Class
 */
public class MailSystem {

    private HashMap<User, MailBox> dictionary = new HashMap<>();
    private MailStore mailStore;

    public MailSystem(MailStore mailStore) {
        this.mailStore = mailStore;
    }

    public MailBox createUser(String userName, String name, int birthYear){
        User user = new User(userName, name, birthYear);
        MailBox mailBox = new MailBox(mailStore, user);
        dictionary.put(user, mailBox);
        return mailBox;
    }

    public boolean userExist(String userName){
        return dictionary.entrySet().stream().anyMatch(map -> map.getKey().getUserName().equals(userName));
    }

    public MailBox getMailBox(String userName){
        return dictionary.get(dictionary.keySet().stream().filter(user -> user.getUserName().equals(userName))
                .collect(CustomCollector.toSingleton()));
    }

    public void updateAllMessages(Comparator comparator){
        dictionary.entrySet().stream().forEach(map -> map.getValue().update(comparator));
    }

    public void getAllMessages(){
        dictionary.entrySet().stream().forEach(map -> map.getValue().listMail());
    }

    public void getAllUsers(){
        System.out.println("\nAll users:");
        dictionary.keySet().stream().forEach(System.out::println);
    }

    public LinkedList<Message> filterAllMessagesSingleWord(){
        LinkedList<Message> messages =
                dictionary.entrySet().stream().map(map -> map.getValue().getReceivedMessages())
                        .flatMap(l -> l.stream()).collect(Collectors.toCollection(LinkedList::new));
        return messages.stream().filter(message -> message.getSubject().split("\\w+").length == 0).collect(Collectors.toCollection(LinkedList::new));
    }


    public LinkedList<Message> filterAllMessagesYear(Predicate predicate){
        LinkedList<Message> messages =
                dictionary.entrySet().stream().map(map -> map.getValue().getReceivedMessages())
                        .flatMap(l -> l.stream()).collect(Collectors.toCollection(LinkedList::new));

        LinkedList<User> users2000 =
                (LinkedList<User>) dictionary.keySet().stream().filter(predicate).collect(Collectors.toCollection(LinkedList::new));

        LinkedList<Message> messagesResu= new LinkedList<>();
        for(User user:users2000){
            messages.stream().filter(message -> message.getSender().equals(user.getUserName())).sequential().collect(Collectors.toCollection(() -> messagesResu));
        }
        return messagesResu;
    }

    public void countMessages(){
        long num = dictionary.entrySet().stream().map(map -> map.getValue().getReceivedMessages()).flatMap(l  -> l.stream()).count();
        System.out.println("\nThe total number of messages is " +num);
    }

    public  void  avgMessages(){
        long num = dictionary.entrySet().stream().map(map -> map.getValue().getReceivedMessages()).flatMap(l  -> l.stream()).count();
        System.out.println("\nThe average of messages is " +(num / dictionary.size()));
    }

    public Map<String, List<Message>> grupSubject(){
        LinkedList<Message> messages =
                dictionary.entrySet().stream().map(map -> map.getValue().getReceivedMessages())
                        .flatMap(l -> l.stream()).collect(Collectors.toCollection(LinkedList::new));
        Map<String, List<Message>> bySubject
                = messages.stream()
                .collect(Collectors.groupingBy(Message::getSubject));
        return bySubject;
    }

    public int countWords(String userName){
        LinkedList<Message> messages =
                dictionary.entrySet().stream().map(map -> map.getValue().getReceivedMessages())
                        .flatMap(l -> l.stream()).filter(message -> message.getSender().equalsIgnoreCase(userName))
                        .collect(Collectors.toCollection(LinkedList::new));
        int words=0;
        for(Message message:messages){
            words += message.getBody().split("\\s+").length;
        }
        return words;
    }

}
