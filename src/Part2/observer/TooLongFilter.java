package Part2.observer;

import Part2.BaseClasses.MailBox;
import Part2.BaseClasses.Message;

import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

public class TooLongFilter extends MailFilter {

    @Override
    public void update(LinkedList<Message> messageOrigninal, LinkedList<Message> messageResult) {
        messageOrigninal.stream().filter(message -> message.getBody().length()>20).collect(Collectors.toCollection(() -> messageResult));
        messageOrigninal.removeIf(message -> message.getBody().length()>20);
    }
}
