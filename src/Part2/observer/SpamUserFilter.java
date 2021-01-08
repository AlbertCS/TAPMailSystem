package Part2.observer;


import Part1.BaseClasses.Message;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * @author Albert Cañellas and Laura Romero.
 * SpamUserFilter Class
 */
public class SpamUserFilter extends MailFilter {
    @Override
    public void update(LinkedList<Message> messageOrigninal, LinkedList<Message> messageResult) {
        messageOrigninal.stream().filter(message -> message.getSender().contains("spam")).collect(Collectors.toCollection(() -> messageResult));
        messageOrigninal.removeIf(message -> message.getSender().contains("spam"));
    }
}
