package intermediate.optimizer.ReachingDefs;

import intermediate.crawler.Block;
import intermediate.shcema.IntermediateScheme;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class BitVectors {

    private int counter;
    private Map<Integer, BitVector> bitVector = new HashMap<>();
    private List<Block> blocks;

    public BitVectors(List<Block> blocks) {

        this.blocks = blocks;
        counter = 0;
        createVector();
        sort();

    }

    public void change(int Key, boolean value) {

        if (bitVector.containsKey(Key)) {
            BitVector bitV = bitVector.get(Key);
            bitV.setValue(value);
        } else {
            System.err.println("╔═══════════════════════════════╗");
            System.err.println("║Error in Find Bit in Bit Vector║");
            System.err.println("║CLASS {Bit vector} : LINE {22} ║");
            System.err.println("╚═══════════════════════════════╝");
        }
    }

    public void change(IntermediateScheme statement, boolean value) {

        boolean find = false;
        for (Map.Entry<Integer, BitVector> entry:bitVector.entrySet()) {
            if (entry.getValue().getStatement().getID() == statement.getID()){
                entry.getValue().setValue(value);
                find = true;
            }
        }
        if (!find){
            System.err.println("╔═══════════════════════════════╗");
            System.err.println("║Error in Find Bit in Bit Vector║");
            System.err.println("║CLASS {Bit vector} : LINE {44} ║");
            System.err.println("╚═══════════════════════════════╝");
        }
    }

    private void createVector() {
        for (Block block : blocks) {
            for (IntermediateScheme statement : block.getIntermedList()) {
                if (statement.type.equals("a") || statement.type.equals("e")) {
                    List<String> vars = statement.iScheme.getVariables();
                    if (vars.size() <= 0) {
                        System.err.println("╔═══════════════════════════════╗");
                        System.err.println("║Error in Find Variables        ║");
                        System.err.println("║CLASS {Bit vector} : LINE {40} ║");
                        System.err.println("╚═══════════════════════════════╝");
                    } else {

                        BitVector bitV = new BitVector(statement, block.getName(), vars.get(0));
                        bitVector.put(++counter, bitV);
                    }
                }
            }
        }
    }

    public void union(IntermediateScheme statement, boolean value) {

        boolean find = false;
        for (Map.Entry<Integer, BitVector> entry:bitVector.entrySet()) {
            if (entry.getValue().getStatement().getID() == statement.getID()){
                entry.getValue().setValue(value || entry.getValue().getValue());
                find = true;
            }
        }
        if (!find){
            System.err.println("╔═══════════════════════════════╗");
            System.err.println("║Error in Find Bit in Bit Vector║");
            System.err.println("║CLASS {Bit vector} : LINE {88} ║");
            System.err.println("╚═══════════════════════════════╝");
        }
    }

    public TreeMap<Integer, BitVector> sort() {
        return new TreeMap<>(bitVector);
    }

    public void showVector() {
        TreeMap<Integer, BitVector> sorted = sort();

        System.out.print(" ┌");
        for (Map.Entry<Integer, BitVector> entry : sorted.entrySet()) {
            if (String.valueOf(entry.getKey()).length() > 1)
                System.out.print("────┬");
            else
                System.out.print("───┬");
        }
        System.out.println("┐");

        //----------------------------------------------------------
        for (Map.Entry<Integer, BitVector> entry : sorted.entrySet()) {
            System.out.print(" │ " + entry.getKey());
        }
        System.out.print(" ││");
        System.out.println("");
        for (Map.Entry<Integer, BitVector> entry : sorted.entrySet()) {
            if (String.valueOf(entry.getKey()).length() > 1)
                System.out.print(" │ " + entry.getValue().getValue(0) + " ");
            else
                System.out.print(" │ " + entry.getValue().getValue(0));
        }
        //----------------------------------------------------------
        System.out.println(" ││");
        System.out.print(" └");
        for (Map.Entry<Integer, BitVector> entry : sorted.entrySet()) {
            if (String.valueOf(entry.getKey()).length() > 1)
                System.out.print("────┴");
            else
                System.out.print("───┴");
        }
        System.out.println("┘");

        //----------------------------------------------------------
//        for (Map.Entry<Integer, BitVector> entry : sorted.entrySet()) {
//            if (String.valueOf(entry.getKey()).length() > 1){
//
//                System.out.print("│ " + entry.getKey() + "│ ");
//                System.out.println( entry.getValue().getStatement().toString().replace("\n", "").replace("\t", "") + " → "+entry.getValue().getBlock());
//            }
//            else{
//
//                System.out.print("│ " + entry.getKey() + " │ ");
//                System.out.println(entry.getValue().getStatement().toString().replace("\n", "").replace("\t", "") + " → "+entry.getValue().getBlock());
//            }
//        }
    }

    @Override
    public String toString() {
        return "BitVectors{" +
                "counter=" + counter +
                ", bitVector=" + bitVector +
                ", blocks=" + blocks +
                '}';
    }
}
