/*
 * Created by JFormDesigner on Sun Mar 22 00:23:47 IRDT 2020
 */

package intermediate.optimizer.ReachingDefs;

import java.awt.event.*;
import java.beans.*;

import intermediate.crawler.Block;
import intermediate.crawler.Edges;
import org.jgrapht.Graph;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;
import java.util.List;

/**
 * @author unknown
 */
public class ReachingDefsForm extends JFrame {
    private List<Block> blocks;
	Graph<Block, Edges> graph;

    public ReachingDefsForm(List<Block> blocks, Graph<Block, Edges> graph) {
        this.blocks = blocks;
		this.graph = graph;
        initComponents();

    }

    private void treBlocksPropertyChange(PropertyChangeEvent e) {
        if (e != null && e.getNewValue() != null)
//        	System.out.println(e.getNewValue().toString());
            clickTreeBlocks(e.getNewValue().toString());
    }

	private void treBlocksMouseClicked(MouseEvent e) {
		// TODO add your code here
	}

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		scrollPane1 = new JScrollPane();
		treBlocks = new JTree();
		scrollPane2 = new JScrollPane();
		txtBitVector = new JTextPane();
		scrollPane3 = new JScrollPane();
		txtPred = new JTextPane();
		scrollPane4 = new JScrollPane();
		txtCode = new JTextPane();
		scrollPane5 = new JScrollPane();
		txtBitVectorHelp = new JTextPane();
		scrollPane6 = new JScrollPane();
		txtSucc = new JTextPane();

		//======== this ========
		setTitle("Reaching Definitions");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== scrollPane1 ========
		{
			setTreBlocks();
			//---- treBlocks ----
			treBlocks.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					treBlocksMouseClicked(e);
				}
			});
			treBlocks.addPropertyChangeListener(this::treBlocksPropertyChange);
			scrollPane1.setViewportView(treBlocks);
		}
		contentPane.add(scrollPane1);
		scrollPane1.setBounds(0, 0, 190, 515);

		//======== scrollPane2 ========
		{

			//---- txtBitVector ----
			txtBitVector.setDisabledTextColor(SystemColor.desktop);
			txtBitVector.setFont(new Font("Consolas", txtBitVector.getFont().getStyle(), txtBitVector.getFont().getSize()));
			txtBitVector.setEditable(false);
			txtBitVector.setEnabled(false);
			scrollPane2.setViewportView(txtBitVector);
		}
		contentPane.add(scrollPane2);
		scrollPane2.setBounds(189, 0, 636, 195);

		//======== scrollPane3 ========
		{

			//---- txtPred ----
			txtPred.setEnabled(false);
			txtPred.setEditable(false);
			txtPred.setDisabledTextColor(SystemColor.desktop);
			txtPred.setFont(new Font("Consolas", txtPred.getFont().getStyle(), txtPred.getFont().getSize()));
			scrollPane3.setViewportView(txtPred);
		}
		contentPane.add(scrollPane3);
		scrollPane3.setBounds(189, 194, 245, 161);

		//======== scrollPane4 ========
		{

			//---- txtCode ----
			txtCode.setEnabled(false);
			txtCode.setEditable(false);
			txtCode.setDisabledTextColor(SystemColor.desktop);
			txtCode.setFont(new Font("Consolas", txtCode.getFont().getStyle(), txtCode.getFont().getSize()));
			scrollPane4.setViewportView(txtCode);
		}
		contentPane.add(scrollPane4);
		scrollPane4.setBounds(433, 194, 392, 321);

		//======== scrollPane5 ========
		{

			//---- txtBitVectorHelp ----
			txtBitVectorHelp.setEnabled(false);
			txtBitVectorHelp.setEditable(false);
			txtBitVectorHelp.setDisabledTextColor(SystemColor.desktop);
			txtBitVectorHelp.setFont(new Font("Consolas", txtBitVectorHelp.getFont().getStyle(), txtBitVectorHelp.getFont().getSize()));
			scrollPane5.setViewportView(txtBitVectorHelp);
		}
		contentPane.add(scrollPane5);
		scrollPane5.setBounds(824, 0, 195, 515);

		//======== scrollPane6 ========
		{

			//---- txtSucc ----
			txtSucc.setEnabled(false);
			txtSucc.setEditable(false);
			txtSucc.setDisabledTextColor(SystemColor.desktop);
			txtSucc.setFont(new Font("Consolas", txtSucc.getFont().getStyle(), txtSucc.getFont().getSize()));
			scrollPane6.setViewportView(txtSucc);
		}
		contentPane.add(scrollPane6);
		scrollPane6.setBounds(189, 352, 245, 163);

		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for(int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		pack();
		setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane scrollPane1;
	private JTree treBlocks;
	private JScrollPane scrollPane2;
	private JTextPane txtBitVector;
	private JScrollPane scrollPane3;
	private JTextPane txtPred;
	private JScrollPane scrollPane4;
	private JTextPane txtCode;
	private JScrollPane scrollPane5;
	private JTextPane txtBitVectorHelp;
	private JScrollPane scrollPane6;
	private JTextPane txtSucc;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    private void setTreBlocks() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Blocks");
        List<DefaultMutableTreeNode> blockTree = new ArrayList<>();

        for (Block block : blocks) {
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(block.getName());
            root.add(treeNode);
        }
        treBlocks = new JTree(root);
    }

    private void clickTreeBlocks(String stringList) {
        List<String> bNameList = Arrays.asList(stringList.split(","));
		String bName = bNameList.get(1).replace("]", "").replace(" ", "");
        Block block = null;
        for (Block blk : blocks) {
            if (blk.getName().equals(bName)) {
                block = blk;
            }
        }
        if (block != null) {
            txtPred.setText(block.getName());
            txtSucc.setText(block.getName());
            txtCode.setText("Block"+block.toFormString());
			String stringBuilder = "GEN:\n" + block.getGEN().getVector() +
					"\nIN:\n" + block.getIN().getVector() +
					"\nKILL:\n" + block.getKILL().getVector() +
					"\nOUT:\n" + block.getOUT().getVector();
			txtBitVector.setText(stringBuilder);
            txtBitVectorHelp.setText(block.getGEN().getVectorHelp()+"\n                                               .");
			txtPred.setText(getSuccessors(graph.outgoingEdgesOf(block)));
			txtSucc.setText(getPredecessors(graph.incomingEdgesOf(block)));
        }
    }

	private String getSuccessors(Set<Edges> successors){
		Set<Block> temp =new HashSet<Block>();
		StringBuilder result = new StringBuilder();
		for (Edges edge:successors) {
			temp.add(edge.getTarget());
		}
		result.append("Successors:");
		result.append("\n──────────────\n");
		for (Block block:temp) {
			result.append("IN[").append(block.getName()).append("]").append(":").append(block.getInVector()).append("\n");
		}
		return result.toString();
	}

	private String getPredecessors(Set<Edges> successors){
		Set<Block> temp =new HashSet<Block>();
		StringBuilder result = new StringBuilder();
		for (Edges edge:successors) {
			temp.add(edge.getSource());
		}
		result.append("Predecessors:");
		result.append("\n──────────────\n");
		for (Block block:temp) {
			result.append("OUT[").append(block.getName()).append("]").append(":").append(block.getOutVector()).append("\n");
		}
		return result.toString();
	}

}
