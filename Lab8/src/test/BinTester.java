package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import binPacking.BestFitStrategy;
import binPacking.Bin;
import binPacking.FirstFitStrategy;
import binPacking.NextFitStrategy;
import binPacking.ThreadedStrategy;

public class BinTester {

	private List<Integer> ints;

	@Before
	public void setUp() throws Exception {
		ints = Arrays.asList(75, 50, 20, 60, 40, 50);
	}

	@After
	public void tearDown() throws Exception {
		ints = null;
	}

	@Test
	public void testGetSpaceReturnsCapacityWhenEmpty() {
		Bin b1 = new Bin(100);
		Assert.assertEquals("Bin.getSpace() should return capacity when empty", 100, b1.getSpace());
	}

	@Test
	public void testGetSpaceReturnsCorrectValueWhenNonEmpty() {
		Bin b1 = new Bin(100);
		b1.store(30);
		Assert.assertEquals("Bin.getSpace() should return correct remaining space after store()", 70, b1.getSpace());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStoreShouldThrowExceptionIfCapacityExceeded() {
		Bin b1 = new Bin(100);
		b1.store(60);
		b1.store(60);
	}

	@Test
	public void testFirstFitStrategyShouldNotChangeArgument() {
		List<Integer> intCopy = new LinkedList<>(ints);
		new FirstFitStrategy().pack(100, ints);
		Assert.assertEquals("FirstFitStrategy should not change input list", ints, intCopy);
	}

	@Test
	public void testNextFitStrategyShouldNotChangeArgument() {
		List<Integer> intCopy = new LinkedList<>(ints);
		new NextFitStrategy().pack(100, ints);
		Assert.assertEquals("NextFitStrategy should not change input list", ints, intCopy);
	}

	@Test
	public void testThreadedStrategyShouldNotChangeArgument() {
		List<Integer> intCopy = new LinkedList<>(ints);
		new ThreadedStrategy(new BestFitStrategy(), 2).pack(100, ints);
		Assert.assertEquals("ThreadedStrategy should not change input list", ints, intCopy);
	}

	@Test
	public void testBestFitStrategyShouldNotChangeArgument() {
		List<Integer> intCopy = new LinkedList<>(ints);
		new BestFitStrategy().pack(100, ints);
		Assert.assertEquals("BestFitStrategy should not change input list", ints, intCopy);
	}

	@Test
	public void testBestFitStrategyShouldReturnEmptySetOnEmptyInput() {
		Assert.assertEquals("BestFitStrategy should return an empty set on an empty input", new HashSet<>(),
				new BestFitStrategy().pack(100, new LinkedList<>()));
	}

	@Test
	public void testNextFitStrategyShouldReturnEmptySetOnEmptyInput() {
		Assert.assertEquals("NextFitStrategy should return an empty set on an empty input", new HashSet<>(),
				new NextFitStrategy().pack(100, new LinkedList<>()));
	}

	@Test
	public void testFirstFitStrategyShouldReturnEmptySetOnEmptyInput() {
		Assert.assertEquals("FirstFitStrategy should return an empty set on an empty input", new HashSet<>(),
				new FirstFitStrategy().pack(100, new LinkedList<>()));
	}

	@Test
	public void testThreadedStrategyShouldReturnEmptySetOnEmptyInput() {
		Assert.assertEquals("ThreadedStrategy should return an empty set on an empty input", new HashSet<>(),
				new ThreadedStrategy(new BestFitStrategy(), 2).pack(100, new LinkedList<>()));
	}

	@Test
	public void testBestFitStrategyShouldReturnValidResult() {
		Set<Bin> bins = new BestFitStrategy().pack(100, ints);
		int totalSpace = 0;
		for (Bin b : bins) {
			int space = b.getSpace();
			totalSpace += space;
			Assert.assertTrue("Bins should have non-negative space left", space >= 0);
		}
		Assert.assertEquals("Total remaining space should be 5 on test set", 5, totalSpace);
	}

	@Test
	public void testFirstFitStrategyShouldReturnValidResult() {
		Set<Bin> bins = new FirstFitStrategy().pack(100, ints);
		int totalSpace = 0;
		for (Bin b : bins) {
			int space = b.getSpace();
			totalSpace += space;
			Assert.assertTrue("Bins should have non-negative space left", space >= 0);
		}
		Assert.assertTrue("Total remaining space should be either 5 or 105 on test set",
				totalSpace == 105 || totalSpace == 5);
	}

	@Test
	public void testNextFitStrategyShouldReturnValidResult() {
		Set<Bin> bins = new NextFitStrategy().pack(100, ints);
		int totalSpace = 0;
		for (Bin b : bins) {
			int space = b.getSpace();
			totalSpace += space;
			Assert.assertTrue("Bins should have non-negative space left", space >= 0);
		}
		Assert.assertEquals("Total remaining space should be 105 on test set", 105, totalSpace);
	}

	@Test
	public void testThreadedStrategyShouldReturnValidResult() {
		// Make a list with 10 copies of each value
		List<Integer> allInts = new ArrayList<>();
		for (int i : ints) {
			allInts.addAll(Collections.nCopies(10, i));
		}
		Collections.shuffle(allInts);

		Set<Bin> bins = new ThreadedStrategy(new BestFitStrategy(), 5).pack(100, allInts);
		// Just make sure each bin is valid -- can't check for optimality
		for (Bin bin : bins) {
			Assert.assertTrue("Each bin should have non-negative space left", bin.getSpace() >= 0);
		}
	}

}
