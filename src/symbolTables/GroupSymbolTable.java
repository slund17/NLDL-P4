package symbolTables;

import symbols.Group;

public class GroupSymbolTable {
    Group group = null;

    void openScope(){
        group = new Group(group);
    }

    void closeScope(){
        group = group.getParent();
    }

    Group retrieveGroup(){
        return group;
    }
}
