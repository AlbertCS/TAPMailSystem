package Part1;

public class GreaterFilter extends FunctionFilter {


    @Override
    public boolean doExecute(Object o, Object o2) {
        if( (int)o > (int)o2) return true;
        else return false;
    }
}
