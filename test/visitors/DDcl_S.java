package visitors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DDcl_S {
    private SemanticsVisitor semanticsVisitor = new SemanticsVisitor();
    String DDcl_S1 = "Segment r1; Settings mySet{ } Group grp1 { r1; mySet; }";
    String DDcl_S2 = "Segment r1, S2, Ko, LO; Settings mySet{ } Group grp1 { r1; mySet; }";

    @Test
    void DDcl_S1() {
        ASTFactory.startFromString(DDcl_S1).apply(semanticsVisitor);
        assertEquals(0, semanticsVisitor.envR.size());
    }

    @Test
    void DDcl_S2() {
        ASTFactory.startFromString(DDcl_S2).apply(semanticsVisitor);
        assertEquals(0, semanticsVisitor.envR.size());
    }
}
