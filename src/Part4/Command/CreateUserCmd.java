package Part4.Command;


import Part4.BaseClasses.MailStore;
import Part4.BaseClasses.MailSystem;

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
        mailSystem.createUser(userName, name, birthYear, mailStore);
    }
}
