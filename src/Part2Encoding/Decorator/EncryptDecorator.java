package Part2Encoding.Decorator;

import Part2Encoding.BaseClasses.MailStore;
import Part2Encoding.BaseClasses.Message;
import Part2Encoding.strategy.OperationDecrypt;
import Part2Encoding.strategy.OperationEncrypt;
import Part2Encoding.strategy.Strategy;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class EncryptDecorator extends MailStore {
    private MailStore mailStore;
    private Strategy encrypt = new OperationEncrypt();
    private Strategy decrypt = new OperationDecrypt();

    public EncryptDecorator(MailStore mailStore){
        super();
        this.mailStore = mailStore;
    }

    @Override
    public LinkedList<Message> getMail(String username) {
        return mailStore.getMail(username).stream().map(m -> decrypt.doOperation(m.getBody())).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public void sendMail(String subject, String body, String userName, String receiver) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException {
        mailStore.sendMail(subject, encrypt.doOperation(body), userName, receiver);
    }

    @Override
    public void clearTheFile() throws IOException {
        mailStore.clearTheFile();
    }
}
