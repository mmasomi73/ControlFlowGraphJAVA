package intermediate.crawler;

import intermediate.shcema.IntermediateScheme;

import java.util.ArrayList;
import java.util.List;

public class BlocksExtractor {

    private List<Block> blockList = new ArrayList<Block>();
    private List<IntermediateScheme> intermedList = new ArrayList<IntermediateScheme>();
    private List<Integer> leaders;
    private int currentLeader = 0;

    public BlocksExtractor(List<IntermediateScheme> intermedList, List<Integer> leaders){

        this.leaders = removeDuplicates(leaders);
        this.intermedList = intermedList;
    }

    private void detectBlock(){
        int blName = 0;
        boolean first = true;
        int lastLeader = this.getLeader() , nextLeader = this.getLeader();
        Block block = new Block("B"+ blName);
        for (int i = 0; i < this.intermedList.size() ; i++) {
            if (lastLeader == i && first){
                first = false;
                block = new Block("B"+ ++blName);
                blockList.add(block);
                block.Add(this.intermedList.get(i));
            }
            if (nextLeader > i &&  (lastLeader < i || !first)){
                first = false;
                block.Add(this.intermedList.get(i));
            }else{
                block = new Block("B"+ ++blName);
                blockList.add(block);
                block.Add(this.intermedList.get(i));
                first = false;
                lastLeader = nextLeader;
                nextLeader = this.getLeader();
                if (nextLeader == -1){
                    nextLeader = this.intermedList.size();
                }
            }
        }
    }

    private int getLeader(){
        if (currentLeader  < this.leaders.size())
            return this.leaders.get(currentLeader++);
        return -1;
    }

    public List<Block> getBlockList(){
        this.detectBlock();
        return this.blockList;
    }

    private static <T> ArrayList<T> removeDuplicates(List<T> list){

        ArrayList<T> newList = new ArrayList<T>();

        for (T element : list) {

            if (!newList.contains(element)) {

                newList.add(element);
            }
        }
        return newList;
    }

    public String toString(){
        this.detectBlock();
        StringBuilder output = new StringBuilder();
        for (Block block : this.blockList) {
            output.append(block.toString());
        }
        return output.toString();
    }

    public Block getWithLabel(String label){
        for (Block block: blockList) {
            if (block != null && block.getLabel() != null && label.equals(block.getLabel())){
                return block;
            }
        }
        return null;
    }
}
