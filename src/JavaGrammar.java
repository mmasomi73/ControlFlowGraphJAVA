import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class JavaGrammar {

    public static void main(String[] args) throws IOException {

        String filename = System.getProperty("user.dir") + "\\input.txt";
        CharStream inputStream = CharStreams.fromFileName(filename); // Read input string convert to CharStream
        JavaLexer javaLexer = new JavaLexer(inputStream);  // generated Lexer With ANTLR4 Scan input CharStream make Lexer
        TokenStream tokenStream = new CommonTokenStream(javaLexer); // Give Token stream from lexer
        JavaParser javaParser = new JavaParser(tokenStream); // Pass Token stream to Generated Parser to Parse Tokens

//        JavaParser.CompilationUnitContext context = javaParser.compilationUnit();
        ParseTree parseTree = javaParser.compilationUnit();
        AST ast = new AST(parseTree);

        System.out.println(ast);
        try (PrintWriter out = new PrintWriter("out.txt")) {
            out.println(ast); //Write Output in out.txt
        } catch (IOException e) {
            System.out.println(e.getStackTrace().toString());
        }

    }



}
