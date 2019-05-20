package visitors;

import com.dat405.nldl.node.Start;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SemanticsVisitorTest {

    private SemanticsVisitor semanticsVisitor = new SemanticsVisitor();
    // Empty declarations that satisfy the parser
    private final String EMPTY_DCLS = "Router r99; Group g99 { setting99; r99; } ";

    // Assert that interface settings in a group are not applied on router
    @Test
    void interfaceSetttingsNotOnRouter(){
        Start start = ASTFactory.startFromString (
                    "Router r1; " +
                        " Settings test2 { "+
                            "OSPF hello-interval 3;" +
                            "OSPF dead-interval 5;"+
                        "}"+
                        "Group g2 { test2; r1; }"
        );

        start.apply(semanticsVisitor);
        int numSettings = semanticsVisitor.envR.retrieveSymbol("r1").getSettings().size();
        assertEquals(0, numSettings);
    }

    @Test
    void PrgSDcl() {
        Start start = visitors.ASTFactory.startFromString(
                  "Settings pika{}" + EMPTY_DCLS
        );

        start.apply(semanticsVisitor);
    }
}