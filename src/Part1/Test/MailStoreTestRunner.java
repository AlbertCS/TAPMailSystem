package Part1.Test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author Albert Cañellas and Laura Romero.
 * MailStoreTestRunner Class
 */
public class MailStoreTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MailStoreTestSuite.class);
        for(Failure failure : result.getFailures()){
            System.out.println("\nfailure = " + failure.toString());
        }
        System.out.println("\nTest MailStore OK? "+result.wasSuccessful());
    }
}
