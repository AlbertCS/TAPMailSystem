package Part3.BaseClasses;

public class MailStoreFileFactory implements MailStoreFactory {
    @Override
    public MailStore createMailStore() {
        return new MailStoreFile();
    }
}
