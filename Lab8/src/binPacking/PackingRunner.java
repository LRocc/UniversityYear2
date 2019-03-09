package binPacking;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PackingRunner implements Runnable {


    private int binCapacity;
    private List<Integer> weights;
    private PackingStrategy strategy;
    private ThreadedStrategy parent;
    public PackingRunner(PackingStrategy usedAlghoritm, ThreadedStrategy parentClass, int binCapacity, List<Integer> weights)
    {
        this.binCapacity = binCapacity;
        this.weights = weights;
        this.strategy = usedAlghoritm;
        this.parent = parentClass;
    }

    @Override
    public void run()
    {
        parent.addResults(this.strategy.pack(binCapacity,weights));

    }
}
