package symbolTables;

import symbols.Router;

import java.util.*;


public class RouterSymbolTable extends VariableSymbolTable<Router> {
    public Collection<Router> getRouters(){
        return this.table.values();
    }
}
