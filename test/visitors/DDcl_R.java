package visitors;

import ast.ASTfactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DDcl_R {
    SemanticsVisitor semanticsVisitor = new SemanticsVisitor();
    String program1 = "Router r1; Group grp1 { r1; }";
    String program2 = "Router r1, gi, M13; Group grp1 { r1; }";
    String program3 = "Router Router r1, r2, r3; Group grp { r1; r2; r3; } ";

    @Test
    void DDcl_R1() {
        // Assert only 1 router was declared
        ASTfactory.createFromString(program1).apply(semanticsVisitor);
        assertEquals(semanticsVisitor.envR.size(), 1);
    }

    @Test
    void DDcl_R2() {
        // Assert 3 routers were declared
        ASTfactory.createFromString(program2).apply(semanticsVisitor);
        assertEquals(semanticsVisitor.envR.size(), 3);
    }

    @Test
    void DDcl_R3() {
        // Assert a specific router was added
        ASTfactory.createFromString(program2).apply(semanticsVisitor);
        assertTrue(semanticsVisitor.envR.containsSymbol("M13"));
    }

    // Assert envS is not modified after semanticVisitor traverses the AST
    @Test
    void DDcl_R4a() {
        // Before visitor
        assertEquals(0, semanticsVisitor.envS.size());
        ASTfactory.createFromString(program2).apply(semanticsVisitor);
    }

    @Test
    void DDcl_R4b() {
        // After visitor
        ASTfactory.createFromString(program2).apply(semanticsVisitor);
        assertEquals(0, semanticsVisitor.envS.size());
    }

    @Test
    void DDcl_R5() {
        // Assert envC was not modified
        assertEquals(0, semanticsVisitor.envC.size());
        ASTfactory.createFromString(program2).apply(semanticsVisitor);
        assertEquals(0, semanticsVisitor.envC.size());
    }

    @Test
    void DDcl_R6() {
        // Assert envC was not modified
        assertEquals(0, semanticsVisitor.envC.size());
        ASTfactory.createFromString(program2).apply(semanticsVisitor);
        assertEquals(0, semanticsVisitor.envC.size());
    }
}