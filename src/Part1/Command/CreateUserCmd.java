package Part1.Command;


import Part1.BaseClasses.MailBox;
import Part1.BaseClasses.MailStore;
import Part1.BaseClasses.MailSystem;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class CreateUserCmd implements Command {

    private String userName;
    private String name;
    private int birthYear;
    private MailStore mailStore;
    private MailSystem mailSystem;


    public CreateUserCmd(String userName, String name, int birthYear, MailStore mailStore, MailSystem mailSystem){
        this.userName=userName;
        this.name=name;
        this.birthYear=birthYear;
        this.mailStore=mailStore;
        this.mailSystem = mailSystem;

    }

    @Override
    public void execute() {
        mailSystem.createUser(userName, name, birthYear);
    }
}
