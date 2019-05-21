package visitors;

import ast.ASTFactory;
import com.dat405.nldl.node.Start;
import org.junit.jupiter.api.Test;
import settings.HelloIntervalSetting;
import settings.InterfaceSetting;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrgGDcl {
    SemanticsVisitor semanticsVisitor = new SemanticsVisitor();

    // Standard program
    String program1 = "Router R14, R15, R16, R17, R18, R19, R20, R21, R22, R23, R24;" +
            "Group area3{" +
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
            "        }"+
            "}";

    // Program with error (shorthand IP but no group IP)
    String program2 =
            "Router R1, R2;" +
            "Group common { " +
                "Setting OSPF Area 3 totally-stub;" +
                "Setting OSPF Network point-to-point;" +
                "Connection y = R1(.1/28, f0/1)->R2(.18/30, f2/1);" +
            "}" +
            "Group special {" +
                "Setting OSPF Area 1 totally-stub;" +
                "Setting OSPF Network point-to-point; " +
            "}";


    String program3 =
                    "Router R1, R2;" +
                    "Group common { " +
                        "IP 10.3.2.0;" +
                        "Setting OSPF hello-interval 200;" +
                        "Connection y = R1(.1/28, f0/1)->R2(.18/30, f2/1);" +
                        "}" +
                    "Group special {" +
                        "Setting OSPF hello-interval 100;" +
                        "y;" +
                     "}";

    String program4 =
                    "Router R1, R2;" +
                    "Group common { " +
                        "IP 10.3.2.0;" +
                        "Connection y = R1(.1/28, f0/1)->R2(.18/30, f2/1);" +
                    "}" +
                    "Group special {" +
                            "Setting OSPF hello-interval 200;" +
                            "y;" +
                    "}";

    String program5 =
            "Router R1, R2;" +
                    "Group common { " +
                        "IP 10.3.2.0;" +
                        "Setting OSPF hello-interval 200;" +
                        "Connection y = R1(.1/28, f0/1)->R2(.18/30, f2/1);" +

                        "Group special {" +
                            "Setting OSPF hello-interval 100;" +
                            "y;" +
                        "}"+
                    "}";


    @Test
    void PrgGDcl_1() {
        // Assert envS was NOT modified
        Start start = ASTFactory.fromString(program1);
        start.apply(semanticsVisitor);
        assertEquals(0, semanticsVisitor.envS.size());
    }

    @Test
    void PrgGDcl_2() {
        // Assert that envI WAS modified for R19
        Start start = ASTFactory.fromString(program1);
        start.apply(semanticsVisitor);
        assertEquals(3, semanticsVisitor.envR.retrieveSymbol("R19").getInterfaces().size());
    }

    @Test
    void PrgGDcl_3() {
        // Assert that envC WAS modified
        Start start = ASTFactory.fromString(program1);
        start.apply(semanticsVisitor);
        assertEquals(1, semanticsVisitor.envC.size());
        assertTrue(semanticsVisitor.envC.containsSymbol("b1"));
    }

    @Test
    void PrgGDcl_4() {
        // Assert that envC WAS modified
        Start start = ASTFactory.fromString(program1);
        start.apply(semanticsVisitor);
        assertEquals(1, semanticsVisitor.envC.size());
        assertTrue(semanticsVisitor.envC.containsSymbol("b1"));
    }

    @Test
    void PrgGDcl_5() {
        // Assert that IP shorthand cannot be used without a group declaration of a long IP
        Start start = ASTFactory.fromString(program2);
        assertThrows(RuntimeException.class, () -> start.apply(semanticsVisitor));
    }

    @SuppressWarnings("Duplicates")
    @Test
    void PrgGDcl_6() {
        // Assert that the second Area setting overrides the first
        Start start = ASTFactory.fromString(program3);
        start.apply(semanticsVisitor);
        List<InterfaceSetting> sets = new ArrayList<>();
        semanticsVisitor.envR.retrieveSymbol("R1").getInterfaces().forEach(ix -> sets.addAll(ix.getSettings()));
        assertEquals(100, ((HelloIntervalSetting)sets.get(0)).interval);
    }

    @SuppressWarnings("Duplicates")
    @Test
    void PrgGDcl_7() {
        // Assert that the group settings in the second group are applied on the connection variable
        Start start = ASTFactory.fromString(program4);
        start.apply(semanticsVisitor);
        List<InterfaceSetting> sets = new ArrayList<>();
        semanticsVisitor.envR.retrieveSymbol("R1").getInterfaces().forEach(ix -> sets.addAll(ix.getSettings()));
        assertEquals(200, ((HelloIntervalSetting)sets.get(0)).interval);
    }

    @SuppressWarnings("Duplicates")
    @Test
    void PrgGDcl_8() {
        // Assert that the group settings in the innermost group scope are applied on the connection variable first
        Start start = ASTFactory.fromString(program5);
        start.apply(semanticsVisitor);
        List<InterfaceSetting> sets = new ArrayList<>();
        semanticsVisitor.envR.retrieveSymbol("R1").getInterfaces().forEach(ix -> sets.addAll(ix.getSettings()));
        assertEquals(100, ((HelloIntervalSetting)sets.get(0)).interval);
    }
}
