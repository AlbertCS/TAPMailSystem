package Part3.Factory;

import Part1.BaseClasses.MailStore;
import Part3.BaseClasses.MailStoreRedis;

public class MailStoreRedisFactory implements MailStoreFactory{
    @Override
    public MailStore createMailStore() {
        return (MailStore) MailStoreRedis.getInstance();
    }
}
