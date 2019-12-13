package intermediate.shcema;

public class GoToScheme implements IScheme {

    private String label = null;

    public GoToScheme(String label){
        this.label = label;
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
        if (this.label != null){
            return "goto "+ this.label;
        }
        return null;
    }
}
