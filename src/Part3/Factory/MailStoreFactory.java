package Part3.Factory;

import Part1.BaseClasses.MailStore;

public interface MailStoreFactory {

    public abstract MailStore createMailStore();
}
