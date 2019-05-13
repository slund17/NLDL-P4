/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.dat405.nldl.node;

import com.dat405.nldl.analysis.*;

@SuppressWarnings("nls")
public final class TSettings extends Token
{
    public TSettings(String text)
    {
        setText(text);
    }

    public TSettings(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TSettings(getText(), getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTSettings(this);
    }
}