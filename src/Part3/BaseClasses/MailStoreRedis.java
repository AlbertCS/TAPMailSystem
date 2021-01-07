package Part3.BaseClasses;

import redis.clients.jedis.Jedis;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * @author Albert Cañellas and Laura Romero.
 * MailStoreFile class, the mailStore that saves to a file.
 */
public class MailStoreRedis implements MailStore {

    // The single instance
    private static MailStoreRedis mailStoreRedis = new MailStoreRedis();

    Jedis jedis = new Jedis("localhost");

    /**
     * Constructor MailStoreFile, private makes impossible to create foreign instances
     */
    private MailStoreRedis() {}

    public void clearMailStore(){
        jedis.flushAll();
    }

    public static MailStoreRedis getInstance(){
        return mailStoreRedis;
    }

    @Override
    public void sendMail(Message message) throws IOException {
            jedis.lpush(message.getReceiver(), message.toString());
    }


    @Override
    public  LinkedList<Message> getMail(String username) {
        List<String> list = jedis.lrange(username, 0, -1);
        LinkedList<Message> messages = new LinkedList<>();
        for(String msg : list){
            String[] str = msg.split(";");
            messages.add(new Message(str[0], str[1], str[2], str[3], str[4]));
        }
        return messages;
    }



}
