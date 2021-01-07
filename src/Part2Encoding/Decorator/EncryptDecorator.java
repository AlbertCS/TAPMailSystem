package Part2Encoding.Decorator;

import Part2Encoding.BaseClasses.Message;
import Part2Encoding.strategy.Strategy;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

public class EncryptDecorator extends Message {

    public EncryptDecorator(String subject, String body, String sender, String receiver, Strategy strategy, Strategy strategy1) throws NoSuchAlgorithmException, NoSuchPaddingException {
        super(subject, strategy1.doOperation(strategy.doOperation(body)), sender, receiver);
    }


}
