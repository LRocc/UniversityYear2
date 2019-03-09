package binPacking;

import java.util.List;
import java.util.Set;

public interface PackingStrategy {
    public Set<Bin> pack(int capacity, List<Integer> values);
}
