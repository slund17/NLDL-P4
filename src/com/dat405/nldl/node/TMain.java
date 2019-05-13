/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.dat405.nldl.node;

import com.dat405.nldl.analysis.*;

@SuppressWarnings("nls")
public final class TMain extends Token
{
    public TMain(String text)
    {
        setText(text);
    }

    public TMain(String text, int line, int pos)
    {
        setText(text);
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TMain(getText(), getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTMain(this);
    }
}
