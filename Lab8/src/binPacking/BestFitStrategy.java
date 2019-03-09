package binPacking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BestFitStrategy implements PackingStrategy {
    @Override
    public Set<Bin> pack(int capacity, List<Integer> values) {
        List<Bin> set = new ArrayList<Bin>();
        if(!values.isEmpty())
        {
            set.add(new Bin(capacity));
        }
        for(int a : values)
        {
            int index = 0;
            int val = capacity;
            boolean sp = false;
            for(int b = 0; b < set.size();b++)
            {
                if(a < set.get(b).getSpace())
                {
                    sp = true;
                    if((set.get(b).getSpace() - 1) < val)
                    {
                        val = set.get(b).getSpace() - 1;
                        index = b;
                    }
                }
            }
            if(sp)
            {
                set.get(index).store(a);
            }
            else
                {
                    set.add(new Bin(capacity));
                    set.get(set.size()).store(a);
                }
        }
        return new HashSet<Bin>(set);
    }
}
