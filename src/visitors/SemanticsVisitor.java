package visitors;

import com.dat405.nldl.analysis.DepthFirstAdapter;
import com.dat405.nldl.node.*;
import settings.InterfaceSetting;
import settings.RouterSetting;
import settings.Setting;
import symbolTables.ConnectionSymbolTable;
import symbolTables.GroupSymbolTable;
import symbolTables.RouterSymbolTable;
import symbolTables.SettingSymbolTable;
import symbols.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings({"unchecked"})
public class SemanticsVisitor extends DepthFirstAdapter {

    SettingSymbolTable envS = new SettingSymbolTable();

    GroupSymbolTable envG = new GroupSymbolTable();

    public RouterSymbolTable envR = new RouterSymbolTable();

    ConnectionSymbolTable envC = new ConnectionSymbolTable();

    @Override
    public void outARouterDeviceDcl(ARouterDeviceDcl node) {
        for (PVar pVar : node.getVar()) {
            String var = (String) getOut(pVar);
            envR.enterSymbol(var, new Router(var));
        }
    }

    @Override
    public void outASegmentDeviceDcl(ASegmentDeviceDcl node) {
        super.outASegmentDeviceDcl(node);
        //TODO do we need to add something to the interface to tell it that it was a segment connection?
    }

    @Override
    public void outAVar(AVar node) {
        setOut(node, node.getIdentifier().getText());
    }

    @Override
    public void inASettingDcl(ASettingDcl node) {
        super.inASettingDcl(node);
    }

    @Override
    public void outASettingDcl(ASettingDcl node) {
        String varName = ((AVar)node.getVar()).getIdentifier().getText();

        Set<Setting> setS = new HashSet<>();

        for (PSettingBlock pSettingBlock : node.getSettingBlock()) {
            Setting set = (Setting)getOut(pSettingBlock);
            setS.add(set);
        }

        envS.enterSymbol(varName, setS);
    }

    @Override
    public void inASettingBlock(ASettingBlock node) {
        super.inASettingBlock(node);
    }

    @Override
    public void outASettingBlock(ASettingBlock node) {
        List<Object> objects = new ArrayList<>();

        if(node.getProtocol()!=null){
            objects.add(node.getProtocol().getText());
        }

        for (PS ps : node.getS()) {
            objects.add(getOut(ps));
        }

        Setting setting = Setting.getSetting(objects);
        setOut(node, setting);
    }

    @Override
    public void outAIdentifierS(AIdentifierS node) {
        setOut(node, node.getIdentifier().getText());
    }


    @Override
    public void outANumS(ANumS node) {
        setOut(node, Integer.valueOf(node.getConst().getText()));
    }

    @Override
    public void outAStringS(AStringS node) {
        setOut(node, node.getString().getText());
    }

    @Override
    public void outAIpS(AIpS node) {
        //Transfer the ip upwards
        setOut(node, getOut(node.getIp()));
    }

    @Override
    public void inAGroupDcl(AGroupDcl node) {
        envG.openScope();
    }

    @Override
    public void outAGroupDcl(AGroupDcl node) {
        envG.closeScope();
    }

    @Override
    public void outAVariablesGroupBlock(AVariablesGroupBlock node) {
        for (PVar var : node.getVar()) {
            String varName = (String)getOut(var);
            if(envC.containsSymbol(varName)){
                for (PhysicalInterface physicalInterface : envC.retrieveSymbol(varName)) {
                    for (Setting setting : envG.retrieveGroup().getSettings()) {
                        if(setting instanceof InterfaceSetting){
                            physicalInterface.enterSetting((InterfaceSetting) setting);
                        }
                    }
                }
            } else if(envS.containsSymbol(varName)){
                for (Setting setting : envS.retrieveSymbol(varName)) {
                    envG.retrieveGroup().addSetting(setting);
                }
            } else if(envR.containsSymbol(varName)){
                Router router = envR.retrieveSymbol(varName);
                for (Setting setting : envG.retrieveGroup().getSettings()) {
                    if(setting instanceof RouterSetting){
                        router.enterSetting((RouterSetting) setting);
                    }
                }
            } else {
                Token start = ((AVar)var).getIdentifier();
                throw new RuntimeException(String.format("Unknown variable position: [%d,%d]", start.getLine(), start.getPos()));
            }
        }
    }

    @Override
    public void outASettingGroupBlock(ASettingGroupBlock node) {
        Setting setting = (Setting) getOut(node.getSettingBlock());

        envG.retrieveGroup().addSetting(setting);
    }

    @Override
    public void inAGroupGroupBlock(AGroupGroupBlock node) {
        envG.openScope();
    }

    @Override
    public void outAGroupGroupBlock(AGroupGroupBlock node) {
        envG.closeScope();
    }

    @Override
    public void outAConnectionGroupBlock(AConnectionGroupBlock node) {
        //Do nothing
    }

    @Override
    public void outAIpGroupBlock(AIpGroupBlock node) {
        envG.retrieveGroup().setIp((IpAddress) getOut(node.getIp()));
    }

