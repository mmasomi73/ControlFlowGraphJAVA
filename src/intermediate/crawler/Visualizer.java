package intermediate.crawler;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Visualizer extends JFrame {

    private static final long serialVersionUID = -2707712944901661771L;

    public Visualizer(ControlFlowGraph cfg)
    {
        JGraphXAdapter<Block, Edges> graph = new JGraphXAdapter<Block, Edges>(cfg.getCFG());
        mxGraphComponent graphComponent = new mxGraphComponent(graph);

        graph.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_NOLABEL, "1");

        mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
        layout.execute(graph.getDefaultParent());

        getContentPane().add(graphComponent, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 320);
        this.setVisible(true);
        this.setTitle("Control Flow Graph");


        try {
            String filename = System.getProperty("user.dir") + "\\CFG.png";
            BufferedImage image = mxCellRenderer.createBufferedImage(graph, null, 1, Color.WHITE, true, null);
            ImageIO.write(image, "PNG", new File(filename));
        } catch (IOException ex) {
            System.out.println("Unable to save graph image: " + ex.getLocalizedMessage());
        }
    }
}
