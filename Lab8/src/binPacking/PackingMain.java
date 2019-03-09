package binPacking;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * A main method to test the bin-packing code developed in JP2 lab 8.
 * 
 * @author Mary Ellen Foster
 */
public class PackingMain {

	/** Constants controlling the bin-packing problem instance */
	/** How many items to include (the "_" does not affect the value but makes it more readable) */
	private static final int NUM_VALUES = 20_000;

	/** The maximum value */
	private static final int MAX_VALUE = 100;
	
	/** The capacity of the bins */
	private static final int CAPACITY = 1000;
	
	/** How many threads to use in the parallel version */
	private static final int NUM_THREADS = 10;
	
	/** A random-number generator, used for creating problem instances */
	private static final Random RAND = new Random();
/**
	public static void main(String[] args) {
		// Generate the weights randomly 
		// Ensure ArrayList has sufficient capacity to avoid reallocation
		List<Integer> values = new ArrayList<>(NUM_VALUES);
		for (int i = 0; i < NUM_VALUES; i++) {
			values.add(RAND.nextInt(MAX_VALUE) + 1);
		}

		List<PackingStrategy> strategies = new ArrayList<>();
		strategies.add(new NextFitStrategy());
		strategies.add(new FirstFitStrategy());
		strategies.add(new BestFitStrategy());
		strategies.add(new ThreadedStrategy(new BestFitStrategy(), NUM_THREADS));
		
		List<String> csvList = new ArrayList<>();
		csvList.add("strategy,duration,bins,unused");
		
		for (PackingStrategy s : strategies) {
			Instant start = Instant.now();
			System.out.println(s.getClass() + " started at " + start);
			Set<Bin> result = s.pack(CAPACITY, new ArrayList<>(values));
			Instant end = Instant.now();
			System.out.println(s.getClass() + " ended at " + end);
			Duration duration = Duration.between(start, end);
			System.out.println("Duration " + duration.toMillis() + "ms");
			
			// Assess the quality by checking the remaining space
			System.out.println("Number of bins: " + result.size());
			int value = result.stream().mapToInt(b -> b.getSpace()).sum();
			System.out.println("Unused space: " + value);
			
			csvList.add(s.getClass().getName() + "," + duration.toMillis() + "," + result.size() + "," + value);

			// Blank line to break things up ...
			System.out.println();
		}
		
		System.out.println(String.join("\n", csvList));
	
	}
 */
}
