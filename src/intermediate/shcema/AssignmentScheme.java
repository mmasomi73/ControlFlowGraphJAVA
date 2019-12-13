package intermediate.shcema;

public class AssignmentScheme implements IScheme {

    private String leftValue = null;
    private String operand_1 = null;

    public AssignmentScheme(String leftValue, String operand_1){

        this.leftValue = leftValue;
        this.operand_1 = operand_1;
    }

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public boolean hasLabel() {
        return false;
    }

    @Override
    public String toString(){
        if ( this.leftValue != null &&  this.operand_1 != null){

            return this.leftValue + " = " + this.operand_1;
        }
        return null;
    }
}
