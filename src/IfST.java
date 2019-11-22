import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.IOException;
import java.io.PrintWriter;


public class IfST {

    public static void main(String[] args) throws IOException {

        String s = "if a >= b + c then w = 5 else  a = c / 5";
        CharStream inputStream = CharStreams.fromString(s); // Read input string convert to CharStream
        IfstLexer ifstLexer = new IfstLexer(inputStream);  // generated Lexer With ANTLR4 Scan input CharStream make Lexer
        TokenStream tokenStream = new CommonTokenStream(ifstLexer); // Give Token stream from lexer
        IfstParser ifstParser = new IfstParser(tokenStream); // Pass Token stream to Generated Parser to Parse Tokens
        IfstParser.IfStContext result = ifstParser.ifSt(); // get Parse result from input string
        System.out.println(result.toStringTree(ifstParser)); // get parse tree string and Print in Output
        try (PrintWriter out = new PrintWriter("out.txt")) {
            out.println(result.toStringTree(ifstParser)); //Write Output in out.txt
        } catch (IOException e) {
            System.out.println(e.getStackTrace().toString());
        }
    }
}
