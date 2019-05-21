package visitors;

import ast.ASTfactory;
import com.dat405.nldl.node.Start;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DDcl_S {
    private SemanticsVisitor semanticsVisitor = new SemanticsVisitor();
    String program_1 = "Segment r1; Settings mySet{ } Group grp1 {  mySet; }";
    String program_2 = "Segment r1, S2, Ko, LO; Settings mySet{ } Group grp1 { mySet; }";
    String program_3 = "Segment r1, S2, Ko, LO; Settings mySet{ } Group grp1 { mySet; r1; }";


    // Assert that declaring a segment variable does not add element to envR
    @Test
    void DDcl_S1() {
        ASTfactory.createFromString(program_2).apply(semanticsVisitor);
        assertEquals(0, semanticsVisitor.envR.size());
    }

    // Assert that declaring multiple segment variables does not add elements to envR
    @Test
    void DDcl_S2() {
        ASTfactory.createFromString(program_2).apply(semanticsVisitor);
        assertEquals(0, semanticsVisitor.envR.size());
    }

    // Assert that segment variables cannot be applied inside the group scope
    @Test
    void DDcl_S3() {
        String errorMsg = "";
        Start start = ASTfactory.createFromString(program_3);
        try {
            start.apply(semanticsVisitor);
        }catch (RuntimeException e) {
            errorMsg = e.getMessage();
        }
        assertTrue(errorMsg.contains("Unknown variable 'r1'"));
    }

    // Assert that declaring multiple segment variables does not add elements to envR
    @Test
    void DDcl_S4() {
        ASTfactory.createFromString(program_2).apply(semanticsVisitor);
        assertEquals(0, semanticsVisitor.envR.size());
    }

    // Assert that declaring multiple segment variables does not add elements to envS
    @Test
    void DDcl_S5() {
        ASTfactory.createFromString(program_2).apply(semanticsVisitor);
        assertEquals(1, semanticsVisitor.envS.size());
    }
}
