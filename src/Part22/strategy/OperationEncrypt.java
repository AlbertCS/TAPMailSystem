package Part22.strategy;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class OperationEncrypt implements Strategy{
    @Override
    public String doOperation(String body) throws NoSuchPaddingException, NoSuchAlgorithmException {
        String key = "IWantToPassTAP12"; // 128 bit key
        java.security.Key aesKey =
                new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");

        byte[] encrypted = new byte[0];
        try {
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            encrypted = cipher.doFinal(body.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(encrypted);
    }
}