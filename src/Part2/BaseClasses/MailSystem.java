package Part2.BaseClasses;

import Part1.BaseClasses.MailStore;
import Part1.BaseClasses.Message;
import Part1.BaseClasses.User;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * @author Albert Cañellas and Laura Romero.
 * MailSystem Part2 Class
 */
public class MailSystem {

    private LinkedList<User> users = new LinkedList<>();
    private LinkedList<Part2.BaseClasses.MailBox> mailBoxes = new LinkedList<>();
    private MailStore mailStore;

    public MailSystem(MailStore mailStore) {
        this.mailStore = mailStore;
    }

    public Part2.BaseClasses.MailBox createUser(String userName, String name, int birthYear){
        User user = new User(userName, name, birthYear);
        Part2.BaseClasses.MailBox mailBox = new Part2.BaseClasses.MailBox(mailStore, user);
        users.add(user);
        mailBoxes.add(mailBox);
        return mailBox;
    }

    public int countWords(String userName){
        LinkedList<Message> messages =
                mailBoxes.stream().map(MailBox::getReceivedMessages ).flatMap(l -> l.stream())
                        .filter(message -> message.getSender().equalsIgnoreCase(userName)).collect(Collectors.toCollection(LinkedList::new));
        int words=0;
        for(Message message:messages){
            words += message.getBody().split("\\s+").length;
        }
        return words;
    }

}
