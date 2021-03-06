package Part1.Command;

import Part1.BaseClasses.MailBox;
import Part1.Comparator.MessageNewerComparator;

/**
 * @author Albert Cañellas and Laura Romero.
 * UpdateCmd Class
 */
public class UpdateCmd implements Command{

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
