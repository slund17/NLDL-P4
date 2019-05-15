package visitors;

import com.dat405.nldl.analysis.DepthFirstAdapter;
import com.dat405.nldl.node.*;
import symbolTables.ConnectionSymbolTable;
import symbolTables.GroupSymbolTable;
import symbolTables.RouterSymbolTable;
import symbolTables.SettingSymbolTable;
import symbols.Router;

public class SemanticsVisitor extends DepthFirstAdapter {

    SettingSymbolTable envS = new SettingSymbolTable();

    GroupSymbolTable envG = new GroupSymbolTable();

    RouterSymbolTable envR = new RouterSymbolTable();

    ConnectionSymbolTable envC = new ConnectionSymbolTable();

    @Override
    public void inStart(Start node) { //TODO semantics
        super.inStart(node);
    }

    @Override
    public void outStart(Start node) { //TODO semantics
        super.outStart(node);
    }

    @Override
    public void inAProgram(AProgram node) { //TODO semantics
        super.inAProgram(node);
    }

    @Override
    public void outAProgram(AProgram node) { //TODO semantics
        super.outAProgram(node);
    }

    @Override
    public void inARouterDeviceDcl(ARouterDeviceDcl node) { //TODO semantics
        super.inARouterDeviceDcl(node);
    }

    @Override
    public void outARouterDeviceDcl(ARouterDeviceDcl node) {
        for (PVar pVar : node.getVar()) {
            String var = (String) getOut(pVar);
            envR.enterSymbol(var, new Router());
        }
    }

    @Override
    public void inASegmentDeviceDcl(ASegmentDeviceDcl node) { //TODO semantics
        super.inASegmentDeviceDcl(node);
    }

    @Override
    public void outASegmentDeviceDcl(ASegmentDeviceDcl node) { //TODO semantics
        super.outASegmentDeviceDcl(node);
    }

    @Override
    public void inAVar(AVar node) { //TODO semantics
        super.inAVar(node);
    }

    @Override
    public void outAVar(AVar node) {
        setOut(node, node.getIdentifier().getText());
    }

    @Override
    public void inASettingDcl(ASettingDcl node) { //TODO semantics
        super.inASettingDcl(node);
    }

    @Override
    public void outASettingDcl(ASettingDcl node) { //TODO semantics
        super.outASettingDcl(node);
    }

    @Override
    public void inASettingBlock(ASettingBlock node) { //TODO semantics
        super.inASettingBlock(node);
    }

    @Override
    public void outASettingBlock(ASettingBlock node) { //TODO semantics
        super.outASettingBlock(node);
    }

    @Override
    public void inAIdentifierS(AIdentifierS node) { //TODO semantics
        super.inAIdentifierS(node);
    }

    @Override
    public void outAIdentifierS(AIdentifierS node) { //TODO semantics
        super.outAIdentifierS(node);
    }

    @Override
    public void inANumS(ANumS node) { //TODO semantics
        super.inANumS(node);
    }

    @Override
    public void outANumS(ANumS node) { //TODO semantics
        super.outANumS(node);
    }

    @Override
    public void inAStringS(AStringS node) { //TODO semantics
        super.inAStringS(node);
    }

    @Override
    public void outAStringS(AStringS node) { //TODO semantics
        super.outAStringS(node);
    }

    @Override
    public void inAIpS(AIpS node) { //TODO semantics
        super.inAIpS(node);
    }

    @Override
    public void outAIpS(AIpS node) { //TODO semantics
        super.outAIpS(node);
    }

    @Override
    public void inAGroupDcl(AGroupDcl node) { //TODO semantics
        envG.openScope();
        super.inAGroupDcl(node);
    }

    @Override
    public void outAGroupDcl(AGroupDcl node) { //TODO semantics
        envG.closeScope();
        super.outAGroupDcl(node);
    }

    @Override
    public void inAVariablesGroupBlock(AVariablesGroupBlock node) { //TODO semantics
        super.inAVariablesGroupBlock(node);
    }

    @Override
    public void outAVariablesGroupBlock(AVariablesGroupBlock node) { //TODO semantics
        super.outAVariablesGroupBlock(node);
    }

