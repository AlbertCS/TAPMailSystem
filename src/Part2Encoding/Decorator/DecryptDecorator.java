package Part2Encoding.Decorator;

import Part2Encoding.BaseClasses.Message;
import Part2Encoding.strategy.Strategy;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

public class DecryptDecorator extends Message {

    public DecryptDecorator(String subject, String body, String sender, String receiver, String date,Strategy strategy, Strategy strategy1) throws NoSuchAlgorithmException, NoSuchPaddingException {
        super(subject, strategy1.doOperation(strategy.doOperation(body)), sender, receiver, date);
    }


}