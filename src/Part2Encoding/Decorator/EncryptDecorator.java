package Part2Encoding.Decorator;

import Part1.BaseClasses.MailStore;
import Part1.BaseClasses.Message;
import Part2Encoding.strategy.OperationDecrypt;
import Part2Encoding.strategy.OperationEncrypt;
import Part2Encoding.strategy.Strategy;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class EncryptDecorator implements MailStore {
    private MailStore mailStore;
    private Strategy encrypt = new OperationEncrypt();
    private Strategy decrypt = new OperationDecrypt();

    public EncryptDecorator(MailStore mailStore){
        super();
        this.mailStore = mailStore;
    }

    @Override
    public LinkedList<Message> getMail(String username) {
        //return mailStore.getMail(username).stream().map(m -> decrypt.doOperation(m.getBody())).collect(Collectors.toCollection(LinkedList::new));
        return mailStore.getMail(username);
    }

    @Override
    public void sendMail(Message message) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException {
        mailStore.sendMail(new Message(message.getSubject(), encrypt.doOperation(message.getBody()), message.getSender(), message.getReceiver()));
    }

    @Override
    public void clearTheFile() throws IOException {
        mailStore.clearTheFile();
    }
}
