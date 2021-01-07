package Part3.BaseClasses;

public class MailStoreMemFactory implements MailStoreFactory{
    @Override
    public MailStore createMailStore() {
        return new MailStoreMem();
    }
}
