package Part1.Comparator;

import Part1.BaseClasses.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

/**
 * @author Albert Cañellas and Laura Romero.
 * MessageNewerComparator Class
 */
public class MessageNewerComparator implements Comparator<Message> {

    @Override
    public int compare(Message o1, Message o2) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss ZZZ", Locale.getDefault());
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = formatter.parse(o1.getCreationDate());
            date2 = formatter.parse(o2.getCreationDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1.compareTo(date2); // If date1 is after date2
    }
}
