package visitors;

import ast.ASTfactory;
import com.dat405.nldl.analysis.DepthFirstAdapter;
import com.dat405.nldl.node.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

// Basic visitor class that uses a map to keep track of the different types of visited nodes
public class TestVisitor extends DepthFirstAdapter {

    private Map<Class, Integer> visitedNodes = new HashMap<>();

    @Override
    public void defaultIn(Node node) {
        visitedNodes.putIfAbsent(node.getClass(), 0);
        visitedNodes.put(node.getClass(), visitedNodes.get(node.getClass()) + 1);
    }

    String program1 =
            "Router R14, R15, R16, R17, R18, R19, R20, R21, R22, R23, R24;" +
            "Group area3 {" +
                "IP 10.3.2.0;" +
                "" +
                "Setting OSPF Area 3 totally-stub;" +
                "Setting OSPF Network point-to-point;" +
                "" +
                "R18(.1/30, f0/0)->R19(.2/30, f1/0);" +
                "R19(.5/30, f0/1)->R16(.6/30, f0/1);" +
                "R19(.10/30, f0/0)->R17(.9/30, f0/1);" +
                "R16(.14/30, f0/0)->R17(.13/30, f2/0);" +
                "R17(.17/30, f0/0)->R15(.18/30, f0/1);" +
                "Connection b1 = Connection{" +
                "        R18(.10/24, f0/0);" +
                "        R19(.11/24, f0/0);" +
                "        R16(.12/24, f0/0);" +
                "        R17(.13/24, f0/0);" +
                "}" +
            "}";

    @Test
    void connectorNodes() {
        ASTfactory.createFromString(program1).apply(this);
        assertEquals(Integer.valueOf(14), visitedNodes.get(AConnector.class));
    }

    @Test
    void groupBlockNodes() {
        ASTfactory.createFromString(program1).apply(this);
        assertNull(visitedNodes.get(AGroupGroupBlock.class));
    }

    @Test
    void settingGroupBlockNodes() {
        ASTfactory.createFromString(program1).apply(this);
        assertEquals(Integer.valueOf(2), visitedNodes.get(ASettingGroupBlock.class));
    }

    @Test
    void routerDclNodes() {
        ASTfactory.createFromString(program1).apply(this);
        assertEquals(Integer.valueOf(1), visitedNodes.get(ARouterDeviceDcl.class));
    }

    @Test
    void connectionDclNodes() {
        ASTfactory.createFromString(program1).apply(this);
        assertEquals(Integer.valueOf(1), visitedNodes.get(ARouterDeviceDcl.class));
    }
}
