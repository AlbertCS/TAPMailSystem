package Part3.Factory;

import Part3.BaseClasses.MailStore;
import Part3.BaseClasses.MailStoreRedis;

public class MailStoreRedisFactory implements MailStoreFactory{
    @Override
    public MailStore createMailStore() {
        return MailStoreRedis.getInstance();
    }
}
