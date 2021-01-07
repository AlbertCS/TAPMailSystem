package Part3.Factory;

import Part3.BaseClasses.MailStore;
import Part3.BaseClasses.MailStoreMem;

public class MailStoreMemFactory implements MailStoreFactory{
    @Override
    public MailStore createMailStore() {
        return new MailStoreMem();
    }
}
