package symbolTables;

import com.dat405.nldl.node.AVar;

import java.util.HashMap;
import java.util.Map;

public abstract class VariableSymbolTable<T> {
    Map<String, T> table = new HashMap<>();

    void enterSymbol(AVar var, T symbol){
        table.put(var.getIdentifier().getText(), symbol);
    }

    T retrieveSymbol(AVar var){
        return  table.get(var.getIdentifier().getText());
    }

}
