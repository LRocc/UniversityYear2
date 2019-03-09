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

    private Monster m1;

    private Monster m2;

    private Monster m3;

    private Monster m4;

    private Monster m5;

    @BeforeEach
    public void setUp() {
        trainer = new Trainer("Ash");

        m1 = new FireMonster (150, 150);
        m2 = new WaterMonster (150, 150);
        m3 = new ElectricMonster (200, 100);
        m4 = new ElectricMonster (150, 100);
        m5 = new ElectricMonster (150, 100);

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
        // Compare each monster separately -- can't use Set.equals
        for (Monster m : trainer2.getMonsters()) {
            boolean found = false;
            for (Monster m2: sortedMonsters) {
                if (m.getType().equals(m2.getType())
                        && m.getAttackPoints() == m2.getAttackPoints()
                        && m.getHitPoints() == m2.getHitPoints()) {
                    // Possible candidate -- check weaknesses
                    boolean sameWeaknesses = true;
                    for (String type : new String[] { "Fire", "Water", "Electric" } ) {
                        sameWeaknesses &= m.isWeakAgainst(type) == m2.isWeakAgainst(type);
                    }
                    if (sameWeaknesses) {
                        sortedMonsters.remove(m2);
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                Assert.fail("Monsters are not identical after reload: " + trainer.getMonsters() + " vs " + trainer2.getMonsters());
            }
        }
        if (!sortedMonsters.isEmpty()) {
            Assert.fail("Monsters are not identical after reload: " + trainer.getMonsters() + " vs " + trainer2.getMonsters());
        }
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

    @Test
    public void testCompareIdenticalMonsters() {
        Assert.assertEquals("Comparing two identical monsters should return zero", 0, m4.compareTo(m5));
    }

    @Test
    public void testCompareIdenticalExceptForType() {
        int comp = m1.compareTo(m2);
        Assert.assertTrue("Comparison incorrect for identical monsters with different types", comp < 0);
    }

    @Test
    public void testCompareSameHPDIfferentAP() {
        int comp = m1.compareTo(m3);
        Assert.assertTrue("Comparison incorrect for monsters with same HP and different AP", comp > 0);
    }

    @Test
    public void testCompareDifferentHP() {
        int comp = m3.compareTo(m5);
        Assert.assertTrue("Comparison incorrect for monsters with different HP", comp < 0);
    }
}
