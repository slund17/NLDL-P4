package symbolTables;

import com.dat405.nldl.node.AVar;
import symbols.Router;

import java.util.Map;

public class RouterSymbolTable {

    Map<String, Router> table;

    void enterRouter(AVar var){
        table.put(var.getIdentifier().getText(), new Router());
    }

    Router retrieveRouter(AVar var){
        return table.get(var.getIdentifier().getText());
    }
}
