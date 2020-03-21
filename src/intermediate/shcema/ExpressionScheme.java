package intermediate.shcema;

import java.util.ArrayList;
import java.util.List;

public class ExpressionScheme implements IScheme {

    private String leftValue = null;
    private String operand_1 = null;
    private String operand_2 = null;
    private String operator = null;

    public ExpressionScheme(String leftValue, String operand_1, String operator, String operand_2){

        this.leftValue = leftValue;
        this.operand_1 = operand_1;
        this.operand_2 = operand_2;
        this.operator = operator;
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
        if ( this.leftValue != null &&  this.operand_1 != null &&
             this.operator != null &&  this.operand_2 != null){

            return this.leftValue + " = " + this.operand_1 + " "+ this.operator + " "+ this.operand_2;
        }
        return null;
    }

    @Override
    public List<String> getVariables() {
        List<String> list = new ArrayList<>();
        if ( this.leftValue != null &&  this.operand_1 != null &&
                this.operator != null &&  this.operand_2 != null){
            list.add(this.leftValue);
            list.add(this.operand_1);
            list.add(this.operand_2);
        }
        return list;
    }
}
