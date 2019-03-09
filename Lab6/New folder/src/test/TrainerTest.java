package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;

import battle.Trainer;
import monster.ElectricMonster;
import monster.FireMonster;
import monster.Monster;
import monster.WaterMonster;

public class TrainerTest {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	private Trainer trainer;
	private List<Monster> sortedMonsters;
	private List<Monster> otherSortedMonsters;

	@BeforeEach
	public void setUp() {
		trainer = new Trainer("Ash");
		
		Monster m1 = new FireMonster (150, 150);
		Monster m2 = new WaterMonster (150, 150);
		Monster m3 = new ElectricMonster (150, 200);
		Monster m4 = new ElectricMonster (150, 100);
		Monster m5 = new ElectricMonster (150, 100);
		
		trainer.addMonster(m1);
		trainer.addMonster(m2);
		trainer.addMonster(m3);
		trainer.addMonster(m4);
		trainer.addMonster(m5);
		
		sortedMonsters = new ArrayList<>();
		sortedMonsters.addAll(Arrays.asList(m3, m1, m2, m4, m5));
		
		otherSortedMonsters = new ArrayList<>();
		otherSortedMonsters.addAll(Arrays.asList(m3, m1, m2, m5, m4));
	}

	@AfterEach
	public void tearDown() {
		trainer = null;
	}

	// Get a file name for loading/saving wishlists, and makes sure the file doesn't exist before returning
	private Path getOutputFile() throws IOException {
		tempFolder.create();
		Path path = tempFolder.newFile().toPath();
		Files.deleteIfExists(path);
		return path;
	}

	@Test
	public void testSaveAndLoadShouldWorkTogether() throws Exception {
		Path path = getOutputFile();
		trainer.saveToFile(path.toString());
		Trainer trainer2 = Trainer.loadFromFile(path.toString());
		// TODO compare each monster separately -- can't use Set.equals()
		/*
		Assert.assertEquals("Wishlist read from file is not equal to original list", wishlist.getSets(),
				wishlist2.getSets());
				*/
	}

	@Test
	public void testSaveShouldProduceNonEmptyFile() throws Exception {
		Path path = getOutputFile();
		trainer.saveToFile(path.toString());
		Assert.assertTrue("Trainer output file does not exist after save()", Files.exists(path));
		Assert.assertTrue("Trainer output file is empty after save()", Files.size(path) > 0);
	}

	@Test
	public void testMonsterSort() {
		List<Monster> testMonsters = new ArrayList(trainer.getMonsters());
		Collections.sort(testMonsters);
		Assert.assertTrue("Comparator does not correctly sort Monsters: " + testMonsters,
				testMonsters.equals(sortedMonsters) ||
				testMonsters.equals(otherSortedMonsters));
	}
}
