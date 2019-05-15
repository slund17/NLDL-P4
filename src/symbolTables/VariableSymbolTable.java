package symbolTables;

import com.dat405.nldl.node.AVar;

import java.util.HashMap;
import java.util.Map;

public abstract class VariableSymbolTable<T> {
    Map<String, T> table = new HashMap<>();

    public void enterSymbol(String var, T symbol){
        table.put(var, symbol);
    }

    public T retrieveSymbol(String var){
        return  table.get(var);
    }

}