    @Override
    public void inASettingGroupBlock(ASettingGroupBlock node) { //TODO semantics
        super.inASettingGroupBlock(node);
    }

    @Override
    public void outASettingGroupBlock(ASettingGroupBlock node) { //TODO semantics
        super.outASettingGroupBlock(node);
    }

    @Override
    public void inAGroupGroupBlock(AGroupGroupBlock node) { //TODO semantics
        envG.openScope();
        super.inAGroupGroupBlock(node);
    }

    @Override
    public void outAGroupGroupBlock(AGroupGroupBlock node) { //TODO semantics
        envG.closeScope();
        super.outAGroupGroupBlock(node);
    }

    @Override
    public void inAConnectionGroupBlock(AConnectionGroupBlock node) { //TODO semantics
        super.inAConnectionGroupBlock(node);
    }

    @Override
    public void outAConnectionGroupBlock(AConnectionGroupBlock node) { //TODO semantics
        super.outAConnectionGroupBlock(node);
    }

    @Override
    public void inAIpGroupBlock(AIpGroupBlock node) { //TODO semantics
        super.inAIpGroupBlock(node);
    }

    @Override
    public void outAIpGroupBlock(AIpGroupBlock node) { //TODO semantics
        super.outAIpGroupBlock(node);
    }

    @Override
    public void inAAssGroupBlock(AAssGroupBlock node) { //TODO semantics
        super.inAAssGroupBlock(node);
    }

    @Override
    public void outAAssGroupBlock(AAssGroupBlock node) { //TODO semantics
        super.outAAssGroupBlock(node);
    }

    @Override
    public void inARrConnectionDcl(ARrConnectionDcl node) { //TODO semantics
        super.inARrConnectionDcl(node);
    }

    @Override
    public void outARrConnectionDcl(ARrConnectionDcl node) { //TODO semantics
        super.outARrConnectionDcl(node);
    }

    @Override
    public void inARsConnectionDcl(ARsConnectionDcl node) { //TODO semantics
        super.inARsConnectionDcl(node);
    }

    @Override
    public void outARsConnectionDcl(ARsConnectionDcl node) { //TODO semantics
        super.outARsConnectionDcl(node);
    }

    @Override
    public void inAMultiConnectionDcl(AMultiConnectionDcl node) { //TODO semantics
        super.inAMultiConnectionDcl(node);
    }

    @Override
    public void outAMultiConnectionDcl(AMultiConnectionDcl node) { //TODO semantics
        super.outAMultiConnectionDcl(node);
    }

    @Override
    public void inATwoIf(ATwoIf node) { //TODO semantics
        super.inATwoIf(node);
    }

    @Override
    public void outATwoIf(ATwoIf node) { //TODO semantics
        super.outATwoIf(node);
    }

    @Override
    public void inAOneIf(AOneIf node) { //TODO semantics
        super.inAOneIf(node);
    }

    @Override
    public void outAOneIf(AOneIf node) { //TODO semantics
        super.outAOneIf(node);
    }

    @Override
    public void inAConnector(AConnector node) { //TODO semantics
        super.inAConnector(node);
    }

    @Override
    public void outAConnector(AConnector node) { //TODO semantics
        super.outAConnector(node);
    }

    @Override
    public void inAFourIp(AFourIp node) { //TODO semantics
        super.inAFourIp(node);
    }

    @Override
    public void outAFourIp(AFourIp node) { //TODO semantics
        super.outAFourIp(node);
    }

    @Override
    public void inAThreeIp(AThreeIp node) { //TODO semantics
        super.inAThreeIp(node);
    }

    @Override
    public void outAThreeIp(AThreeIp node) { //TODO semantics
        super.outAThreeIp(node);
    }

    @Override
    public void inATwoIp(ATwoIp node) { //TODO semantics
        super.inATwoIp(node);
    }

    @Override
    public void outATwoIp(ATwoIp node) { //TODO semantics
        super.outATwoIp(node);
    }

    @Override
    public void inAOneIp(AOneIp node) { //TODO semantics
        super.inAOneIp(node);
    }

    @Override
    public void outAOneIp(AOneIp node) { //TODO semantics
        super.outAOneIp(node);
    }
}
