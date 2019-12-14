package intermediate.crawler;

import intermediate.shcema.IntermediateScheme;

import java.util.ArrayList;
import java.util.List;

public class LeadersExtractor {
    private List<IntermediateScheme> intermedList = new ArrayList<IntermediateScheme>();

    public LeadersExtractor(List<IntermediateScheme> intermedList) {
        this.intermedList = intermedList;
    }

    private boolean isLeader(IntermediateScheme intermediateScheme, int index) {

        if (index == 0) return true;

        if (intermediateScheme.hasLabel) return true;

        if (intermediateScheme.iScheme != null){
            return this.isJump(this.intermedList.get(index - 1));
        }

        return false;
    }

    private boolean isJump(IntermediateScheme intermediateScheme) {

        return intermediateScheme.iScheme != null && intermediateScheme.iScheme.hasLabel();
    }

    public List<Integer> getLeadersIndex() {
        List<Integer> leaders = new ArrayList<Integer>();
        for (int i = 0; i < this.intermedList.size(); i++) {
            if (this.isLeader(this.intermedList.get(i), i)) {
                leaders.add(i);
            }
        }
        return leaders;
    }

    public List<String> getLeadersStatement() {
        List<String> leaders = new ArrayList<String>();
        for (int i = 0; i < this.intermedList.size(); i++) {
            if (this.isLeader(this.intermedList.get(i), i)) {
                leaders.add(this.intermedList.get(i).toString());
            }
        }
        return leaders;
    }
}
