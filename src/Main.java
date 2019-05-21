

import ast.ASTfactory;
import codegeneration.CodeGenerator;
import com.dat405.nldl.lexer.LexerException;
import com.dat405.nldl.parser.ParserException;
import visitors.SemanticsVisitor;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, ParserException, LexerException {
        SemanticsVisitor semantics = new SemanticsVisitor();
        ASTfactory.createFromFile("src//my_source.txt").apply(semantics);
        //start.apply(new PrintVisitor());
        CodeGenerator codeGenerator = new CodeGenerator(semantics.envR);
        codeGenerator.generate();
    }
}


