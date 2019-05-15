package symbolTables;

import symbols.Group;

public class GroupSymbolTable {
    Group group = null;

    public void openScope(){
        group = new Group(group);
    }

    public void closeScope(){
        group = group.getParent();
    }

    public Group retrieveGroup(){
        return group;
    }
}
