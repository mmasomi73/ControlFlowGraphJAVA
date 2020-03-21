package intermediate.shcema;

import java.util.List;

public interface IScheme {
    public String toString();
    public String getLabel();
    public boolean hasLabel();
    public List<String> getVariables();

}
