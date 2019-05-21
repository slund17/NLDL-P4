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
        StringReader stringReader = new StringReader(code);
        PushbackReader pushbackReader = new PushbackReader(stringReader);
        Parser parser = createParser(pushbackReader);
        Start start = null;
        try {
            start = parser.parse();
        } catch (ParserException | LexerException | IOException e) {
            throw new RuntimeException(e);
        }
        return start;
    }

    public static Start createFromFile(String path) {
        File source = new File(path);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(source));
            PushbackReader pushbackReader = new PushbackReader(bufferedReader, 2048);
            Parser parser = createParser(pushbackReader);
            return parser.parse();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Parser createParser(PushbackReader pushbackReader) {
        Lexer lexer = new Lexer(pushbackReader);
        return new Parser(lexer);
    }
}