    @Override
    public void outAAssGroupBlock(AAssGroupBlock node) {
        String varName = (String) getOut(node.getVar());
        List<PhysicalInterface> interfaces = (List<PhysicalInterface>) getOut(node.getConnectionDcl());
        envC.enterSymbol(varName, interfaces);
    }

    @Override
    public void outARrConnectionDcl(ARrConnectionDcl node) {
        List<PhysicalInterface> interfaces = new ArrayList<>();

        interfaces.add((PhysicalInterface) getOut(node.getFirst()));
        interfaces.add((PhysicalInterface) getOut(node.getSecond()));

        setOut(node, interfaces);
    }

    @Override
    public void outARsConnectionDcl(ARsConnectionDcl node) {
        List<PhysicalInterface> interfaces = new ArrayList<>();

        interfaces.add((PhysicalInterface) getOut(node.getFirst()));

        setOut(node, interfaces);

        //TODO do we need to add something to the interface to tell it that it was a segment connection?
    }

    @Override
    public void outAMultiConnectionDcl(AMultiConnectionDcl node) {
        List<PhysicalInterface> interfaces = new ArrayList<>();

        for (PConnector connector : node.getConnector()) {
            interfaces.add((PhysicalInterface) getOut(connector));
        }

        setOut(node, interfaces);

        //TODO do we need to add something to the interface to tell it that it was a segment connection?
    }


    private InterfaceType getInterfaceType(TInterfaceType ttype){
        switch (ttype.getText().toUpperCase()){
            case "F":
            case "FE": return InterfaceType.FAST_ETHERNET;
            case "G":
            case "GB": return InterfaceType.GIGABIT;
            case "E": return InterfaceType.ETHERNET;
            default: throw new RuntimeException("Unsupported interface type");
        }
    }


    @Override
    public void outATwoIf(ATwoIf node) {
        int first = Integer.valueOf(node.getFirst().getText());
        int second = Integer.valueOf(node.getSecond().getText());
        InterfaceType type = getInterfaceType(node.getInterfaceType());

        setOut(node, new InterfaceIndex(first, second, type));
    }


    @Override
    public void outAOneIf(AOneIf node) {
        int first = Integer.valueOf(node.getFirst().getText());
        InterfaceType type = getInterfaceType(node.getInterfaceType());

        setOut(node, new InterfaceIndex(first, type));
    }

    @Override
    public void inAConnector(AConnector node) {
        super.inAConnector(node);
    }

    @Override
    public void outAConnector(AConnector node) {
        String varName = (String)getOut(node.getVar());
        IpAddress ip = (IpAddress)getOut(node.getIp());
        int mask = Integer.valueOf(node.getConst().getText());
        InterfaceIndex infIndex = (InterfaceIndex)getOut(node.getIf());

        Router router = envR.retrieveSymbol(varName);
        PhysicalInterface physicalInterface = router.enterInterface(infIndex);

        physicalInterface.enterIP(ip);
        physicalInterface.enterMask(mask);

        for (Setting setting : envG.retrieveGroup().getSettings()) {
            if(setting instanceof InterfaceSetting){
                physicalInterface.enterSetting((InterfaceSetting) setting);
            }
        }

        setOut(node, physicalInterface);
    }

    @Override
    public void outAFourIp(AFourIp node) {
        int seg1 = Integer.valueOf(node.getSeg1().getText());
        int seg2 = Integer.valueOf(node.getSeg2().getText());
        int seg3 = Integer.valueOf(node.getSeg3().getText());
        int seg4 = Integer.valueOf(node.getSeg4().getText());

        setOut(node, new IpAddress(seg1, seg2, seg3, seg4));
    }

    @Override
    public void outAThreeIp(AThreeIp node) {
        int seg1 = envG.retrieveGroup().getIp().seg1;
        int seg2 = Integer.valueOf(node.getSeg2().getText());
        int seg3 = Integer.valueOf(node.getSeg3().getText());
        int seg4 = Integer.valueOf(node.getSeg4().getText());

        setOut(node, new IpAddress(seg1, seg2, seg3, seg4));
    }

    @Override
    public void outATwoIp(ATwoIp node) {
        int seg1 = envG.retrieveGroup().getIp().seg1;
        int seg2 = envG.retrieveGroup().getIp().seg2;
        int seg3 = Integer.valueOf(node.getSeg3().getText());
        int seg4 = Integer.valueOf(node.getSeg4().getText());

        setOut(node, new IpAddress(seg1, seg2, seg3, seg4));
    }

    @Override
    public void outAOneIp(AOneIp node) {
        int seg1 = envG.retrieveGroup().getIp().seg1;
        int seg2 = envG.retrieveGroup().getIp().seg2;
        int seg3 = envG.retrieveGroup().getIp().seg3;
        int seg4 = Integer.valueOf(node.getSeg4().getText());

        setOut(node, new IpAddress(seg1, seg2, seg3, seg4));
    }
}
