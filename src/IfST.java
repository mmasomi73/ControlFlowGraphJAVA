import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.IOException;

import static java.lang.System.currentTimeMillis;

public class IfST {

    public static void main(String[] args) throws IOException {

        CharStream inputStream = CharStreams.fromString("if a >= b + c then w = 5 else  a = c / 5");
        IfstLexer ifstLexer = new IfstLexer(inputStream);
        TokenStream tokenStream = new CommonTokenStream(ifstLexer);
        IfstParser ifstParser = new IfstParser(tokenStream);
        IfstParser.IfStContext result = ifstParser.ifSt();
        System.out.println(result.toStringTree(ifstParser));
    }
}
