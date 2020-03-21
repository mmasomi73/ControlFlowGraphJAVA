package intermediate.optimizer.ReachingDefs;

import intermediate.shcema.IntermediateScheme;

import java.util.concurrent.atomic.AtomicInteger;

public class BitVector {

    private static final AtomicInteger count = new AtomicInteger(0);

    private int ID;
    private String block;
    private boolean value;
    private String variable;
    private IntermediateScheme statement;

    public BitVector(IntermediateScheme statement, String block, String variable) {
        ID = count.incrementAndGet();
        this.statement = statement;
        this.variable = variable;
        this.block = block;
        value = false;
    }

    public BitVector(IntermediateScheme statement, String block, String variable, boolean value) {
        ID = count.incrementAndGet();
        this.statement = statement;
        this.variable = variable;
        this.block = block;
        this.value = value;
    }

    private boolean isDef() {
        return statement.type.equals("a") || statement.type.equals("e");
    }
    //----------------------------------------------------------------------= Getter

    public int getID() {
        return ID;
    }

    public boolean getValue() {
        return value;
    }
    public int getValue(int i) {
        return value ? 1 : 0;
    }

    public String getBlock() {
        return block;
    }

    public String getVariable() {
        return variable;
    }

    public IntermediateScheme getStatement() {
        return statement;
    }

    //----------------------------------------------------------------------= Setter

    public void setBlock(String block) {
        this.block = block;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public void setStatement(IntermediateScheme statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "BitVector{" +
                "ID=" + ID +
                ", block='" + block + '\'' +
                ", value=" + value +
                ", variable='" + variable + '\'' +
                ", statement=" + statement +
                '}';
    }
}
