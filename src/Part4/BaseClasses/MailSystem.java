package Part4.BaseClasses;

import Part1.BaseClasses.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Config(store = "Part1.BaseClasses.MailStoreMem", log = false)
public class MailSystem {

    private LinkedList<User> users = new LinkedList<>();
    private LinkedList<MailBox> mailBoxes = new LinkedList<>();
    private MailStore mailStore;


    private void readAnnotation() throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException {
        boolean log = false;
        String store = null;

        System.out.println("\nAnnotation");
        for (Annotation annotation : MailSystem.class.getAnnotations()) {
            Class<? extends Annotation> type = annotation.annotationType();
            Method[] methods = type.getDeclaredMethods();
            log = (boolean)methods[0].invoke(annotation, (Object[])null);
            store = (String) methods[1].invoke(annotation, (Object[])null);
        }
        Class<?> aClass = Class.forName(store);
        if(log)
            this.mailStore = (MailStore) DynamicProxy.newInstance(aClass.getDeclaredConstructor().newInstance());
        else
            this.mailStore = (MailStore) aClass.getDeclaredConstructor().newInstance();

    }

    public MailSystem() throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        readAnnotation();
    }

    public MailBox createUser(String userName, String name, int birthYear){
        User user = new User(userName, name, birthYear);
        MailBox mailBox = new MailBox(mailStore, user);
        users.add(user);
        mailBoxes.add(mailBox);
        return mailBox;
    }


    public void updateAllMessages(Comparator comparator){
        mailBoxes.stream().forEach(mailBox -> mailBox.update(comparator));
    }

    public void getAllMessages(){
        mailBoxes.stream().forEach(MailBox::listMail);
    }


    public int countWords(String userName){
        LinkedList<Message> messages =
                mailBoxes.stream().map(MailBox::getReceivedMessages).flatMap(l -> l.stream())
                        .filter(message -> message.getSender().equalsIgnoreCase(userName)).collect(Collectors.toCollection(LinkedList::new));
        int words=0;
        for(Message message:messages){
            words += message.getBody().split("\\s+").length;
        }
        return words;
    }




}
