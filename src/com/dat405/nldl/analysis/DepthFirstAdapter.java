/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.dat405.nldl.analysis;

import java.util.*;
import com.dat405.nldl.node.*;

public class DepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getPProgram().apply(this);
        node.getEOF().apply(this);
        outStart(node);
    }

    public void inAProgram(AProgram node)
    {
        defaultIn(node);
    }

    public void outAProgram(AProgram node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAProgram(AProgram node)
    {
        inAProgram(node);
        {
            List<PDeviceDcl> copy = new ArrayList<PDeviceDcl>(node.getDeviceDcl());
            for(PDeviceDcl e : copy)
            {
                e.apply(this);
            }
        }
        {
            List<PSettingDcl> copy = new ArrayList<PSettingDcl>(node.getSettingDcl());
            for(PSettingDcl e : copy)
            {
                e.apply(this);
            }
        }
        {
            List<PGroupDcl> copy = new ArrayList<PGroupDcl>(node.getGroupDcl());
            for(PGroupDcl e : copy)
            {
                e.apply(this);
            }
        }
        outAProgram(node);
    }

    public void inARouterDeviceDcl(ARouterDeviceDcl node)
    {
        defaultIn(node);
    }

    public void outARouterDeviceDcl(ARouterDeviceDcl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARouterDeviceDcl(ARouterDeviceDcl node)
    {
        inARouterDeviceDcl(node);
        {
            List<PVar> copy = new ArrayList<PVar>(node.getVar());
            for(PVar e : copy)
            {
                e.apply(this);
            }
        }
        outARouterDeviceDcl(node);
    }

    public void inASegmentDeviceDcl(ASegmentDeviceDcl node)
    {
        defaultIn(node);
    }

    public void outASegmentDeviceDcl(ASegmentDeviceDcl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASegmentDeviceDcl(ASegmentDeviceDcl node)
    {
        inASegmentDeviceDcl(node);
        {
            List<PVar> copy = new ArrayList<PVar>(node.getVar());
            for(PVar e : copy)
            {
                e.apply(this);
            }
        }
        outASegmentDeviceDcl(node);
    }

    public void inAVar(AVar node)
    {
        defaultIn(node);
    }

    public void outAVar(AVar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVar(AVar node)
    {
        inAVar(node);
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outAVar(node);
    }

    public void inASettingDcl(ASettingDcl node)
    {
        defaultIn(node);
    }

    public void outASettingDcl(ASettingDcl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASettingDcl(ASettingDcl node)
    {
        inASettingDcl(node);
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        {
            List<PSettingBlock> copy = new ArrayList<PSettingBlock>(node.getSettingBlock());
            for(PSettingBlock e : copy)
            {
                e.apply(this);
            }
        }
        outASettingDcl(node);
    }

    public void inASettingBlock(ASettingBlock node)
    {
        defaultIn(node);
    }

    public void outASettingBlock(ASettingBlock node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASettingBlock(ASettingBlock node)
    {
        inASettingBlock(node);
        if(node.getProtocol() != null)
        {
            node.getProtocol().apply(this);
        }
        {
            List<PS> copy = new ArrayList<PS>(node.getS());
            for(PS e : copy)
            {
                e.apply(this);
            }
        }
        outASettingBlock(node);
    }

    public void inAIdentifierS(AIdentifierS node)
    {
        defaultIn(node);
    }

    public void outAIdentifierS(AIdentifierS node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIdentifierS(AIdentifierS node)
    {
        inAIdentifierS(node);
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        outAIdentifierS(node);
    }

    public void inAConstantS(AConstantS node)
    {
        defaultIn(node);
    }

    public void outAConstantS(AConstantS node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAConstantS(AConstantS node)
    {
        inAConstantS(node);
        if(node.getV() != null)
        {
            node.getV().apply(this);
        }
        outAConstantS(node);
    }

    public void inAGroupDcl(AGroupDcl node)
    {
        defaultIn(node);
    }

    public void outAGroupDcl(AGroupDcl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGroupDcl(AGroupDcl node)
    {
        inAGroupDcl(node);
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        {
            List<PGroupBlock> copy = new ArrayList<PGroupBlock>(node.getGroupBlock());
            for(PGroupBlock e : copy)
            {
                e.apply(this);
            }
        }
        outAGroupDcl(node);
    }

    public void inANumV(ANumV node)
    {
        defaultIn(node);
    }

    public void outANumV(ANumV node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANumV(ANumV node)
    {
        inANumV(node);
        if(node.getConst() != null)
        {
            node.getConst().apply(this);
        }
        outANumV(node);
    }

    public void inAStringV(AStringV node)
    {
        defaultIn(node);
    }

    public void outAStringV(AStringV node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAStringV(AStringV node)
    {
        inAStringV(node);
        if(node.getString() != null)
        {
            node.getString().apply(this);
        }
        outAStringV(node);
    }

    public void inAIpV(AIpV node)
    {
        defaultIn(node);
    }

    public void outAIpV(AIpV node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIpV(AIpV node)
    {
        inAIpV(node);
        if(node.getIp() != null)
        {
            node.getIp().apply(this);
        }
        outAIpV(node);
    }

    public void inAVariablesGroupBlock(AVariablesGroupBlock node)
    {
        defaultIn(node);
    }

    public void outAVariablesGroupBlock(AVariablesGroupBlock node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVariablesGroupBlock(AVariablesGroupBlock node)
    {
        inAVariablesGroupBlock(node);
        {
            List<PVar> copy = new ArrayList<PVar>(node.getVar());
            for(PVar e : copy)
            {
                e.apply(this);
            }
        }
        outAVariablesGroupBlock(node);
    }

    public void inASettingGroupBlock(ASettingGroupBlock node)
    {
        defaultIn(node);
    }

    public void outASettingGroupBlock(ASettingGroupBlock node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASettingGroupBlock(ASettingGroupBlock node)
    {
        inASettingGroupBlock(node);
        if(node.getSettingBlock() != null)
        {
            node.getSettingBlock().apply(this);
        }
        outASettingGroupBlock(node);
    }

    public void inAGroupGroupBlock(AGroupGroupBlock node)
    {
        defaultIn(node);
    }

    public void outAGroupGroupBlock(AGroupGroupBlock node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGroupGroupBlock(AGroupGroupBlock node)
    {
        inAGroupGroupBlock(node);
        if(node.getGroupDcl() != null)
        {
            node.getGroupDcl().apply(this);
        }
        outAGroupGroupBlock(node);
    }

    public void inAConnectionGroupBlock(AConnectionGroupBlock node)
    {
        defaultIn(node);
    }

    public void outAConnectionGroupBlock(AConnectionGroupBlock node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAConnectionGroupBlock(AConnectionGroupBlock node)
    {
        inAConnectionGroupBlock(node);
        if(node.getConnectionDcl() != null)
        {
            node.getConnectionDcl().apply(this);
        }
        outAConnectionGroupBlock(node);
    }

    public void inAIpGroupBlock(AIpGroupBlock node)
    {
        defaultIn(node);
    }

    public void outAIpGroupBlock(AIpGroupBlock node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIpGroupBlock(AIpGroupBlock node)
    {
        inAIpGroupBlock(node);
        if(node.getIp() != null)
        {
            node.getIp().apply(this);
        }
        outAIpGroupBlock(node);
    }

    public void inAAssGroupBlock(AAssGroupBlock node)
    {
        defaultIn(node);
    }

    public void outAAssGroupBlock(AAssGroupBlock node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAssGroupBlock(AAssGroupBlock node)
    {
        inAAssGroupBlock(node);
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        if(node.getConnectionDcl() != null)
        {
            node.getConnectionDcl().apply(this);
        }
        outAAssGroupBlock(node);
    }

    public void inARrConnectionDcl(ARrConnectionDcl node)
    {
        defaultIn(node);
    }

    public void outARrConnectionDcl(ARrConnectionDcl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARrConnectionDcl(ARrConnectionDcl node)
    {
        inARrConnectionDcl(node);
        if(node.getFirst() != null)
        {
            node.getFirst().apply(this);
        }
        if(node.getSecond() != null)
        {
            node.getSecond().apply(this);
        }
        outARrConnectionDcl(node);
    }

    public void inARsConnectionDcl(ARsConnectionDcl node)
    {
        defaultIn(node);
    }

    public void outARsConnectionDcl(ARsConnectionDcl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARsConnectionDcl(ARsConnectionDcl node)
    {
        inARsConnectionDcl(node);
        if(node.getFirst() != null)
        {
            node.getFirst().apply(this);
        }
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        outARsConnectionDcl(node);
    }

    public void inAMultiConnectionDcl(AMultiConnectionDcl node)
    {
        defaultIn(node);
    }

    public void outAMultiConnectionDcl(AMultiConnectionDcl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultiConnectionDcl(AMultiConnectionDcl node)
    {
        inAMultiConnectionDcl(node);
        {
            List<PConnector> copy = new ArrayList<PConnector>(node.getConnector());
            for(PConnector e : copy)
            {
                e.apply(this);
            }
        }
        outAMultiConnectionDcl(node);
    }

    public void inATwoIf(ATwoIf node)
    {
        defaultIn(node);
    }

    public void outATwoIf(ATwoIf node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATwoIf(ATwoIf node)
    {
        inATwoIf(node);
        if(node.getInterfaceType() != null)
        {
            node.getInterfaceType().apply(this);
        }
        if(node.getFirst() != null)
        {
            node.getFirst().apply(this);
        }
        if(node.getSecond() != null)
        {
            node.getSecond().apply(this);
        }
        outATwoIf(node);
    }

    public void inAOneIf(AOneIf node)
    {
        defaultIn(node);
    }

    public void outAOneIf(AOneIf node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOneIf(AOneIf node)
    {
        inAOneIf(node);
        if(node.getInterfaceType() != null)
        {
            node.getInterfaceType().apply(this);
        }
        if(node.getFirst() != null)
        {
            node.getFirst().apply(this);
        }
        outAOneIf(node);
    }

    public void inAConnector(AConnector node)
    {
        defaultIn(node);
    }

    public void outAConnector(AConnector node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAConnector(AConnector node)
    {
        inAConnector(node);
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        if(node.getIp() != null)
        {
            node.getIp().apply(this);
        }
        if(node.getConst() != null)
        {
            node.getConst().apply(this);
        }
        if(node.getIf() != null)
        {
            node.getIf().apply(this);
        }
        outAConnector(node);
    }

    public void inAFourIp(AFourIp node)
    {
        defaultIn(node);
    }

    public void outAFourIp(AFourIp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFourIp(AFourIp node)
    {
        inAFourIp(node);
        if(node.getSeg1() != null)
        {
            node.getSeg1().apply(this);
        }
        if(node.getSeg2() != null)
        {
            node.getSeg2().apply(this);
        }
        if(node.getSeg3() != null)
        {
            node.getSeg3().apply(this);
        }
        if(node.getSeg4() != null)
        {
            node.getSeg4().apply(this);
        }
        outAFourIp(node);
    }

    public void inAThreeIp(AThreeIp node)
    {
        defaultIn(node);
    }

    public void outAThreeIp(AThreeIp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAThreeIp(AThreeIp node)
    {
        inAThreeIp(node);
        if(node.getSeg2() != null)
        {
            node.getSeg2().apply(this);
        }
        if(node.getSeg3() != null)
        {
            node.getSeg3().apply(this);
        }
        if(node.getSeg4() != null)
        {
            node.getSeg4().apply(this);
        }
        outAThreeIp(node);
    }

    public void inATwoIp(ATwoIp node)
    {
        defaultIn(node);
    }

    public void outATwoIp(ATwoIp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATwoIp(ATwoIp node)
    {
        inATwoIp(node);
        if(node.getSeg3() != null)
        {
            node.getSeg3().apply(this);
        }
        if(node.getSeg4() != null)
        {
            node.getSeg4().apply(this);
        }
        outATwoIp(node);
    }

    public void inAOneIp(AOneIp node)
    {
        defaultIn(node);
    }

    public void outAOneIp(AOneIp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOneIp(AOneIp node)
    {
        inAOneIp(node);
        if(node.getSeg4() != null)
        {
            node.getSeg4().apply(this);
        }
        outAOneIp(node);
    }
}
