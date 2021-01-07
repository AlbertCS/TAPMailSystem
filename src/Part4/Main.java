package Part4;

import Part4.BaseClasses.*;
import Part4.Comparator.MessageNewerComparator;
import Part4.Comparator.UserNameComparator;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("MAIL SYSTEM P4");

        // 1. Initialize
        MailStore mailStore = new MailStoreMem();
        MessageNewerComparator comparator = new MessageNewerComparator();

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

        mailSystem.updateAllMessages(comparator);

        mailSystem.getAllMessages();



    }
}
