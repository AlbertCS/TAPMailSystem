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

    public static void main(String[] args) throws IOException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss ZZZ");

        System.out.println("MAIL STORE MEM");

        // 1. Initialize
        MailStore mailStore = new MailStoreMem();
        MessageNewerComparator comparator = new MessageNewerComparator();
        UserNameComparator comparator1 = new UserNameComparator();



        // 2. Create users
        MailSystem mailSystem = new MailSystem();
        MailBox mailBoxLaura =  mailSystem.createUser("LauraRH", "Laura", 1997, mailStore);
        MailBox mailBoxAlbert = mailSystem.createUser("AlbertCS", "Albert", 1997, mailStore);
        MailBox mailBoxKoala = mailSystem.createUser("Koala spam", "Laura", 2001, mailStore);

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

        System.out.println("\nSpam messages:");
        mailBoxLaura.listSpam();

        System.out.println("\nMessage List:");
        mailBoxLaura.listMail();


    }
}
