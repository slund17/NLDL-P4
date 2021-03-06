/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.dat405.nldl.node;

import java.util.*;
import com.dat405.nldl.analysis.*;

@SuppressWarnings("nls")
public final class AProgram extends PProgram
{
    private final LinkedList<PDeviceDcl> _deviceDcl_ = new LinkedList<PDeviceDcl>();
    private final LinkedList<PSettingDcl> _settingDcl_ = new LinkedList<PSettingDcl>();
    private final LinkedList<PGroupDcl> _groupDcl_ = new LinkedList<PGroupDcl>();

    public AProgram()
    {
        // Constructor
    }

    public AProgram(
        @SuppressWarnings("hiding") List<?> _deviceDcl_,
        @SuppressWarnings("hiding") List<?> _settingDcl_,
        @SuppressWarnings("hiding") List<?> _groupDcl_)
    {
        // Constructor
        setDeviceDcl(_deviceDcl_);

        setSettingDcl(_settingDcl_);

        setGroupDcl(_groupDcl_);

    }

    @Override
    public Object clone()
    {
        return new AProgram(
            cloneList(this._deviceDcl_),
            cloneList(this._settingDcl_),
            cloneList(this._groupDcl_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAProgram(this);
    }

    public LinkedList<PDeviceDcl> getDeviceDcl()
    {
        return this._deviceDcl_;
    }

    public void setDeviceDcl(List<?> list)
    {
        for(PDeviceDcl e : this._deviceDcl_)
        {
            e.parent(null);
        }
        this._deviceDcl_.clear();

        for(Object obj_e : list)
        {
            PDeviceDcl e = (PDeviceDcl) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._deviceDcl_.add(e);
        }
    }

    public LinkedList<PSettingDcl> getSettingDcl()
    {
        return this._settingDcl_;
    }

    public void setSettingDcl(List<?> list)
    {
        for(PSettingDcl e : this._settingDcl_)
        {
            e.parent(null);
        }
        this._settingDcl_.clear();

        for(Object obj_e : list)
        {
            PSettingDcl e = (PSettingDcl) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._settingDcl_.add(e);
        }
    }

    public LinkedList<PGroupDcl> getGroupDcl()
    {
        return this._groupDcl_;
    }

    public void setGroupDcl(List<?> list)
    {
        for(PGroupDcl e : this._groupDcl_)
        {
            e.parent(null);
        }
        this._groupDcl_.clear();

        for(Object obj_e : list)
        {
            PGroupDcl e = (PGroupDcl) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._groupDcl_.add(e);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._deviceDcl_)
            + toString(this._settingDcl_)
            + toString(this._groupDcl_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._deviceDcl_.remove(child))
        {
            return;
        }

        if(this._settingDcl_.remove(child))
        {
            return;
        }

        if(this._groupDcl_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        for(ListIterator<PDeviceDcl> i = this._deviceDcl_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PDeviceDcl) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        for(ListIterator<PSettingDcl> i = this._settingDcl_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PSettingDcl) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        for(ListIterator<PGroupDcl> i = this._groupDcl_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PGroupDcl) newChild);
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
