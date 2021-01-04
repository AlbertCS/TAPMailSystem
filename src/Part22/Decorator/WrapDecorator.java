package Part22.Decorator;

import Part22.BaseClasses.Message;

import java.util.StringTokenizer;

public class WrapDecorator extends Message {
    public WrapDecorator(String subject, String body, String sender, String receiver) {
        super(subject, body+" body extra", sender, receiver);
    }

    public WrapDecorator(String subject, String body, String sender, String receiver, String creationDate) {
        super(subject, body+" body extra", sender, receiver, creationDate);
    }


    public String getBody(){
        String body="";
        StringTokenizer tokens = new StringTokenizer(" ");
        while(tokens.hasMoreTokens() ){
            if (!tokens.equals("body")){
                body=body+tokens;
                break;
            }
        }
        return body;
    }

}
