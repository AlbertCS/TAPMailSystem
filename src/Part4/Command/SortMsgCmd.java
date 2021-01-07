package Part4.Command;

import Part4.BaseClasses.MailBox;
import Part4.Comparator.UserNameComparator;

public class SortMsgCmd implements Command {
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
