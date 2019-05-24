package parser;

import com.dat405.nldl.lexer.LexerException;
import com.dat405.nldl.node.*;
import com.dat405.nldl.parser.Parser;
import com.dat405.nldl.parser.ParserException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class parser {


    String program0 = "Router r1; Group grp1 { r1; }";

    // Syntactically valid program
    @Test
    void parseTest0() throws ParserException, IOException, LexerException {
        Parser.fromString(program0).parse();
    }

    // Right bracket missing
    String program1 = "Router r1; Group grp1 { r1; ";

    @Test
    void parseTest1() {
        assertThrows(ParserException.class, () -> Parser.fromString(program1).parse());
    }

    // Device declaration missing
    String program2 = "Group grp1 { r1; }";

    @Test
    void parseTest2() {
        assertThrows(ParserException.class, () -> Parser.fromString(program2).parse());
    }

    // Missing group declaration
    String program3 = "Router r1;";
    @Test
    void parseTest3() {
        assertThrows(ParserException.class, () -> Parser.fromString(program3).parse());
    }

    // Syntactically valid setting declaration
    String program4 = "Router r1; Group g2 { Setting Blabla; r1; }";
    @Test
    void parseTest4() throws ParserException, IOException, LexerException {
        Parser.fromString(program4).parse();
    }

    // Missing semicolon after declaration
    String program5 = "Router r1 Group g2 { Setting Blabla; r1; }";
    @Test
    void parseTest5() {
        assertThrows(ParserException.class, () -> Parser.fromString(program5).parse());
    }

    // Wrong token after Group token
    String program6 = "Router r1; Group Group { Setting Blabla; r1; }";
    @Test
    void parseTest6() {
        assertThrows(ParserException.class, () -> Parser.fromString(program6).parse());
    }

    // Syntactically valid, semantically invalid (using undeclared variables)
    String program7 = "Router r1, r2; Group g1 { r1; R90; Setting Blabla;  }";
    @Test
    void parseTest7() throws ParserException, IOException, LexerException {
        Parser.fromString(program7).parse();
    }

    // Missing semicolon
    String program8 = "Router r1 Group { Setting Blabla; r1; Setting asjdskad;  }";
    @Test
    void parseTest8() {
        assertThrows(ParserException.class, () -> Parser.fromString(program8).parse());
    }

    // Declaring Setting variable before identifier in group
    String program9 = "Router r1; Group { Setting Blabla; r1; Setting asjdskad;  }";
    @Test
    void parseTest9() {
        assertThrows(ParserException.class, () -> Parser.fromString(program9).parse());
    }

    String program10 =
            "Router R18, R19;" +
            "Group area3{" +
                "IP .3.2.0;" +
                "" +
                "Setting OSPF Area 3 totally-stub;" +
                "Setting OSPF Network point-to-point;" +
                "" +
                "R18(.1/30, f0/0)->R19(.2/30, f1/0);" +
            "}";

    // Missing octet in IP
    @Test
    void parseTest10() {
        assertThrows(ParserException.class, () -> Parser.fromString(program10).parse());
    }

    String program11 =
            "Router R18, R19;" +
            "Group area3{" +
                "IP 10.3.2.0;" +
                "" +
                "Setting OSPF Area 3 totally-stub;" +
                "Setting OSPF Network point-to-point;" +
                "" +
                "R18(.1/30 f0/0)->R19(.2/30, f1/0);" +
            "}";

    // Missing comma in connection
    @Test
    void parseTest11() {
        assertThrows(ParserException.class, () -> Parser.fromString(program11).parse());
    }


    String program12 =
            "Router R18, R19;" +
            "Group area3{" +
                "IP 10.3.2.0;" +
                "" +
                "Settin OSPF Area 3 totally-stub;" +
                "Setting OSPF Network point-to-point;" +
                "" +
                "R18(.1/30, f0/0)->R19(.2/30, f1/0);" +
            "}";


    // Misspelled 'Settin'
    @Test
    void parseTest12() {
        assertThrows(ParserException.class, () -> Parser.fromString(program12).parse());
    }

    String program13 =
            "Router R18, R19;" +
            "Group area3{" +
                "IP 10.3.2.0;" +
                "" +
                "Setting OSPF Area 3 totally-stub;" +
                "Setting OSPF Network point-to-point;" +
                "" +
                "R1(.1/30, f0/0)->R19(.2/30, f1/0);" +
            "}";


    // Valid syntax, but connection betweeen undeclared variables
    @Test
    void parseTest13() throws ParserException, IOException, LexerException {
        Parser.fromString(program13).parse();
    }


    String program14
            =
            "Router R18, R19;" +
            "Group area3{" +
                "IP 10...0;" +
                "" +
                "Setting OSPF Area 3 totally-stub;" +
                "Setting OSPF Network point-to-point;" +
                "" +
                "R1(.1/30, f0/0)->R19(.2/30, f1/0);" +
            "}";


    // Missing two octets in group IP
    @Test
    void parseTest14() {
        assertThrows(ParserException.class, () -> Parser.fromString(program14).parse());
    }


    String program15
            =   "Router R18, R19;" +
                "Group area3{" +
                    "IP 10.3.2.0;" +
                    "R1(.1/30, f0/0)-R19(.2/30, f1/0);" +
                "}";


    // Missing char in arrow
    @Test
    void parseTest15() {
        assertThrows(ParserException.class, () -> Parser.fromString(program15).parse());
    }



    String program16
            = "Router R18, R19;" +
            "Group area3{" +
            "IP 10.3.2.0;" +
            "R1(.1/30, f0/0)->R19(.2/30, f1/0);" +
            "}";


    @Test
    void parseTest16() throws ParserException, IOException, LexerException {
        Parser.fromString(program16).parse();
    }


    String program17
            =
            "Router R18, R19;" +
                "Group area3{" +
                "IP 10.3.2.0;" +
                "R1(.1/30, f0/0)-R19(.2/30, f1/0);" +
                "Group { r18; }" +
                    "}";

    // Missing   '>' in arrow syntax
    @Test
    void parseTest17() throws ParserException, IOException, LexerException {
        assertThrows(ParserException.class, () -> Parser.fromString(program17).parse());
    }


    String program18
            = "Router R18, R19;" +
            "Group area3{" +
                "R1(.1/30, f0/0)-R19(.2/30, f1/0);" +
            "}";

    // Missing IP
    @Test
    void parseTest18() {
        assertThrows(ParserException.class, () -> Parser.fromString(program18).parse());
    }


    String program19 = 
            "Router r1;" +
            "Group area2{" +
                "IP 10.2.30.0;" +
                "Setting OSPF Area 2;" +
            "    Setting OSPF Network broadcast;" +
            "" +
                "Connection b2 = Connection{" +
                    "R5(.4/29, f0/0);" +
                    "R3(.1/29, f0/0);" +
                    "R1(.2/29, f0/0);" +
                    "R2(.3/29, f0/0);" +
                "}" +
            "" +
                "Group ptp{" +
                    "Setting OSPF Network point-to-point;" +
                    "R2(.0.2/30, f0/1)->R23(.0.1/30, f0/0);" +
                "}" +
            "}";
            
    // Valid program
    @Test
    void parseTest19() throws ParserException, IOException, LexerException {
        Parser.fromString(program19).parse();
    }


    String program20 =
            "Router r1;" +
            "Group area2{" +
                "IP 10.2.30.0;" +
                "Setting OSPF Area 2;" +
            "    Setting OSPF Network broadcast;" +
            "" +
                "Connection b2 = Connection{" +
                    "R5(4/29, f0/0);" +
                    "R3(.1/29, f0/0);" +
                    "R1(.2/29, f0/0);" +
                    "R2(.3/29, f0/0);" +
                "}" +
            "" +
                "Group ptp{" +
                    "Setting OSPF Network point-to-point;" +
                    "R2(.0.2/30, f0/1)->R23(.0.1/30, f0/0);" +
                "}" +
            "}";


    // Missing . in connection
    @Test
    void parseTest20() {
        assertThrows(ParserException.class, () -> Parser.fromString(program20).parse());
    }

    String program21 =
            "Router r1;" +
            "Group area2{" +
                "Connection b2 = Connection{" +
                    "R5(.4/29, f0/0);" +
                    "R3(.1/29, f0/0);" +
                    "R1(.2/29, f0/0);" +
                    "R2(.3/29, f0/0);" +
                "}" +
                "IP 10.2.30.0;" +
                "Setting OSPF Area 2;" +
            "    Setting OSPF Network broadcast;" +
            "" +
            "" +
                "Group ptp{" +
                    "Setting OSPF Network point-to-point;" +
                    "R2(.0.2/30, f0/1)->R23(.0.1/30, f0/0);" +
                "}" +
            "}";


    // Declaring connection before identifiers in group
    @Test
    void parseTest21() {
        assertThrows(ParserException.class, () -> Parser.fromString(program21).parse());
    }

    String program22 =
            "Router r1;" +
            "Group area2{" +
                "IP 10.2.30.0;" +
                "Setting OSPF Area 2;" +
            "    Setting OSPF Network broadcast;" +
            "" +
                "Connection b2 = Connection{" +
                    "R5(.4/29, f0/0);" +
                    "R3(.1/29, f0/0);" +
                    "R1(.2/29, f0/0);" +
                    "R2(.3/29, f0/0);" +
                "}" +
            "" +
                "Group ptp{" +
                    "Setting OSPF Network point-to-point;" +
                    "R2(.0.2/30, 0/1)->R23(.0.1/30, f0/0);" +
                "}" +
            "}";

    // Missing interface specifer in connection
    @Test
    void parseTest22() {
        assertThrows(ParserException.class, () -> Parser.fromString(program22).parse());
    }

    String program23 =
            "Router r1;" +
            "Connection b2 = Connection{" +
                "R5(.4/29, f0/0);" +
                "R3(.1/29, f0/0);" +
                "R1(.2/29, f0/0);" +
                "R2(.3/29, f0/0);" +
            "}" +
            "Group area2{" +
                "IP 10.2.30.0;" +
                "Setting OSPF Area 2;" +
            "    Setting OSPF Network broadcast;" +
            "" +
            "" +
                "Group ptp{" +
                    "Setting OSPF Network point-to-point;" +
                    "R2(.0.2/30, 0/1)->R23(.0.1/30, f0/0);" +
                "}" +
            "}";


    // Connection outside group
    @Test
    void parseTest23() {
        assertThrows(ParserException.class, () -> Parser.fromString(program23).parse());
    }


    String program24 =
        "Router r1;" +
        "Group area2{" +
            "IP 10.2.30.0;" +
            "Setting OSPF Area 2;" +
        "    Setting OSPF Network broadcast;" +
        "" +
            "Connection b2 = Connection{" +
                "R5(.4/29, f0/0);" +
                "R3(.1/29, f0/0)" +
                "R1(.2/29, f0/0);" +
                "R2(.3/29, f0/0);" +
            "}" +
        "" +
            "Group ptp{" +
                "Setting OSPF Network point-to-point;" +
                "R2(.0.2/30, f0/1)->R23(.0.1/30, f0/0);" +
            "}" +
        "}";


    // Missing semicolon in connection inside b2
    @Test
    void parseTest24() {
        assertThrows(ParserException.class, () -> Parser.fromString(program24).parse());
    }

    String program25 =
        "Router r1;" +
        "Group area2{" +
            "IP 10.2.30.0;" +
            "Setting OSPF Area 2;" +
        "    Setting OSPF Network broadcast;" +
        "" +
            "Connection b2 = Connection{" +
                "R5(.4/29, f0/0);" +
                "R3(.1/29, f0/0)" +
                "R1(.229, f0/0);" +
                "R2(.3/29, f0/0);" +
            "}" +
        "" +
            "Group ptp{" +
                "Setting OSPF Network point-to-point;" +
                "R2(.0.2/30, f0/1)->R23(.0.1/30, f0/0);" +
            "}" +
        "}";

    // Missing subnet mask for connection
    @Test
    void parseTest25() {
        assertThrows(ParserException.class, () -> Parser.fromString(program24).parse());
    }

    String program26 = "Router r3; Router r1, R2; Group grp1 { r1; } ";
    // Expect 2 device dcl nodes
    @Test
    void parseTest26() throws ParserException, IOException, LexerException {
        Start start = Parser.fromString(program26).parse();
        AProgram aProgram = (AProgram) start.getPProgram();
        assertEquals(2, aProgram.getDeviceDcl().size());
    }

    // Assert parent/childe node relationship is correct
    String program27 = "Router r3; Router r1, R2; Group grp1 { r1; } Group rx { x; } ";
    @Test
    void parseTest27() throws ParserException, IOException, LexerException {
        Start start = Parser.fromString(program27).parse();
        AProgram aProgram = (AProgram) start.getPProgram();
        PDeviceDcl pDeviceDcl = aProgram.getDeviceDcl().get(0);
        assertEquals(aProgram, pDeviceDcl.parent());
    }

    // Assert first router node's var name is "r3"
    @Test
    void parseTest28() throws ParserException, IOException, LexerException {
        Start start = Parser.fromString(program27).parse();
        AProgram aProgram = (AProgram) start.getPProgram();
        ARouterDeviceDcl pDeviceDcl = (ARouterDeviceDcl)aProgram.getDeviceDcl().get(0);
        AVar var = (AVar)pDeviceDcl.getVar().get(0);
        assertEquals("r3", var.getIdentifier().getText());
    }

    // Assert second declaration's node first router var name is "r1"
    @Test
    void parseTest29() throws ParserException, IOException, LexerException {
        Start start = Parser.fromString(program27).parse();
        AProgram aProgram = (AProgram) start.getPProgram();
        ARouterDeviceDcl pDeviceDcl = (ARouterDeviceDcl)aProgram.getDeviceDcl().get(1);
        AVar var = (AVar)pDeviceDcl.getVar().get(0);
        assertEquals("r1", var.getIdentifier().getText());
    }

    // Assert second device declaration node's second router has variable name "R2"
    @Test
    void parseTest30() throws ParserException, IOException, LexerException {
        Start start = Parser.fromString(program27).parse();
        AProgram aProgram = (AProgram) start.getPProgram();
        ARouterDeviceDcl pDeviceDcl = (ARouterDeviceDcl)aProgram.getDeviceDcl().get(1);
        AVar var = (AVar)pDeviceDcl.getVar().get(1);
        assertEquals("R2", var.getIdentifier().getText());
    }
}

