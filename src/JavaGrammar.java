import intermediate.shcema.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class JavaGrammar {

    public static void main(String[] args) throws IOException {

        String filename = System.getProperty("user.dir") + "\\input.txt";
        CharStream inputStream = CharStreams.fromFileName(filename); // Read input string convert to CharStream
        JavaLexer javaLexer = new JavaLexer(inputStream);  // generated Lexer With ANTLR4 Scan input CharStream make Lexer
        TokenStream tokenStream = new CommonTokenStream(javaLexer); // Give Token stream from lexer
        JavaParser javaParser = new JavaParser(tokenStream); // Pass Token stream to Generated Parser to Parse Tokens

        ParseTree parseTree = javaParser.compilationUnit();
        // Get Parser result form compilationUnit grammar
        JavaParser.CompilationUnitContext result = javaParser.compilationUnit();
        Visualizer visualizer = new Visualizer(parseTree); //Visualize the result of Parse tree
        System.out.println("---------------------");
        System.out.println(javaParser.intermedList.toString());


    }

}
