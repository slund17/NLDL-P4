/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.dat405.nldl.node;

import com.dat405.nldl.analysis.*;

@SuppressWarnings("nls")
public final class AIpGroupBlock extends PGroupBlock
{
    private PIp _ip_;

    public AIpGroupBlock()
    {
        // Constructor
    }

    public AIpGroupBlock(
        @SuppressWarnings("hiding") PIp _ip_)
    {
        // Constructor
        setIp(_ip_);

    }

    @Override
    public Object clone()
    {
        return new AIpGroupBlock(
            cloneNode(this._ip_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAIpGroupBlock(this);
    }

    public PIp getIp()
    {
        return this._ip_;
    }

    public void setIp(PIp node)
    {
        if(this._ip_ != null)
        {
            this._ip_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._ip_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._ip_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._ip_ == child)
        {
            this._ip_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._ip_ == oldChild)
        {
            setIp((PIp) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}