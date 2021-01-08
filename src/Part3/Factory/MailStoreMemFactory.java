package Part3.Factory;

import Part1.BaseClasses.MailStore;
import Part1.BaseClasses.MailStoreMem;

public class MailStoreMemFactory implements MailStoreFactory{
    @Override
    public MailStore createMailStore() {
        return new MailStoreMem();
    }
}
