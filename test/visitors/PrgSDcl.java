package visitors;

import com.dat405.nldl.node.Start;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrgSDcl {
    private SemanticsVisitor semanticsVisitor = new SemanticsVisitor();
    String PrgSDcl = "Router r1; Settings mySet{ } Group grp1 { r1; mySet; }";

    @Test
    void PrgSDcl_1() {
        // Assert that envS is 1 element larger after declaring a setting var
        int startSize = semanticsVisitor.envS.size();
        Start start = visitors.ASTFactory.startFromString(PrgSDcl);
        start.apply(semanticsVisitor);
        assertEquals( startSize + 1, semanticsVisitor.envS.size());
    }

    @Test
    void PrgSDcl_2() {
        // Assert that envS overwrites var names
        String PrgSDclExpanded = "Router r1; Settings mySet{ } Settings mySet{ } Group grp1 { r1; mySet; }";
        int startSize = semanticsVisitor.envS.size();
        Start start = visitors.ASTFactory.startFromString(PrgSDclExpanded);
        start.apply(semanticsVisitor);
        assertEquals(1, semanticsVisitor.envS.size());
    }

    @Test
    void PrgSDcl_3() {
        // Assert that envS is updated with the right var name
        Start start = visitors.ASTFactory.startFromString(PrgSDcl);
        start.apply(semanticsVisitor);
        assertTrue(semanticsVisitor.envS.containsSymbol("mySet") );
    }
}
