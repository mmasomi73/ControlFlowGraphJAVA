package intermediate.crawler;

import org.jgrapht.graph.DefaultEdge;

public class Edges extends DefaultEdge {

    @Override
    public Block getSource() {
        return (Block) super.getSource();
    }

    @Override
    public Block getTarget() {
        return (Block) super.getTarget();
    }
}
