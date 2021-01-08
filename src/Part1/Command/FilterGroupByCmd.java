package Part1.Command;

import Part1.BaseClasses.MailSystem;
import Part1.BaseClasses.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Albert Cañellas and Laura Romero.
 * FilerGroupBy Class
 */
public class FilterGroupByCmd implements Command{

    private MailSystem mailSystem;
    private LinkedList messages;
    private String subject;

    public FilterGroupByCmd(String subject, MailSystem mailSystem, LinkedList messages) {
        this.mailSystem = mailSystem;
        this.messages = messages;
        this.subject = subject;
    }

    @Override
    public void execute() {
        Map<String, List<Message>> bySubject = mailSystem.grupSubject();
        bySubject.get(subject).stream().collect(Collectors.toCollection(()->messages));
    }
}
