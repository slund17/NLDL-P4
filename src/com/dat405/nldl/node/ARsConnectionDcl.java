/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.dat405.nldl.node;

import com.dat405.nldl.analysis.*;

@SuppressWarnings("nls")
public final class ARsConnectionDcl extends PConnectionDcl
{
    private PConnector _first_;
    private PVar _var_;

    public ARsConnectionDcl()
    {
        // Constructor
    }

    public ARsConnectionDcl(
        @SuppressWarnings("hiding") PConnector _first_,
        @SuppressWarnings("hiding") PVar _var_)
    {
        // Constructor
        setFirst(_first_);

        setVar(_var_);

    }

    @Override
    public Object clone()
    {
        return new ARsConnectionDcl(
            cloneNode(this._first_),
            cloneNode(this._var_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseARsConnectionDcl(this);
    }

    public PConnector getFirst()
    {
        return this._first_;
    }

    public void setFirst(PConnector node)
    {
        if(this._first_ != null)
        {
            this._first_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._first_ = node;
    }

    public PVar getVar()
    {
        return this._var_;
    }

    public void setVar(PVar node)
    {
        if(this._var_ != null)
        {
            this._var_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._var_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._first_)
            + toString(this._var_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._first_ == child)
        {
            this._first_ = null;
            return;
        }

        if(this._var_ == child)
        {
            this._var_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._first_ == oldChild)
        {
            setFirst((PConnector) newChild);
            return;
        }

        if(this._var_ == oldChild)
        {
            setVar((PVar) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
