package binPacking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NextFitStrategy implements PackingStrategy {

    @Override
    public Set<Bin> pack(int capacity, List<Integer> values)
    {
        List<Bin> set = new ArrayList<Bin>();
        if(!values.isEmpty())
        {
            set.add(new Bin(capacity));
        }
        int val = capacity;
        int index = 0;

        for(int a : values)
        {
            if(a <= set.get(index).getSpace())
            {
                set.get(index).store(a);
            }else{
                set.add(new Bin(capacity));
                index ++;
                set.get(index).store(a);
            }
        }
        return new HashSet<Bin>(set);
    }

}
