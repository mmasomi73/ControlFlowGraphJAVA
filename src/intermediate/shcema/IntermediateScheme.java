package intermediate.shcema;

public class IntermediateScheme {

    public String type = null;
    public String label = null;
    public boolean hasLabel = false;
    public IScheme iScheme = null;

    public IntermediateScheme(String type, IScheme iScheme, boolean hasLabel, String label) {

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
}
