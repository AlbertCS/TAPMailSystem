package Part4.Command;

import Part4.BaseClasses.MailSystem;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class FilterSingleWordCmd implements Command {

    private MailSystem mailSystem;
    private LinkedList messages;

    public FilterSingleWordCmd(MailSystem mailSystem, LinkedList messages) {
        this.mailSystem = mailSystem;
        this.messages = messages;
    }

    @Override
    public void execute() {
        mailSystem.filterAllMessagesSingleWord().stream().collect(Collectors.toCollection(()->messages));;
    }
}
