package Part2.observer;

import Part2.BaseClasses.MailBox;
import Part2.BaseClasses.Message;

import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

public class SpamUserFilter extends MailFilter {
    @Override
    public void update(LinkedList<Message> messageOrigninal, LinkedList<Message> messageResult) {
        messageOrigninal.stream().filter(message -> message.getSender().contains("spam")).collect(Collectors.toCollection(() -> messageResult));
        messageOrigninal.removeIf(message -> message.getSender().contains("spam"));
    }
}
