package Part3.Factory;

import Part3.BaseClasses.MailStore;

public interface MailStoreFactory {

    public abstract MailStore createMailStore();
}
