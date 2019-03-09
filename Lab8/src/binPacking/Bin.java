package binPacking;

import java.util.ArrayList;
import java.util.List;

public class Bin {
    private int capacyty;
    private List<Integer> weights = new ArrayList<Integer>();

    public Bin(int capacity)
    {
        this.capacyty = capacity;
    }
    /**
     * This method stores into @param weight list the parameters values
     * @throws IllegalArgumentException
     */
    public void store(int weight) throws IllegalArgumentException {
        //int temp = this.getSpace();
        /**
         * Check whether the weight inserted overflow the capacity of the object
         */
        if (this.capacyty - weight < 0)
        {
            throw new IllegalArgumentException("Can't add more weights");
        } else
            /** */
            {
                this.weights.add(weight);
                this.capacyty -= weight;
            }

    }

    /**
     * this method returns the remaining space in the bin
     * @return
     */
    public int getSpace()
    {
        int temp = this.capacyty;
        //System.out.println(temp);
        return temp;
    }
}
