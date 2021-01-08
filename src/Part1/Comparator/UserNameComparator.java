package Part1.Comparator;

import Part1.BaseClasses.Message;

import java.util.Comparator;

/**
 * @author Albert Cañellas and Laura Romero.
 * UserNameComparator Class
 */
public class UserNameComparator implements Comparator<Message> {
    @Override
    public int compare(Message o1, Message o2) {
        String user1 = o1.getSender();
        String user2 = o2.getSender();
        return user1.compareTo(user2);
    }
}
