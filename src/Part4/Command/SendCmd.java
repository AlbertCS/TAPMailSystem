package Part4.Command;

import Part4.BaseClasses.MailBox;

import java.io.IOException;

public class SendCmd implements Command {

    private MailBox mailBox;
    private String subject;
    private String body;
    private String receiver;

    public SendCmd(MailBox mailBox, String receiver, String subject, String body) {
        this.mailBox = mailBox;
        this.subject = subject;
        this.body = body;
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        try {
            mailBox.sendMail(subject, body, receiver);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
