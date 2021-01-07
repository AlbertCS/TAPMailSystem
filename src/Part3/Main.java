package Part3;

import Part3.Comparator.MessageNewerComparator;
import Part3.BaseClasses.*;
import Part3.Factory.MailStoreFactory;
import Part3.Factory.MailStoreRedisFactory;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("MAIL STORE");

        // 1. Initialize
        //MailStoreFactory factory = new MailStoreFileFactory();
        //MailStoreFactory factory = new MailStoreMemFactory();
        MailStoreFactory factory = new MailStoreRedisFactory();

        MailStore mailStore = factory.createMailStore();
        MessageNewerComparator comparator = new MessageNewerComparator();

        // Clean the mailStore
        mailStore.clearMailStore();

        // 2. Create users
        MailSystem mailSystem = new MailSystem();
        MailBox mailBoxLaura =  mailSystem.createUser("LauraRH", "Laura", 1997, mailStore);
        MailBox mailBoxAlbert = mailSystem.createUser("AlbertCS", "Albert", 1997, mailStore);
        MailBox mailBoxKoala = mailSystem.createUser("Koala", "Laura", 2001, mailStore);

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
