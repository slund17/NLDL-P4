package recognizers;

import com.dat405.nldl.node.*;
import symbols.IpAddress;

import java.util.function.Predicate;

public class Predicates {


    public static Predicate<Object> isIdentifier(String string){
        return (x->{
            return x instanceof String && ((String) x).equalsIgnoreCase(string);
        });
    }

    public static Predicate<Object> isLessThanNum(int num){
        return (x->{
            return x instanceof Integer && (Integer)x<num;
        });
    }

    public static Predicate<Object> isGreaterThanNum(int num){
        return (x->{
            return x instanceof Integer && (Integer)x>num;
        });
    }

    public static Predicate<Object> isPosNum(){
        return (x->{
            return x instanceof Integer && (Integer)x>=0;
        });
    }

    public static Predicate<Object> isNum(){
        return (x->{
            return x instanceof Integer;
        });
    }

    public static Predicate<Object> isIp() {
        return (x->{
            return x instanceof IpAddress;
        });
    }
}
