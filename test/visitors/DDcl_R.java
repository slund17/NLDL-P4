package visitors;

import ast.ASTfactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DDcl_R {
    SemanticsVisitor semanticsVisitor = new SemanticsVisitor();
    String DDcl_R1 = "Router r1; Settings mySet{ } Group grp1 { r1; mySet; }";
    String DDcl_R2 = "Router r1, gi, M13; Settings mySet{ } Group grp1 { r1; mySet; }";

    @Test
    void DDcl_R1() {
        // Assert only 1 router was declared
        ASTfactory.createFromString(DDcl_R1).apply(semanticsVisitor);
        assertEquals(semanticsVisitor.envR.size(), 1);
    }

    @Test
    void DDcl_R2() {
        // Assert 3 routers were declared
        ASTfactory.createFromString(DDcl_R2).apply(semanticsVisitor);
        assertEquals(semanticsVisitor.envR.size(), 3);
    }

    @Test
    void DDcl_R3() {
        // Assert a specific router was added
        ASTfactory.createFromString(DDcl_R2).apply(semanticsVisitor);
        assertTrue(semanticsVisitor.envR.containsSymbol("M13"));
    }
}
