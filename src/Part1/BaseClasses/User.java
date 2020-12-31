package Part1.BaseClasses;

/**
 * @author Albert Cañellas and Laura Romero.
 * User class.
 */
public class User {
    private String userName;
    private String name;
    private int birthYear;
    //private MailBox mailbox;

    /**
     * Constructor for user.
     * @param userName
     * @param name
     * @param birthYear
     */
    public User(String userName, String name, int birthYear) {
        this.userName = userName;
        this.name = name;
        this.birthYear = birthYear;
        //this.mailbox = mailbox;
    }


    public int getBirthYear() {
        return birthYear;
    }

    public String getUserName() {
        return userName;
    }
}
