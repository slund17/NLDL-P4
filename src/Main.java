

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
        String inputFile = "src//my_source.txt";

        // If inputfile path provided as program argument then load it.
        if(args.length == 1){
            inputFile = args[0];
        }

        // Creating a AST
        Start abstractSyntaxTree = ASTfactory.createFromFile(inputFile);

        // Applying visitors to AST
        abstractSyntaxTree.apply(typeChecker);
        abstractSyntaxTree.apply(semantics);
        //start.apply(new PrintVisitor());

        // Code generation
        CodeGenerator codeGenerator = new CodeGenerator(semantics.envR);
        codeGenerator.generate();
    }
}


