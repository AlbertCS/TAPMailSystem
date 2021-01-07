package Part4.Command;

import Part4.BaseClasses.MailBox;
import Part4.Comparator.MessageNewerComparator;

public class UpdateCmd implements Command {

    MailBox mailBox;
    MessageNewerComparator messageNewerComparator = new MessageNewerComparator();

    public UpdateCmd(MailBox mailBox) {
        this.mailBox = mailBox;
    }

    @Override
    public void execute() {
        mailBox.update(messageNewerComparator);
    }
}
