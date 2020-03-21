import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import intermediate.crawler.*;
import intermediate.optimizer.ReachingDefs.ReachingDefs;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;


public class JavaGrammar extends JFrame{

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

        Visualizer visualizer = new Visualizer(cfg);

        ReachingDefs reachingDefs = new ReachingDefs(cfg);
        List<Block> allBlocks = reachingDefs.run();



    }

}
