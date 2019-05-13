/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.dat405.nldl.node;

import com.dat405.nldl.analysis.*;

@SuppressWarnings("nls")
public final class ATwoIp extends PIp
{
    private TConst _seg3_;
    private TConst _seg4_;

    public ATwoIp()
    {
        // Constructor
    }

    public ATwoIp(
        @SuppressWarnings("hiding") TConst _seg3_,
        @SuppressWarnings("hiding") TConst _seg4_)
    {
        // Constructor
        setSeg3(_seg3_);

        setSeg4(_seg4_);

    }

    @Override
    public Object clone()
    {
        return new ATwoIp(
            cloneNode(this._seg3_),
            cloneNode(this._seg4_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseATwoIp(this);
    }

    public TConst getSeg3()
    {
        return this._seg3_;
    }

    public void setSeg3(TConst node)
    {
        if(this._seg3_ != null)
        {
            this._seg3_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._seg3_ = node;
    }

    public TConst getSeg4()
    {
        return this._seg4_;
    }

    public void setSeg4(TConst node)
    {
        if(this._seg4_ != null)
        {
            this._seg4_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._seg4_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._seg3_)
            + toString(this._seg4_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._seg3_ == child)
        {
            this._seg3_ = null;
            return;
        }

        if(this._seg4_ == child)
        {
            this._seg4_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._seg3_ == oldChild)
        {
            setSeg3((TConst) newChild);
            return;
        }

        if(this._seg4_ == oldChild)
        {
            setSeg4((TConst) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}