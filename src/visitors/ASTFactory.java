package visitors;

import codegeneration.CodeGenerator;
import com.dat405.nldl.lexer.Lexer;
import com.dat405.nldl.lexer.LexerException;
import com.dat405.nldl.node.Start;
import com.dat405.nldl.parser.Parser;
import com.dat405.nldl.parser.ParserException;
import visitors.*;

import java.io.*;

public class ASTFactory {

    public static void main(String[] args) throws IOException, ParserException, LexerException {
        Parser parser = createParserFromFile("src//my_source.txt");
        Start start = parser.parse();
        SemanticsVisitor semantics = new SemanticsVisitor();
        start.apply(semantics);
        //start.apply(new PrintVisitor());
        CodeGenerator codeGenerator = new CodeGenerator(semantics.envR);
        codeGenerator.generate();
    }

    public static Parser createParserFromFile(String filePath) throws ParserException, IOException, LexerException {
        File source = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(source));
        PushbackReader pushbackReader = new PushbackReader(bufferedReader, 2048);
        Lexer lexer = new Lexer(pushbackReader);
        Parser parser = new Parser(lexer);
        return parser;
    }


    public static Start startFromString(String code) {
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
