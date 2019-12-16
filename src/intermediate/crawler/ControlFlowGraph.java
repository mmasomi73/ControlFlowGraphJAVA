package intermediate.crawler;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import intermediate.shcema.IntermediateScheme;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedPseudograph;

import java.util.LinkedList;
import java.util.List;

public class ControlFlowGraph {

    private Multimap<String, String> edges = ArrayListMultimap.create();
    private Graph<Block, DefaultEdge> cfg = new DirectedPseudograph<>(DefaultEdge.class);

    private List<Block> blockList;
    private BlocksExtractor blocks;

    public ControlFlowGraph(BlocksExtractor blocks) {
        this.blockList = blocks.getBlockList();
        this.blocks = blocks;
        for (Block block : this.blockList) {
            cfg.addVertex(block);
        }
    }

    private void findFlow() {

        for (int i = 0; i < this.blockList.size(); i++) {

            IntermediateScheme lastST = this.blockList.get(i).getLastStatement();
            if (isBranch(lastST)) {
                Block block = blocks.getWithLabel(lastST.iScheme.getLabel());
                edges.put(this.blockList.get(i).getName(), block.getName());
                cfg.addEdge(this.blockList.get(i), block);

            }
            if (!isUnconditionalBranch(lastST) && i < this.blockList.size() - 1) {
                edges.put(this.blockList.get(i).getName(), this.blockList.get(i + 1).getName());
                cfg.addEdge(this.blockList.get(i), this.blockList.get(i + 1));

            }
        }
    }

    private boolean isBranch(IntermediateScheme intermediateScheme) {
        return intermediateScheme.type.equals("i") || intermediateScheme.type.equals("g");
    }

    private boolean isUnconditionalBranch(IntermediateScheme intermediateScheme) {
        return intermediateScheme.type.equals("g");
    }

    public Multimap<String, String> getEdges() {
        this.findFlow();
        return edges;
    }

    public String toString() {
        this.findFlow();
        StringBuilder st = new StringBuilder();
        List<String> exists = new LinkedList<>();
        for (String key : edges.keys()) {
            if (!exists.contains(key)) {
                exists.add(key);
                st.append(key).append("->").append(edges.get(key)).append("\n");
            }
        }
        return st.toString();
    }

    public Graph<Block, DefaultEdge> getCFG() {
        this.findFlow();
        return cfg;
    }

}
