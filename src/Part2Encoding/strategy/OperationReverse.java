package Part2Encoding.strategy;

/**
 * @author Albert Cañellas and Laura Romero.
 * OperationReverse class
 */
public class OperationReverse implements Strategy{
    @Override
    public String doOperation(String body) {
        StringBuilder bodyReverse = new StringBuilder(body);
        return bodyReverse.reverse().toString();
    }
}