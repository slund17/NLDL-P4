package symbolTables;

import com.dat405.nldl.node.AVar;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

public abstract class VariableSymbolTable<T>{
    Map<String, T> table = new HashMap<>();

    public T enterSymbol(String var, T symbol){
        table.put(var, symbol);
        return symbol;
    }

    public int size() { return table.size(); }

    public boolean containsSymbol(String var){
        return table.containsKey(var);
    }

    public T retrieveSymbol(String var){
        return  table.get(var);
    }
}
