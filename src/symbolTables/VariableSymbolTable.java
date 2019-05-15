package symbolTables;

import com.dat405.nldl.node.AVar;

import java.util.HashMap;
import java.util.Map;

public abstract class VariableSymbolTable<T> {
    Map<String, T> table = new HashMap<>();

    public T enterSymbol(String var, T symbol){
        table.put(var, symbol);
        return symbol;
    }

    public boolean containsSymbol(String var){
        return table.containsKey(var);
    }

    public T retrieveSymbol(String var){
        return  table.get(var);
    }

}
