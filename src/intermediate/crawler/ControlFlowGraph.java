package intermediate.crawler;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.mxgraph.view.mxGraph;
import intermediate.shcema.IntermediateScheme;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedPseudograph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ControlFlowGraph {

    private Multimap<String, String> edges = ArrayListMultimap.create();
    private Graph<Block, DefaultEdge> cfg = new DirectedPseudograph<>(DefaultEdge.class);
    private mxGraph graph = new mxGraph();
    private Object parent = graph.getDefaultParent();
    HashMap<String, Object> map = new HashMap<>();

    private List<Block> blockList;
    private BlocksExtractor blocks;

    public ControlFlowGraph(BlocksExtractor blocks) {
        graph.getModel().beginUpdate();
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

                addVertex(this.blockList.get(i).getName(),this.blockList.get(i).toDiagramString(),block.getName(),block.toDiagramString());
            }
            if (!isUnconditionalBranch(lastST) && i < this.blockList.size() - 1) {
                edges.put(this.blockList.get(i).getName(), this.blockList.get(i + 1).getName());
                cfg.addEdge(this.blockList.get(i), this.blockList.get(i + 1));

                addVertex(this.blockList.get(i).getName(),this.blockList.get(i).toDiagramString(),this.blockList.get(i+1).getName(),this.blockList.get(i+1).toDiagramString());

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

    public mxGraph getGraph(){
        this.findFlow();
        return graph;
    }

    private void addVertex(String key_1, String value_1,String key_2, String value_2){
        Object v1,v2;
        try {
            if (map.containsKey(key_1)){
                v1 = map.get(key_1);
            }else{
                v1 = graph.insertVertex(parent, null, value_1, 20, 20, 20, 20);
                map.put(key_1,v1);
            }

            if (map.containsKey(key_2)){
                v2 = map.get(key_2);
            }else{
                v2 = graph.insertVertex(parent, null, value_2, 20, 20, 20, 20);
                map.put(key_2,v1);
            }
            graph.insertEdge(parent, null, null, v1, v2);
            graph.updateCellSize(v1, true);
            graph.updateCellSize(v2, true);
        } finally {
            graph.getModel().endUpdate();
        }
    }
}
