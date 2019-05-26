package visitors;

import com.dat405.nldl.analysis.DepthFirstAdapter;
import com.dat405.nldl.node.*;
import symbols.Type;

import java.util.HashMap;

public class TypeCheckerVisitor extends DepthFirstAdapter {

    HashMap<String, Type> symbolTable = new HashMap<>();

    boolean isTypeCorrect(Type actualType, Type correctType){
        return actualType == correctType;
    }


    @Override
    public void outARouterDeviceDcl(ARouterDeviceDcl node) {
        String varName;

        for (PVar var : node.getVar()) {
            varName = (String) getOut(var);
            symbolTable.put(varName, Type.ROUTER);
        }

        setOut(node, Type.OK);
    }

    @Override
    public void outASegmentDeviceDcl(ASegmentDeviceDcl node) {
        String varName;

        for (PVar var : node.getVar()) {
            varName = (String) getOut(var);
            symbolTable.put(varName, Type.SEGMENT);
        }

        setOut(node, Type.OK);

    }

    @Override
    public void outASettingDcl(ASettingDcl node) {
        boolean areChildrenTypeCorrect = true;
        Type actualType;

        for (PSettingBlock settingBlock : node.getSettingBlock()) {
            actualType = (Type)getOut(settingBlock);

            if(!isTypeCorrect(actualType, Type.OK)){
                areChildrenTypeCorrect = false;
            }
        }

        if(areChildrenTypeCorrect){
            symbolTable.put((String)getOut(node.getVar()), Type.SETTING);
        }
    }

    @Override
    public void outAVar(AVar node) {
        setOut(node, node.getIdentifier().getText());
    }


    @Override
    public void outASettingBlock(ASettingBlock node) {
        setOut(node, Type.OK);

    }

    @Override
    public void outAGroupDcl(AGroupDcl node) {
        boolean areChildrenTypeCorrect = true;
        Type actualType;

        for (PGroupBlock groupBlock : node.getGroupBlock()) {
            actualType = (Type)getOut(groupBlock);
            if(!isTypeCorrect(actualType, Type.OK)){
                areChildrenTypeCorrect = false;
            }
        }

        if(areChildrenTypeCorrect){
            setOut(node, Type.OK);
        }
    }


    @Override
    public void outAVariablesGroupBlock(AVariablesGroupBlock node) {
        boolean areChildrenTypeCorrect = true;
        boolean varIsDefinedInSymbolTable;
        Type actualType;
        String varName;
        for (PVar var : node.getVar()) {
            varName = (String)getOut(var);
            varIsDefinedInSymbolTable = symbolTable.containsKey(varName);
            if(varIsDefinedInSymbolTable){
                actualType = symbolTable.get(varName);
                if(isTypeCorrect(actualType, Type.SEGMENT)){
                    areChildrenTypeCorrect = false;
                    throw new RuntimeException("The variable " +varName+ " is of type Segment.");
                }
            } else {
                areChildrenTypeCorrect = false;
                throw new RuntimeException("The variable " + varName + " is not previously defined.");
            }
        }

        if(areChildrenTypeCorrect){
            setOut(node, Type.OK);
        }
    }

    @Override
    public void outASettingGroupBlock(ASettingGroupBlock node) {
        setOut(node, Type.OK);
    }


    @Override
    public void outAGroupGroupBlock(AGroupGroupBlock node) {
        setOut(node, Type.OK);
    }


    @Override
    public void outAConnectionGroupBlock(AConnectionGroupBlock node) {
        setOut(node, Type.OK);

    }

    @Override
    public void outAIpGroupBlock(AIpGroupBlock node) {
        boolean areChildTypeCorrect = isTypeCorrect((Type)getOut(node.getIp()), Type.OK);

        if(areChildTypeCorrect){
            setOut(node, Type.OK);
        }
    }

    @Override
    public void outAAssGroupBlock(AAssGroupBlock node) {
        boolean isChildTypeCorrect = isTypeCorrect((Type) getOut(node.getConnectionDcl()), Type.OK);

        if(isChildTypeCorrect){
            symbolTable.put((String) getOut(node.getVar()), Type.CONNECTION);
        }

        if(isChildTypeCorrect){
            setOut(node, Type.OK);
        }
    }


    @Override
    public void outARrConnectionDcl(ARrConnectionDcl node) {
        boolean areChildrenTypeCorrect = isTypeCorrect((Type)getOut(node.getFirst()), Type.OK)
                && isTypeCorrect((Type)getOut(node.getSecond()), Type.OK);

        if(areChildrenTypeCorrect){
            setOut(node, Type.OK);
        }
    }


    @Override
    public void outARsConnectionDcl(ARsConnectionDcl node) {
        String var = (String)getOut(node.getVar());
        boolean areChildrenTypeCorrect = isTypeCorrect((Type)getOut(node.getFirst()), Type.OK);

        if( areChildrenTypeCorrect && symbolTable.containsKey(var)) {
            areChildrenTypeCorrect = isTypeCorrect(symbolTable.get(var), Type.SEGMENT);
        } else {
            areChildrenTypeCorrect = false;
            throw new RuntimeException("The variable "+var+" is not previously defined.");
        }

        if(areChildrenTypeCorrect){
            setOut(node, Type.OK);
        } else {
            throw new RuntimeException("The variable "+var+" was expected to be a segment, but isn't");
        }

    }

    @Override
    public void inAMultiConnectionDcl(AMultiConnectionDcl node) {



    }

    @Override
    public void outAMultiConnectionDcl(AMultiConnectionDcl node) {
        boolean areChildrenTypeCorrect = true;

        for (PConnector connector : node.getConnector()) {
            if(!isTypeCorrect((Type)getOut(connector), Type.OK)){
                areChildrenTypeCorrect = false;
            }
        }

        if(areChildrenTypeCorrect){
            setOut(node, Type.OK);
        }
    }


    @Override
    public void outAConnector(AConnector node) {
        boolean isVarTypeCorrect;
        boolean isIpTypeCorect = isTypeCorrect((Type)getOut(node.getIp()), Type.OK);

        String var = (String) getOut(node.getVar());

        if(symbolTable.containsKey(var)){
            isVarTypeCorrect = isTypeCorrect(symbolTable.get(var), Type.ROUTER);
        } else {
            throw new RuntimeException("The variable "+var+" was not previously defined");
        }

        if(isVarTypeCorrect && isIpTypeCorect){
            setOut(node, Type.OK);
        }

    }


    @Override
    public void outAFourIp(AFourIp node) {
        setOut(node, Type.OK);
    }


    @Override
    public void inATwoIp(ATwoIp node) {
        setOut(node, Type.OK);

    }

    @Override
    public void outATwoIp(ATwoIp node) {
        setOut(node, Type.OK);

    }

    @Override
    public void outAOneIp(AOneIp node) {
        setOut(node, Type.OK);

    }
}
