

import ast.ASTfactory;
import codegeneration.CodeGenerator;
import com.dat405.nldl.lexer.LexerException;
import com.dat405.nldl.node.Start;
import com.dat405.nldl.parser.ParserException;
import visitors.SemanticsVisitor;
import visitors.TypeCheckerVisitor;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, ParserException, LexerException {
        TypeCheckerVisitor typeChecker = new TypeCheckerVisitor();
        SemanticsVisitor semantics = new SemanticsVisitor();
        Start abstractSyntaxTree = ASTfactory.createFromFile("src//my_source.txt");
        abstractSyntaxTree.apply(typeChecker);
        abstractSyntaxTree.apply(semantics);
        //start.apply(new PrintVisitor());
        CodeGenerator codeGenerator = new CodeGenerator(semantics.envR);
        codeGenerator.generate();
    }
}


