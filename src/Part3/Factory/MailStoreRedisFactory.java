package Part3.Factory;

import Part1.BaseClasses.MailStore;
import Part3.BaseClasses.MailStoreRedis;

/**
 * @author Albert Cañellas and Laura Romero.
 * MailStoreRedisFactory
 */
public class MailStoreRedisFactory implements MailStoreFactory{
    @Override
    public MailStore createMailStore() {
        return (MailStore) MailStoreRedis.getInstance();
    }
}
