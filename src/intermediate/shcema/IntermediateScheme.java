package intermediate.shcema;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class IntermediateScheme {

    private static final AtomicInteger count = new AtomicInteger(0);
    private int ID;

    public String type = null;
    public String label = null;
    public boolean hasLabel = false;
    public IScheme iScheme = null;

    public IntermediateScheme(String type, IScheme iScheme, boolean hasLabel, String label) {
        ID = count.incrementAndGet();

        this.type = type;
        this.label = label;
        this.hasLabel = hasLabel;
        this.iScheme = iScheme;
    }

    @Override
    public String toString() {
        if (this.iScheme == null && this.hasLabel){
            return "\n"+this.label + ":\t";
        }
        if (this.hasLabel){
            return "\n"+this.label + ":\t" + this.iScheme.toString();
        }
        return "\n\t\t" + this.iScheme.toString();
    }

    public String toBlockString() {
        if (this.iScheme == null && this.hasLabel){
            return "|"+beautiLabel() + (this.label.length() < 3 ? " |   " : " |    " );
        }
        if (this.hasLabel){
            return "|"+beautiLabel() + " | " + this.iScheme.toString();
        }
        return "|    | " + this.iScheme.toString();
    }

    public String toDiagramString() {
        if (this.iScheme == null && this.hasLabel){
            return beautiLabel();
        }
        if (this.hasLabel){
            return beautiLabel() + ":"+this.iScheme.toString();
        }
        return  this.iScheme.toString();
    }
    public String toFormString() {
        if (this.iScheme == null && this.hasLabel){
            return beautiLabel()+":";
        }
        if (this.hasLabel){
            return beautiLabel() + ":"+this.iScheme.toString()+"\n";
        }
        return  "\t"+this.iScheme.toString()+"\n";
    }

    private String beautiLabel(){
        if (this.label.length() < 3) return this.label+ " ";
        return this.label;
    }

    public int getID() {
        return ID;
    }

    public List<String> getVariables() {
        if (iScheme == null) return new ArrayList<String>();
        return iScheme.getVariables();
    }
}
