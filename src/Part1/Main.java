package Part1;

import Part1.BaseClasses.*;
import Part1.Comparator.*;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
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
        MailBox mailBoxKoala = mailSystem.createUser("Koala", "Laura", 2001, mailStore);

        // 3. Send messages
        mailBoxLaura.sendMail("Dubte Tap","Hola, suspendrem tap?","AlbertCS");
        mailBoxAlbert.sendMail("Dubte Tap","No, ho aprovarem segur","LauraRH");
        mailBoxKoala.sendMail("Hola","Vull eucaliptus que tinc gana","LauraRH");
        mailBoxKoala.sendMail("Albert!!","La laura no em dona de menjar","AlbertCS");
        mailBoxLaura.sendMail("Hola","Avui fa bon dia? Ja se que ningu ho ha demanat","AlbertCS");

        // 4. Update one mailBox
        mailBoxLaura.update(comparator);

        // 5. List messages by date
        System.out.println("\nFor each print");
        mailBoxLaura.forEach(System.out::println);

        // 6. List messages by sender username
        System.out.println("\nMessages by sender");
        mailBoxLaura.getMail(comparator1);

        // 7a. Filter messages with a certain word
        System.out.println("\nFilter msg by subject");
        mailBoxLaura.filterSubjectWord("Tap");

        // 7b. Filter messages from a certain user
        System.out.println("\nFilter msg by sender");
        mailBoxLaura.filterUserMail("AlbertCS");

        mailSystem.updateAllMessages(comparator);

        // 8. Print all messages
        System.out.println("\nPrinting all messsages:");
        mailSystem.getAllMessages();

        //mailSystem.getAllUsers();

        // 9a. Filter messages subject is a single word
        System.out.println("\nSingle word:");
        mailSystem.filterAllMessagesSingleWord().forEach(System.out::println);

        // 9a. Filter messages send by a user born after 2000
        System.out.println("\nAfter year:");
        Predicate<User> afterYear = p -> p.getBirthYear() > 2000;
        mailSystem.filterAllMessagesYear(afterYear).forEach(System.out::println);

        // 10. Get the count of messages
        mailSystem.countMessages();

        // 11. Get average number of messages
        mailSystem.avgMessages();

        // 12. Group the messages per subject
        System.out.println("\nGrup by");
        Map<String, List<Message>> bySubject = mailSystem.grupSubject();
        bySubject.get("Dubte Tap").forEach(System.out::println);

        // 13. Count the words of messages
        System.out.println("\nCount words: " +mailSystem.countWords("Koala"));

        // 14. pregunta trampa
        System.out.println("\n Pregunta trampa");
        mailSystem.getMailBox("LauraRH").forEach(System.out::println);

        // 15. Filter messages send by a user born before 2000
        System.out.println("\nBefore year:");
        Predicate<User> beforeYear = p -> p.getBirthYear() < 2000;
        mailSystem.filterAllMessagesYear(beforeYear).forEach(System.out::println);


        // 16. Change to File implementation

        System.out.println("\nMAIL STORE FILE");

        // 1. Initialize

        MailStore mailStoreFile = new MailStoreFile();

        // 2. Create users
        MailSystem mailSystemFile = new MailSystem();
        MailBox mailBoxLauraFile =  mailSystemFile.createUser("LauraRH", "Laura", 1997, mailStoreFile);
        MailBox mailBoxAlbertFile = mailSystemFile.createUser("AlbertCS", "Albert", 1997, mailStoreFile);
        MailBox mailBoxKoalaFile = mailSystemFile.createUser("Koala", "Laura", 2001, mailStoreFile);

        // 3. Send messages
        mailBoxLauraFile.sendMail("Dubte Tap","Hola, suspendrem tap?","AlbertCS");
        mailBoxAlbertFile.sendMail("Dubte Tap","No, ho aprovarem segur","LauraRH");
        mailBoxKoalaFile.sendMail("Hola","Vull eucaliptus que tinc gana","LauraRH");
        mailBoxKoalaFile.sendMail("Albert!!","La laura no em dona de menjar","AlbertCS");
        mailBoxLauraFile.sendMail("Hola","Avui fa bon dia? Ja se que ningu ho ha demanat","AlbertCS");

        // 4. Update one mailBox
        mailBoxLauraFile.update(comparator);

        // 5. List messages by date
        System.out.println("\nFor each print");
        mailBoxLauraFile.forEach(System.out::println);

        // 6. List messages by sender username
        System.out.println("\nMessages by sender");
        mailBoxLauraFile.getMail(comparator1);

        // 7a. Filter messages with a certain word
        System.out.println("\nFilter msg by subject");
        mailBoxLauraFile.filterSubjectWord("Tap");

        // 7b. Filter messages from a certain user
        System.out.println("\nFilter msg by sender");
        mailBoxLauraFile.filterUserMail("AlbertCS");

        mailSystemFile.updateAllMessages(comparator);

        // 8. Print all messages
        System.out.println("\nPrinting all messsages:");
        mailSystemFile.getAllMessages();

        //mailSystem.getAllUsers();

        // 9a. Filter messages subject is a single word
        System.out.println("\nSingle word:");
        mailSystemFile.filterAllMessagesSingleWord().forEach(System.out::println);

        // 9a. Filter messages send by a user born after 2000
        System.out.println("\nAfter year:");
        //Predicate<User> afterYear = p -> p.getBirthYear() > 2000;
        mailSystemFile.filterAllMessagesYear(afterYear).forEach(System.out::println);

        // 10. Get the count of messages
        mailSystemFile.countMessages();

        // 11. Get average number of messages
        mailSystemFile.avgMessages();

        // 12. Group the messages per subject
        System.out.println("\nGrup by");
        Map<String, List<Message>> bySubjectF = mailSystemFile.grupSubject();
        bySubjectF.get("Dubte Tap").forEach(System.out::println);

        // 13. Count the words of messages
        System.out.println("\nCount words: " +mailSystemFile.countWords("Koala"));

        // 14. pregunta trampa
        System.out.println("\n Pregunta trampa");
        mailSystemFile.getMailBox("LauraRH").forEach(System.out::println);

        // 15. Filter messages send by a user born before 2000
        System.out.println("\nBefore year:");
        //Predicate<User> beforeYear = p -> p.getBirthYear() < 2000;
        mailSystemFile.filterAllMessagesYear(beforeYear).forEach(System.out::println);


    }
}
