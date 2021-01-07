package Part3.Factory;

import Part3.BaseClasses.MailStore;
import Part3.BaseClasses.MailStoreFile;

public class MailStoreFileFactory implements MailStoreFactory {
    @Override
    public MailStore createMailStore() {
        return new MailStoreFile();
    }
}
