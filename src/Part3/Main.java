package Part3;

import Part1.BaseClasses.MailBox;
import Part1.BaseClasses.MailStore;
import Part1.BaseClasses.MailSystem;
import Part1.Comparator.MessageNewerComparator;
import Part3.Factory.MailStoreFactory;
import Part3.Factory.MailStoreFileFactory;
import Part3.Factory.MailStoreMemFactory;
import Part3.Factory.MailStoreRedisFactory;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Albert Cañellas and Laura Romero.
 * Main Part3
 */
public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException {

        System.out.println("MAIL SYSTEM P3");

        // 1. Initialize
        //MailStoreFactory factory = new MailStoreFileFactory();
        //MailStoreFactory factory = new MailStoreMemFactory();
        MailStoreFactory factory = new MailStoreRedisFactory();

        MailStore mailStore = factory.createMailStore();
        MessageNewerComparator comparator = new MessageNewerComparator();

        // Clean the mailStore
        mailStore.clearMailStore();

        // 2. Create users
        MailSystem mailSystem = new MailSystem(mailStore);
        MailBox mailBoxLaura =  mailSystem.createUser("LauraRH", "Laura", 1997);
        MailBox mailBoxAlbert = mailSystem.createUser("AlbertCS", "Albert", 1997);
        MailBox mailBoxKoala = mailSystem.createUser("Koala", "Laura", 2001);

        // 3. Send messages
        mailBoxLaura.sendMail("Dubte Tap","Hola, suspendrem tap?","AlbertCS");
        mailBoxAlbert.sendMail("Dubte Tap","No, ho aprovarem segur","LauraRH");
        mailBoxKoala.sendMail("Hola","Vull eucaliptus que tinc gana","LauraRH");
        mailBoxKoala.sendMail("Albert!!","La laura no em dona de menjar","AlbertCS");
        mailBoxLaura.sendMail("Hola","Avui fa bon dia? Ja se que ningu ho ha demanat","AlbertCS");

        // 4. Update messages
        mailSystem.updateAllMessages(comparator);

        // 5. Get messages
        mailSystem.getAllMessages();


    }
}
