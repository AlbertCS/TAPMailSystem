package Part2.observer;


import Part1.BaseClasses.Message;
import java.util.LinkedList;


//Observer Pattern
public abstract class MailFilter {
    public abstract void update(LinkedList<Message> messageOrigninal, LinkedList<Message> messageResult);
}
