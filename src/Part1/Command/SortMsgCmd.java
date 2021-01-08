package Part1.Command;

import Part1.BaseClasses.MailBox;
import Part1.Comparator.UserNameComparator;

/**
 * @author Albert Cañellas and Laura Romero.
 * SortMsgCmd Class
 */
public class SortMsgCmd implements Command{
    MailBox mailBox;
    UserNameComparator userNameComparator = new UserNameComparator();

    public SortMsgCmd(MailBox mailBox) {
        this.mailBox = mailBox;
    }

    @Override
    public void execute() {
        mailBox.getMail(userNameComparator);
    }
}
