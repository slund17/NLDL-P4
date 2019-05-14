package recognizers;

import com.dat405.nldl.node.AIdentifierS;
import com.dat405.nldl.node.ANumS;
import com.dat405.nldl.node.PS;

public class Predicates {


    public static TokenPredicate isIdentifier(String string){
        return ((TokenPredicate)x->{
            return x instanceof AIdentifierS && ((AIdentifierS) x).getIdentifier().getText().equals(string);
        });
    }

    public static TokenPredicate isLessThanNum(int num){
        return ((TokenPredicate)x->{
            return x instanceof ANumS && Integer.valueOf(((ANumS) x).getConst().getText())<num;
        });
    }

    public static TokenPredicate isGreaterThanNum(int num){
        return ((TokenPredicate)x->{
            return x instanceof ANumS && Integer.valueOf(((ANumS) x).getConst().getText())>num;
        });
    }

    public static TokenPredicate isPosNum(){
        return ((TokenPredicate)x->{
            return x instanceof ANumS && Integer.valueOf(((ANumS) x).getConst().getText())>=0;
        });
    }

    public static TokenPredicate isNum(){
        return ((TokenPredicate)x->{
            return x instanceof ANumS;
        });
    }
}
