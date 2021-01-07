package Part2Encoding;

import Part2Encoding.BaseClasses.*;
import Part2Encoding.Comparator.MessageNewerComparator;
import Part2Encoding.Comparator.UserNameComparator;


import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

public class Main {
    //Main part 2

    public static void main(String[] args) throws IOException {

        System.out.println("MAIL STORE FILE");

        // 1. Initialize
        MailStore mailStore = new MailStoreFile();
        mailStore.clearTheFile();
        MessageNewerComparator comparator = new MessageNewerComparator();

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

        // 4. Update one mailBox
        mailBoxLaura.update(comparator);

        System.out.println("\nMessage List:");
        mailBoxLaura.listMail();


    }
}
