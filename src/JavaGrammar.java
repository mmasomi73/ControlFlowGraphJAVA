import intermediate.crawler.BlocksExtractor;
import intermediate.crawler.ControlFlowGraph;
import intermediate.crawler.LeadersExtractor;
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
        JavaParser.CompilationUnitContext result = javaParser.compilationUnit();

        LeadersExtractor leaders = new LeadersExtractor(javaParser.intermedList);

        BlocksExtractor blocks = new BlocksExtractor(javaParser.intermedList, leaders.getLeadersIndex());

        ControlFlowGraph cfg = new ControlFlowGraph(blocks);


//        System.out.print(blocks.toString());
        System.out.print(cfg.toString());

    }

}
