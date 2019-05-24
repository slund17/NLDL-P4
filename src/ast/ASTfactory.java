package ast;

import codegeneration.CodeGenerator;
import com.dat405.nldl.lexer.Lexer;
import com.dat405.nldl.lexer.LexerException;
import com.dat405.nldl.node.Start;
import com.dat405.nldl.parser.Parser;
import com.dat405.nldl.parser.ParserException;
import visitors.*;

import java.io.*;

public class ASTfactory {

    public static Start createFromString(String code) {
        Parser parser = Parser.fromString(code);
        try {
            return parser.parse();
        } catch (ParserException | LexerException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Start createFromFile(String path) {
        try {
            return Parser.fromFile(path).parse();
        } catch (ParserException | LexerException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
