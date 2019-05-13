/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.dat405.nldl.node;

import java.util.*;
import com.dat405.nldl.analysis.*;

@SuppressWarnings("nls")
public final class ASettingBlock extends PSettingBlock
{
    private TProtocol _protocol_;
    private final LinkedList<PS> _s_ = new LinkedList<PS>();

    public ASettingBlock()
    {
        // Constructor
    }

    public ASettingBlock(
        @SuppressWarnings("hiding") TProtocol _protocol_,
        @SuppressWarnings("hiding") List<?> _s_)
    {
        // Constructor
        setProtocol(_protocol_);

        setS(_s_);

    }

    @Override
    public Object clone()
    {
        return new ASettingBlock(
            cloneNode(this._protocol_),
            cloneList(this._s_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASettingBlock(this);
    }

    public TProtocol getProtocol()
    {
        return this._protocol_;
    }

    public void setProtocol(TProtocol node)
    {
        if(this._protocol_ != null)
        {
            this._protocol_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._protocol_ = node;
    }

    public LinkedList<PS> getS()
    {
        return this._s_;
    }

    public void setS(List<?> list)
    {
        for(PS e : this._s_)
        {
            e.parent(null);
        }
        this._s_.clear();

        for(Object obj_e : list)
        {
            PS e = (PS) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._s_.add(e);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._protocol_)
            + toString(this._s_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._protocol_ == child)
        {
            this._protocol_ = null;
            return;
        }

        if(this._s_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._protocol_ == oldChild)
        {
            setProtocol((TProtocol) newChild);
            return;
        }

        for(ListIterator<PS> i = this._s_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PS) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        throw new RuntimeException("Not a child.");
    }
}