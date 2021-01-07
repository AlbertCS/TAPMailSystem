package Part3.BaseClasses;

public class MailStoreRedisFactory implements MailStoreFactory{
    @Override
    public MailStore createMailStore() {
        return MailStoreRedis.getInstance();
    }
}
