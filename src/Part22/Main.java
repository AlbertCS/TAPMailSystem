package Part22;

import Part22.BaseClasses.*;
import Part22.Comparator.MessageNewerComparator;
import Part22.Comparator.UserNameComparator;
import Part22.strategy.Context;
import Part22.strategy.OperationDecrypt;
import Part22.strategy.OperationEncrypt;
import Part22.strategy.OperationReverse;


import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

public class Main {
    //Main part 2

    public static void main(String[] args) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss ZZZ");

        System.out.println("MAIL STORE FILE");

        // 1. Initialize

        MailStore mailStore = new MailStoreFile();
        mailStore.clearTheFile();
        MessageNewerComparator comparator = new MessageNewerComparator();
        UserNameComparator comparator1 = new UserNameComparator();

        // 2. Create users
        MailSystem mailSystem = new MailSystem();
        MailBox mailBoxLaura =  mailSystem.createUser("LauraRH", "Laura", 1997, mailStore);
        MailBox mailBoxAlbert = mailSystem.createUser("AlbertCS", "Albert", 1997, mailStore);
        MailBox mailBoxKoala = mailSystem.createUser("Koala spam", "Laura", 2001, mailStore);

        Context context = new Context(new OperationReverse());
        System.out.println("\nReverse: "+context.executeStrategy("Student Marks"));
        Context context1 = new Context(new OperationEncrypt());
        System.out.println("\nEncrypt: "+context1.executeStrategy("Student Marks"));
        Context context2 = new Context(new OperationDecrypt());
        System.out.println("\nDecrypt: "+context2.executeStrategy("Student Marks"));

        /*
        // 3. Send messages
        mailBoxLaura.sendMail("Dubte Tap","Hola, suspendrem tap?","AlbertCS");
        mailBoxAlbert.sendMail("Dubte Tap","No, ho aprovarem segur que si que si que si que magraden les mandonguilles amb tomaquet","LauraRH");
        mailBoxAlbert.sendMail("No","No","LauraRH");
        mailBoxKoala.sendMailDecorator("Hola","Vull eucaliptus que tinc gana","LauraRH");
        mailBoxKoala.sendMail("Albert!!","La laura no em dona de menjar","AlbertCS");
        mailBoxLaura.sendMail("Hola","Avui fa bon dia? Ja se que ningu ho ha demanat","AlbertCS");

        // 4. Update one mailBox
        mailBoxLaura.update(comparator);


        System.out.println("\nMessage List:");
        mailBoxLaura.listMail();
        */

    }
}
