package Part2Encoding.strategy;


import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public String executeStrategy(String body) throws NoSuchAlgorithmException, NoSuchPaddingException {
        return strategy.doOperation(body);
    }
}
