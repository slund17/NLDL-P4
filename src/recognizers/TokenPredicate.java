package recognizers;

import com.dat405.nldl.node.Node;
import com.dat405.nldl.node.PS;
import com.dat405.nldl.node.Token;

public interface TokenPredicate {
    boolean test(PS t);
}
