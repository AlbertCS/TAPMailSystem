package Part2.observer;

import Part2.BaseClasses.MailBox;
import Part2.BaseClasses.Message;
import java.util.LinkedList;
import java.util.Set;

//Observer Pattern
public abstract class MailFilter {
    public abstract void update(LinkedList<Message> messageOrigninal, LinkedList<Message> messageResult);
}
