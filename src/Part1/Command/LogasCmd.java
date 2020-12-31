package Part1.Command;

import Part1.BaseClasses.MailBox;
import Part1.BaseClasses.MailSystem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import static java.lang.System.in;

public class LogasCmd implements Command {
    private MailBox mailBox;
    private MailSystem mailSystem;
    private String userName;


    public LogasCmd(String userName, MailSystem mailSystem) {
        this.mailSystem = mailSystem;
        this.userName = userName;
        this.mailBox = mailSystem.getMailBox(userName);
    }

    @Override
    public void execute() {
        System.out.println("\nHi "+userName+", Input a command line (separate the parameters by ';') write help for more information:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String token;
        StringTokenizer tokens = null;
        try {
            tokens = new StringTokenizer(reader.readLine(), ";");
        } catch (IOException e) {
            e.printStackTrace();
        }
        token=tokens.nextToken();
        while(!token.equalsIgnoreCase("exit")){
            switch (token){
                case "send":
                    String receiver = tokens.nextToken();
                    if(mailSystem.userExist(receiver))
                        new SendCmd(mailBox, receiver, tokens.nextToken(), tokens.nextToken()).execute();
                    else
                        System.out.println("\nThe receiver doesn't exist");
                    break;
                case "update":
                    new UpdateCmd(mailBox).execute();
                    break;
                case "list":
                    System.out.println("\nList of messages:");
                    mailBox.forEach(System.out::println);
                    break;
                case "sort":
                    System.out.println("\nSorting...");
                    new SortMsgCmd(mailBox).execute();
                    break;
                case "filter":
                    String nextToken=tokens.nextToken();
                    if(nextToken.equalsIgnoreCase("byUser")){
                        mailBox.filterUserMail(tokens.nextToken());
                    }
                    else if(nextToken.equalsIgnoreCase("bySubject")){
                        mailBox.filterSubjectWord(tokens.nextToken());
                    }
                    else{
                        System.out.println("\n The filter parameter is not correct");
                    }
                    break;
                case "help":
                    System.out.println("\nYou have this commands:" +
                            "\n\tsend <to> <subject> <body> : Send a new message" +
                            "\n\tupdate : retrieve messages from the mailStore" +
                            "\n\tlist : show messages sorted by send time" +
                            "\n\tsort : sort messages by username" +
                            "\n\tfilter\t <byUser> <username>: filter messages from a certain user" +
                            "\n\t\t\t<bySubject> : filter messages by subject" +
                            "\n\texit : log out");
                    break;
                default:
                    System.out.println("\nNot a valid command");
                    break;
            }
            System.out.println("\n Input command "+userName+":");
            try {
                tokens = new StringTokenizer(reader.readLine(), ";");
            } catch (IOException e) {
                e.printStackTrace();
            }
            token=tokens.nextToken();
        }
        System.out.println("\nBye "+userName);
    }
}
