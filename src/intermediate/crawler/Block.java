package intermediate.crawler;

import intermediate.shcema.IntermediateScheme;

import java.util.ArrayList;
import java.util.List;

public class Block {

    private List<IntermediateScheme> intermedList = new ArrayList<IntermediateScheme>();
    private String name;

    public Block(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public List<IntermediateScheme> getIntermedList(){
        return this.intermedList;
    }

    public void Add(IntermediateScheme intermediateScheme){
        this.intermedList.add(intermediateScheme);
    }

    @Override
    public String toString() {
        int len = 0;
        StringBuilder output = new StringBuilder();
        StringBuilder border = new StringBuilder();
        for (int i = 0; i <this.intermedList.size() ; i++) {
            len = Math.max(len,this.intermedList.get(i).toString().length());
        }

        border.append("+");
        for (int i = 0; i < len + 6 ; i++) {
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
            for (int j = 0; j <len + 3 - this.intermedList.get(i).toString().length(); j++) {
                output.append(" ");
            }
            output.append("|\n");
        }
        output.append(border.toString());

        return output.toString();
    }

    public IntermediateScheme getLastStatement(){
        return intermedList.get(intermedList.size() - 1);
    }

    public String getLabel(){
        String label = null;

        for (IntermediateScheme intermediateScheme:intermedList) {
            if (intermediateScheme.hasLabel){
                label = intermediateScheme.label;
            }
        }
        return label;
    }
}
