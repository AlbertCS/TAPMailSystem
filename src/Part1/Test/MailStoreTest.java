package Part1.Test;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

import Part1.BaseClasses.MailStore;
import Part1.BaseClasses.MailStoreMem;
import Part1.BaseClasses.Message;
import org.junit.Assert;
import org.junit.Test;

public class MailStoreTest {


    @Test
    public void setGetMailTest() throws NoSuchPaddingException, NoSuchAlgorithmException, IOException {
        Message msg = new Message("subject", "body", "sender", "receiver");
        MailStore mailStore = new MailStoreMem();
        mailStore.sendMail(msg);
        LinkedList<Message> result = mailStore.getMail("receiver");
        System.out.println("-> TESTING setGetMail...");
        Assert.assertTrue(msg==result.get(0));
    }

    @Test
    public void clearMailStoreTest() throws NoSuchPaddingException, NoSuchAlgorithmException, IOException {
        Message msg = new Message("subject", "body", "sender", "receiver");
        MailStore mailStore = new MailStoreMem();
        mailStore.sendMail(msg);
        mailStore.clearMailStore();
        System.out.println("-> TESTING clearMailStore...");
        Assert.assertTrue(mailStore.getMail("receiver").isEmpty());
    }
}