package Part2;

import Part2.BaseClasses.*;
import Part2.Comparator.MessageNewerComparator;
import Part2.Comparator.UserNameComparator;
import Part2.observer.SpamUserFilter;
import Part2.observer.TooLongFilter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Predicate;

public class Main {
    //Main part 2

    public static void main(String[] args) throws IOException {

        System.out.println("MAIL SYSTEM P2");

        // 1. Initialize
        MailStore mailStore = new MailStoreMem();
        MessageNewerComparator comparator = new MessageNewerComparator();

        // 2. Create users
        MailSystem mailSystem = new MailSystem(mailStore);
        MailBox mailBoxLaura =  mailSystem.createUser("LauraRH", "Laura", 1997);
        MailBox mailBoxAlbert = mailSystem.createUser("AlbertCS", "Albert", 1997);
        MailBox mailBoxKoala = mailSystem.createUser("Koala spam", "Laura", 2001);

        // 3. Send messages
        mailBoxLaura.sendMail("Dubte Tap","Hola, suspendrem tap?","AlbertCS");
        mailBoxAlbert.sendMail("Dubte Tap","No, ho aprovarem segur que si que si que si que magraden les mandonguilles amb tomaquet","LauraRH");
        mailBoxAlbert.sendMail("No","No","LauraRH");
        mailBoxKoala.sendMail("Hola","Vull eucaliptus que tinc gana","LauraRH");
        mailBoxKoala.sendMail("Albert!!","La laura no em dona de menjar","AlbertCS");
        mailBoxLaura.sendMail("Hola","Avui fa bon dia? Ja se que ningu ho ha demanat","AlbertCS");

        //mailBoxAlbert.attach(new SpamUserFilter());
        //mailBoxKoala.attach(new SpamUserFilter());
        mailBoxLaura.attach(new SpamUserFilter());
        //mailBoxAlbert.attach(new TooLongFilter());
        //mailBoxKoala.attach(new TooLongFilter());
        mailBoxLaura.attach(new TooLongFilter());

        // 4. Update one mailBox
        mailBoxLaura.update(comparator);

        // 5. List the spam messages
        System.out.println("\nSpam messages:");
        mailBoxLaura.listSpam();

        // 6. List the messages
        System.out.println("\nMessage List:");
        mailBoxLaura.listMail();


    }
}
