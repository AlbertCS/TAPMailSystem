package Part3.Factory;

import Part1.BaseClasses.MailStore;

/**
 * @author Albert Cañellas and Laura Romero.
 * MailStoreFactory interface
 */
public interface MailStoreFactory {

    public abstract MailStore createMailStore();
}
