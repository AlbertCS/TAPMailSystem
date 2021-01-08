package Part3.Factory;

import Part1.BaseClasses.MailStore;
import Part1.BaseClasses.MailStoreFile;

/**
 * @author Albert Cañellas and Laura Romero.
 * MailStoreFileFactory Class
 */
public class MailStoreFileFactory implements MailStoreFactory {
    @Override
    public MailStore createMailStore() {
        return new MailStoreFile();
    }
}
