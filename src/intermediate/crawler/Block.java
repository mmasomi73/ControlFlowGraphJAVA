package intermediate.crawler;

import intermediate.optimizer.ReachingDefs.BitVector;
import intermediate.optimizer.ReachingDefs.BitVectors;
import intermediate.shcema.IntermediateScheme;

import java.util.*;

public class Block {

    /*
     * Add GEN, KILL, IN, OUT
     */
    private BitVectors GEN;
    private BitVectors KILL;
    private BitVectors IN;
    private BitVectors OUT;


    private List<IntermediateScheme> intermedList = new ArrayList<IntermediateScheme>();
    private String name;

    public Block(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<IntermediateScheme> getIntermedList() {
        return this.intermedList;
    }

    public void Add(IntermediateScheme intermediateScheme) {
        this.intermedList.add(intermediateScheme);
    }

    @Override
    public String toString() {
        int len = 0;
        StringBuilder output = new StringBuilder();
        StringBuilder border = new StringBuilder();
        for (int i = 0; i < this.intermedList.size(); i++) {
            len = Math.max(len, this.intermedList.get(i).toString().length());
        }

        border.append("+");
        for (int i = 0; i < len + 6; i++) {
            border.append("-");
        }
        border.append("+\n");

        output.append(border.toString());
        output.append("| ").append(name);
        output.append(" ".repeat(Math.max(0, len + 5 - name.length())));
        output.append("|\n");
        output.append(border.toString());

        for (int i = 0; i < this.intermedList.size(); i++) {
            output.append(this.intermedList.get(i).toBlockString());
            for (int j = 0; j < len + 3 - this.intermedList.get(i).toString().length(); j++) {
                output.append(" ");
            }
            output.append("|\n");
        }
        output.append(border.toString());

//        return output.toString();
        return toDiagramString();
    }

    public IntermediateScheme getLastStatement() {
        return intermedList.get(intermedList.size() - 1);
    }

    public String getLabel() {
        String label = null;

        for (IntermediateScheme intermediateScheme : intermedList) {
            if (intermediateScheme.hasLabel) {
                label = intermediateScheme.label;
            }
        }
        return label;
    }

    public String toDiagramString() {
        StringBuilder output = new StringBuilder();

        output.append(name);
        output.append("\n");

        for (int i = 0; i < this.intermedList.size(); i++) {
            output.append(this.intermedList.get(i).toDiagramString());
            output.append("\n");
        }

        return output.toString();
    }

    public String toFormString() {
        StringBuilder output = new StringBuilder();

        output.append("Block:[").append(name).append("]");
        output.append("\n──────────────\n");

        for (int i = 0; i < this.intermedList.size(); i++) {
            output.append(this.intermedList.get(i).toFormString());
        }

        return output.toString();
    }

    //----------------------------------------------------------------------=

    public void init(List<Block> blocks) {
        setGEN(blocks);
        setKILL(blocks);
        setIN(blocks);
        setOUT(blocks);
    }

    public void setGEN(List<Block> blocks) {
        this.GEN = new BitVectors(blocks);
    }

    public void setKILL(List<Block> blocks) {
        this.KILL = new BitVectors(blocks);
    }

    public void setIN(List<Block> blocks) {
        this.IN = new BitVectors(blocks);
    }

    public void setOUT(List<Block> blocks) {
        this.OUT = new BitVectors(blocks);
    }

    public BitVectors getGEN() {
        computeGEN();
        return GEN;
    }

    public BitVectors getKILL() {
        computeKILL();
        return KILL;
    }

    public BitVectors getIN() {
        return IN;
    }

    public BitVectors getOUT() {
        return OUT;
    }

    private void computeGEN() {
        for (IntermediateScheme statement : intermedList) {
            if (statement.type.equals("a") || statement.type.equals("e")) {
                GEN.change(statement, true);
            }
        }
    }

    private void computeKILL() {
        int i = 0;
        for (IntermediateScheme statement_1 : intermedList) {
            int j = 0;
            for (Map.Entry<Integer, BitVector> entry : KILL.sort().entrySet()) {
                IntermediateScheme statement_2 = entry.getValue().getStatement();
                if ((statement_1.getID() != statement_2.getID())
                        && (statement_1.type.equals("a") || statement_1.type.equals("e"))
                        && (statement_2.type.equals("a") || statement_2.type.equals("e"))) {
                    List<String> vars_1 = statement_1.getVariables();
                    List<String> vars_2 = statement_2.getVariables();
                    if (vars_1.size() > 1 && vars_2.size() > 1) {
                        if (vars_1.get(0).equals(vars_2.get(0))) {
                            KILL.change(statement_2, true);
                        }
                    }
                }
            }
        }
    }

    public void setINEmptySet(){

        for (Map.Entry<Integer, BitVector> entry : IN.sort().entrySet()) {
            IntermediateScheme statement = entry.getValue().getStatement();
            KILL.change(statement, false);
        }
    }

    public void setOUTEmptySet(){

        for (Map.Entry<Integer, BitVector> entry : OUT.sort().entrySet()) {
            IntermediateScheme statement = entry.getValue().getStatement();
            KILL.change(statement, false);
        }
    }

    public Map<Integer, Boolean> getOUTList(){
        Map<Integer, Boolean> out = new HashMap<>();

        for (Map.Entry<Integer, BitVector> bitVector:OUT.sort().entrySet()) {
            out.put(bitVector.getKey(),bitVector.getValue().getValue());
        }
        return out;
    }

    public void showVlas(){
        System.out.println("---------------------="+name);
        System.out.println("-----=OUT");
        OUT.showVector();

        System.out.println("-----=IN");
        IN.showVector();

        System.out.println("-----=GEN");
        GEN.showVector();

        System.out.println("-----=KILL");
        KILL.showVector();
    }

    public String getOutVector(){
        return OUT.getBits();
    }

    public String getInVector(){
        return IN.getBits();
    }
}
