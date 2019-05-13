/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.dat405.nldl.lexer;

import com.dat405.nldl.node.*;

@SuppressWarnings("serial")
public class LexerException extends Exception
{
    private InvalidToken invalidToken;
    
    public LexerException(@SuppressWarnings("hiding") InvalidToken invalidToken, String message)
    {
        super(message);
        this.invalidToken = invalidToken;
    }

    public InvalidToken getToken()
    {
        return this.invalidToken;
    }
}
