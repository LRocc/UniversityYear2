package binPacking;
import com.sun.source.tree.ContinueTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FirstFitStrategy implements PackingStrategy {


    @Override
    public Set<Bin> pack(int capacity, List<Integer> values) {
        List<Bin> set = new ArrayList<Bin>();
        if (!values.isEmpty()) {
            set.add(new Bin(capacity));
        }
        int index = 0;
        int val = capacity;
        //iterate over values
        for (int a : values) {
            /** check if the value of a,(The weight that has to be added to the bin),goes over the bin capacity */
            if (a + set.get(index).getSpace() > val) {
                set.add(new Bin(capacity));
                index++;
                set.get(index).store(a);
            } else {
                set.get(index).store(a);
            }
        }

        return new HashSet<Bin>(set);
    }

}

