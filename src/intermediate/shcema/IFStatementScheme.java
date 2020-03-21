package intermediate.shcema;

import java.util.ArrayList;
import java.util.List;

public class IFStatementScheme implements IScheme {

    private String condition = null;
    private boolean isNegative = false;
    private String label = null;
    public IFStatementScheme(String condition,String label,boolean isNegative){
        this.condition = condition;
        this.label = label;
        this.isNegative = isNegative;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public boolean hasLabel() {
        return true;
    }

    @Override
    public String toString(){
        if (this.condition != null && this.label != null){
            if (this.isNegative)
                return "if NOT " + this.condition + " goto "+ this.label;
            return "if " + this.condition + " goto "+ this.label;
        }
        return null;
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }
}
