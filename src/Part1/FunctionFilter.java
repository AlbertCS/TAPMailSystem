package Part1;

/**
 * @author Albert Cañellas and Laura Romero.
 * FunctionFilter Class
 * @param <T>
 * @param <O>
 */
public abstract class FunctionFilter<T,O> {

    public abstract boolean doExecute(T t, O o);

}
