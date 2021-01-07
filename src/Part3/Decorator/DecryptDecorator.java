package Part3.Decorator;

import Part3.BaseClasses.Message;
import Part3.strategy.Strategy;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

public class DecryptDecorator extends Message {

    public DecryptDecorator(String subject, String body, String sender, String receiver, String date,Strategy strategy, Strategy strategy1) throws NoSuchAlgorithmException, NoSuchPaddingException {
        super(subject, strategy1.doOperation(strategy.doOperation(body)), sender, receiver, date);
    }


}