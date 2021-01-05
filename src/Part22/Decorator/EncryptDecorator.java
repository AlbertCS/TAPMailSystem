package Part22.Decorator;

import Part22.BaseClasses.Message;
import Part22.strategy.Strategy;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

public class EncryptDecorator extends Message {

    public EncryptDecorator(String subject, String body, String sender, String receiver, Strategy strategy, Strategy strategy1) throws NoSuchAlgorithmException, NoSuchPaddingException {
        super(subject, strategy1.doOperation(strategy.doOperation(body)), sender, receiver);
    }


}
