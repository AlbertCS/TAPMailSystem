package Part3.Decorator;

import Part3.BaseClasses.Message;
import Part3.strategy.Strategy;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

public class EncryptDecorator extends Message {

    public EncryptDecorator(String subject, String body, String sender, String receiver, Strategy strategy, Strategy strategy1) throws NoSuchAlgorithmException, NoSuchPaddingException {
        super(subject, strategy1.doOperation(strategy.doOperation(body)), sender, receiver);
    }


}
