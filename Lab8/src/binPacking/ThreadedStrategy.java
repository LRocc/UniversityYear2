package binPacking;

import java.util.*;

public class ThreadedStrategy implements PackingStrategy {

    private int threadsToUse;
    private PackingStrategy strategy;
    private Set<Bin> bins;
    private Set<Bin> output = new HashSet<>();

    public ThreadedStrategy(PackingStrategy obj, int threads)
    {
        this.threadsToUse = threads;
        this.bins = Collections.emptySet();
        this.strategy = obj;
    }



    public synchronized void addResults(Set<Bin> result)
    {
            output.addAll(result);
    }


    private List<List<Integer>> partitionlist(int segment,List<Integer> values)
    {
        List<List<Integer>> subList = new ArrayList<>();
        //Remaining elements in the list
        int rem = values.size() % segment;
        //how many will go in each list
        int eah = (values.size() - rem) / segment;

        for(int index = 0; index < values.size();)
        {
            if(rem > 0)
            {
                subList.add(values.subList(index,index+eah + 1));
                index += eah + 1;
                rem--;
            }else
            {
                subList.add(values.subList(index,index + eah));
                index += eah;
            }
        }
        return subList;
    }



    @Override
    public Set<Bin> pack(int capacity, List<Integer> values)

    {
        System.out.println(threadsToUse + ": " + values.toString() + " " + strategy.getClass());
        /** create a list to store threads and execute them */
        List<List<Integer>> subList = this.partitionlist(threadsToUse,values);
        List<Thread> threadPool = new ArrayList<>(this.threadsToUse);
        for(List<Integer> partition : subList)
        {
            Thread runner = new Thread(new PackingRunner(strategy,this,capacity,partition));
            threadPool.add(runner);
        }
        /** iterate the list and start the threads one after the other */

        for(Thread thread: threadPool)
        {
            thread.start();
        }

        for (Thread thread: threadPool)
        {
            try{
                thread.join();
            }catch(InterruptedException e )
            {
                e.printStackTrace();
            }
        }
        return output; // Output of the thread
    }
}
