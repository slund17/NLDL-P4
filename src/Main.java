

import codegeneration.CodeGenerator;
import com.dat405.nldl.analysis.DepthFirstAdapter;
import com.dat405.nldl.lexer.Lexer;
import com.dat405.nldl.lexer.LexerException;
import com.dat405.nldl.node.Start;
import com.dat405.nldl.parser.Parser;
import com.dat405.nldl.parser.ParserException;
import symbols.IpAddress;
import symbols.PhysicalInterface;
import visitors.PrintVisitor;
import visitors.SemanticsVisitor;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException, ParserException, LexerException {
        Parser parser = createParser();
        Start start = parser.parse();
        SemanticsVisitor semantics = new SemanticsVisitor();
        start.apply(semantics);
        //start.apply(new PrintVisitor());
        CodeGenerator codeGenerator = new CodeGenerator(semantics.envR);
        codeGenerator.generate();
    }

    public static Parser createParser() throws ParserException, IOException, LexerException {
        File source = new File("src//my_source.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(source));
        PushbackReader pushbackReader = new PushbackReader(bufferedReader, 2048);
        Lexer lexer = new Lexer(pushbackReader);
        Parser parser = new Parser(lexer);
        return parser;
    }
}
