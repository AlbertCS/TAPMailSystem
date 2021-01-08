package Part2Encoding.Decorator;

import Part1.BaseClasses.Message;
import Part2Encoding.BaseClasses.MailStore;
import Part2Encoding.strategy.OperationReverse;
import Part2Encoding.strategy.Strategy;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class ReverseDecorator implements MailStore {
    private MailStore mailStore;
    private Strategy reverse = new OperationReverse();

    public ReverseDecorator(MailStore mailStore) {
        super();
        this.mailStore = mailStore;
    }

    @Override
    public LinkedList<Message> getMail(String username) {
        //return mailStore.getMail(username).stream().map(m -> reverse.doOperation(m.getBody())).collect(Collectors.toCollection(LinkedList::new));

        return mailStore.getMail(username);
    }

    @Override
    public void sendMail(String subject, String body, String userName, String receiver) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException {
        mailStore.sendMail(subject, reverse.doOperation(body), userName, receiver);

    }

    @Override
    public void clearTheFile() throws IOException {
        mailStore.clearTheFile();
    }
}