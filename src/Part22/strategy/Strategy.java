package Part22.strategy;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;


public interface Strategy {
    String doOperation(String body) throws NoSuchPaddingException, NoSuchAlgorithmException;
}