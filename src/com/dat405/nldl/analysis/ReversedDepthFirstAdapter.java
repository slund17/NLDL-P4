/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.dat405.nldl.analysis;

import java.util.*;
import com.dat405.nldl.node.*;

public class ReversedDepthFirstAdapter extends AnalysisAdapter
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
        node.getEOF().apply(this);
        node.getPProgram().apply(this);
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
            List<PGroupDcl> copy = new ArrayList<PGroupDcl>(node.getGroupDcl());
            Collections.reverse(copy);
            for(PGroupDcl e : copy)
            {
                e.apply(this);
            }
        }
        {
            List<PSettingDcl> copy = new ArrayList<PSettingDcl>(node.getSettingDcl());
            Collections.reverse(copy);
            for(PSettingDcl e : copy)
            {
                e.apply(this);
            }
        }
        {
            List<PDeviceDcl> copy = new ArrayList<PDeviceDcl>(node.getDeviceDcl());
            Collections.reverse(copy);
            for(PDeviceDcl e : copy)
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
            Collections.reverse(copy);
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
            Collections.reverse(copy);
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
        {
            List<PSettingBlock> copy = new ArrayList<PSettingBlock>(node.getSettingBlock());
            Collections.reverse(copy);
            for(PSettingBlock e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
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
        {
            List<PS> copy = new ArrayList<PS>(node.getS());
            Collections.reverse(copy);
            for(PS e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getProtocol() != null)
        {
            node.getProtocol().apply(this);
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

    public void inANumS(ANumS node)
    {
        defaultIn(node);
    }

    public void outANumS(ANumS node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANumS(ANumS node)
    {
        inANumS(node);
        if(node.getConst() != null)
        {
            node.getConst().apply(this);
        }
        outANumS(node);
    }

    public void inAStringS(AStringS node)
    {
        defaultIn(node);
    }

    public void outAStringS(AStringS node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAStringS(AStringS node)
    {
        inAStringS(node);
        if(node.getString() != null)
        {
            node.getString().apply(this);
        }
        outAStringS(node);
    }

    public void inAIpS(AIpS node)
    {
        defaultIn(node);
    }

    public void outAIpS(AIpS node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIpS(AIpS node)
    {
        inAIpS(node);
        if(node.getIp() != null)
        {
            node.getIp().apply(this);
        }
        outAIpS(node);
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
        {
            List<PGroupBlock> copy = new ArrayList<PGroupBlock>(node.getGroupBlock());
            Collections.reverse(copy);
            for(PGroupBlock e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        outAGroupDcl(node);
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
            Collections.reverse(copy);
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
        if(node.getConnectionDcl() != null)
        {
            node.getConnectionDcl().apply(this);
        }
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
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
        if(node.getSecond() != null)
        {
            node.getSecond().apply(this);
        }
        if(node.getFirst() != null)
        {
            node.getFirst().apply(this);
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
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        if(node.getFirst() != null)
        {
            node.getFirst().apply(this);
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
            Collections.reverse(copy);
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
        if(node.getSecond() != null)
        {
            node.getSecond().apply(this);
        }
        if(node.getFirst() != null)
        {
            node.getFirst().apply(this);
        }
        if(node.getInterfaceType() != null)
        {
            node.getInterfaceType().apply(this);
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
        if(node.getFirst() != null)
        {
            node.getFirst().apply(this);
        }
        if(node.getInterfaceType() != null)
        {
            node.getInterfaceType().apply(this);
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
        if(node.getIf() != null)
        {
            node.getIf().apply(this);
        }
        if(node.getConst() != null)
        {
            node.getConst().apply(this);
        }
        if(node.getIp() != null)
        {
            node.getIp().apply(this);
        }
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
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
        if(node.getSeg4() != null)
        {
            node.getSeg4().apply(this);
        }
        if(node.getSeg3() != null)
        {
            node.getSeg3().apply(this);
        }
        if(node.getSeg2() != null)
        {
            node.getSeg2().apply(this);
        }
        if(node.getSeg1() != null)
        {
            node.getSeg1().apply(this);
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
        if(node.getSeg4() != null)
        {
            node.getSeg4().apply(this);
        }
        if(node.getSeg3() != null)
        {
            node.getSeg3().apply(this);
        }
        if(node.getSeg2() != null)
        {
            node.getSeg2().apply(this);
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
        if(node.getSeg4() != null)
        {
            node.getSeg4().apply(this);
        }
        if(node.getSeg3() != null)
        {
            node.getSeg3().apply(this);
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