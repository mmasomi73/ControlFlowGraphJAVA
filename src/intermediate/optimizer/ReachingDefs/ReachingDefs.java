package intermediate.optimizer.ReachingDefs;

import intermediate.crawler.Block;
import intermediate.crawler.ControlFlowGraph;
import intermediate.crawler.Edges;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;

import javax.swing.*;
import java.util.*;

public class ReachingDefs {

    private ControlFlowGraph cfg;
    private List<Block> changed;
    private BitVectors bitVector;
    private Graph<Block, Edges> graph;

    public ReachingDefs(ControlFlowGraph graph){
        cfg = graph;
        Set<Block> temp =new HashSet<Block>( cfg.getBlockList());
        changed = new ArrayList<>(temp);
        this.graph = cfg.getCFG();
        bitVector = new BitVectors(cfg.getBlockList());
        for (Block block: changed) {
            block.init(cfg.getBlockList());
        }

    }

    public List<Block> run() {
        List<Block> blocks = new ArrayList<>();
        Map<Integer, Boolean> out = new HashMap<>();

        for (Block block:changed) {
            setEmptySet(block,true);
        }
        setEmptySet(changed.get(0),false);

        while (changed.size() > 0){
            Block block = chooseNode();
            changed.remove(block);
            setEmptySet(block,false);

            block = getUnionPredecessors(block,graph.incomingEdgesOf(block));

            out = block.getOUTList();

            block = transferFunction(block);

            if (isChangedOUT(block,out)){
                addSuccessors(graph.outgoingEdgesOf(block));
            }
            blocks.add(block);
        }


        ReachingDefsForm dialog = new ReachingDefsForm(blocks,graph);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setEnabled(true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        return blocks;
    }

    private Block chooseNode(){
        //TODO: improve with DF-order
        Block block = changed.get(0);

        return block;
    }


    private void setEmptySet(Block block,boolean isOut){
        if (isOut){
            block.setOUTEmptySet();
        }else{
            block.setINEmptySet();
        }
    }

    public Block getUnionPredecessors(Block block, Set<Edges> predecessors){

        for (Edges blk:predecessors) {
            BitVectors inBlock = block.getIN();
            BitVectors outBlk = blk.getSource().getOUT();
            for (Map.Entry<Integer, BitVector> bitVector:outBlk.sort().entrySet()) {
                inBlock.union(bitVector.getValue().getStatement(),bitVector.getValue().getValue());
            }
        }
        return block;
    }

    public Block transferFunction(Block block){
        BitVectors IN = block.getIN();
        BitVectors OUT = block.getOUT();
        BitVectors GEN = block.getGEN();
        BitVectors KILL = block.getKILL();
        for (Map.Entry<Integer, BitVector> bitVector:IN.sort().entrySet()) {
            boolean result = GEN.sort().get(bitVector.getKey()).getValue() ||
                    mines(IN.sort().get(bitVector.getKey()).getValue(),KILL.sort().get(bitVector.getKey()).getValue());
            OUT.sort().get(bitVector.getKey()).setValue(result);
        }
        return block;
    }

    public boolean isChangedOUT(Block block,Map<Integer, Boolean> last){
        Map<Integer, Boolean> now = block.getOUTList();

        boolean changed = false;

        for (Map.Entry<Integer, Boolean> entry: last.entrySet()) {
            if (entry.getValue() != now.get(entry.getKey())){
                changed = true;
            }
        }


        return changed;
    }

    public void addSuccessors(Set<Edges> successors){
        Set<Block> temp =new HashSet<Block>(changed);
        for (Edges edge:successors) {
            temp.add(edge.getTarget());
        }
        changed = new ArrayList<>(temp);
    }

    public boolean mines(boolean val1, boolean val2){
        if (val1 && !val2) return true;
        return false;
    }
}
