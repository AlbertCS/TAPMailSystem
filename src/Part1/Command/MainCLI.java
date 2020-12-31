package Part1.Command;

import Part1.BaseClasses.MailBox;
import Part1.BaseClasses.MailStore;
import Part1.BaseClasses.MailStoreMem;
import Part1.BaseClasses.MailSystem;
import Part1.Comparator.MessageNewerComparator;
import Part1.Comparator.UserNameComparator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

import static java.lang.System.in;
import static java.lang.System.setOut;

public class MainCLI {

    public static void main(String[] args) throws IOException {


        // 1. Initialize
        MailStore mailStore = new MailStoreMem();
        MessageNewerComparator comparator = new MessageNewerComparator();
        MailSystem mailSystem = new MailSystem();

        // 2. Create users
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

        System.out.println("\nHello, input a command line (separate the parameters by 'space') write help for more information:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String token;
        StringTokenizer tokens = new StringTokenizer(reader.readLine(), " ");
        token=tokens.nextToken();
        while(!token.equalsIgnoreCase("exit")){
            switch (token){
                case "createuser":
                    new CreateUserCmd(tokens.nextToken(), tokens.nextToken(), Integer.parseInt(tokens.nextToken()), mailStore, mailSystem).execute();
                    break;

                case "filter":
                    String nextToken=tokens.nextToken();
                    LinkedList messagesFilter = new LinkedList();
                    if(nextToken.equalsIgnoreCase("groupBy")){
                        new FilterGroupByCmd(tokens.nextToken(), mailSystem, messagesFilter).execute();
                        messagesFilter.forEach(System.out::println);
                    }
                    else if(nextToken.equalsIgnoreCase("singleWord")){
                        new FilterSingleWordCmd(mailSystem, messagesFilter).execute();
                        messagesFilter.forEach(System.out::println);
                    }
                    else{
                        System.out.println("\n The filter parameter is not correct");
                    }
                    break;

                case "logas":
                    String userName = tokens.nextToken();
                    if(mailSystem.userExist(userName))
                        new LogasCmd(userName, mailSystem).execute();
                    else
                        System.out.println("\nUser doesn't exist");
                    break;

                case "help":
                    System.out.println("\nYou have this commands:" +
                            "\n\tcreateuser <username> <name> <birthYear> : To create a new user" +
                            "\n\tfilter\t <groupBy> : group the messages by subject" +
                            "\n\t\t\t<singleWord> : filter the messages that subject is a single word" +
                            "\n\tlogas <username> : log in as a user" +
                            "\n\texit : exit the program");
                    break;

                default:
                    System.out.println("\nNot a valid command");
                    break;
            }
            System.out.println("\n Input command:");
            tokens = new StringTokenizer(reader.readLine(), " ");
            token=tokens.nextToken();
        }
        System.out.println("\nBye.");


    }
}
