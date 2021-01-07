package Part3;

import Part3.Comparator.MessageNewerComparator;
import Part3.BaseClasses.*;
import Part3.Comparator.UserNameComparator;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) throws IOException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss ZZZ");

        System.out.println("MAIL STORE REDIS");

        // 1. Initialize
        MailStoreFactory factory = new MailStoreFileFactory();

        MailStore mailStore = factory.createMailStore();
        //MailStoreRedis mailStore = MailStoreRedis.getInstance();
        MessageNewerComparator comparator = new MessageNewerComparator();

        // Clean redis server
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

        mailSystem.updateAllMessages(comparator);
        System.out.println("\n Printing mails");
        mailSystem.getAllMessages();


    }
}
