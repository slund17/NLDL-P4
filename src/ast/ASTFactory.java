package ast;

import codegeneration.CodeGenerator;
import com.dat405.nldl.lexer.Lexer;
import com.dat405.nldl.lexer.LexerException;
import com.dat405.nldl.node.Start;
import com.dat405.nldl.parser.Parser;
import com.dat405.nldl.parser.ParserException;
import visitors.*;

import java.io.*;

public class ASTFactory {

    public static Start fromString(String code) {
        StringReader stringReader = new StringReader(code);
        PushbackReader pushbackReader = new PushbackReader(stringReader);
        Lexer lexer = new Lexer(pushbackReader);
        Parser parser = new Parser(lexer);
        Start start = null;
        try {
            start = parser.parse();
        } catch (ParserException | LexerException | IOException e) {
            throw new RuntimeException(e);
        }
        return start;
    }
}
