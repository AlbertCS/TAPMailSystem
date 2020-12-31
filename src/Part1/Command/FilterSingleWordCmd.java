package Part1.Command;

import Part1.BaseClasses.MailSystem;
import Part1.BaseClasses.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilterSingleWordCmd implements Command{

    private MailSystem mailSystem;
    private LinkedList messages;

    public FilterSingleWordCmd( MailSystem mailSystem, LinkedList messages) {
        this.mailSystem = mailSystem;
        this.messages = messages;
    }

    @Override
    public void execute() {
        mailSystem.filterAllMessagesSingleWord().stream().collect(Collectors.toCollection(()->messages));;
    }
}
