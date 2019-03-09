package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import battle.Battle;
import battle.Trainer;
import monster.ElectricMonster;
import monster.FireMonster;
import monster.Monster;
import monster.WaterMonster;


class TestBattle {
	
	// Test fixture chosen by my son again
	private Trainer ash;
	private ElectricMonster pikachu;
	private WaterMonster greninja;
	
	private Trainer brock;
	private FireMonster vulpix;

	@BeforeEach
	void setUp() throws Exception {
		ash = new Trainer("Ash");
		pikachu = new ElectricMonster(75, 200);
		greninja = new WaterMonster(150, 150);
		ash.addMonster(pikachu);
		ash.addMonster(greninja);

		brock = new Trainer("Brock");
		vulpix = new FireMonster(100, 200);
		brock.addMonster(vulpix);
	}

	@AfterEach
	void tearDown() throws Exception {
		ash = null;
		brock = null;
		pikachu = null;
		greninja = null;
		vulpix = null;
	}

	@Test
	public void getNameReturnsName() {
		Assert.assertEquals("getName() should return name", "Ash", ash.getName());
	}
	
	@Test
	public void addMonsterAndGetMonsterWorkTogether() {
		Trainer trainer = new Trainer("Test");
		Set<Monster> monsters = new HashSet<>();
		monsters.add(pikachu);
		monsters.add(greninja);
		monsters.add(vulpix);
		
		for (Monster m : monsters) {
			trainer.addMonster(m);
		}
		Assert.assertEquals("getMonsters() should return correct monster", monsters, trainer.getMonsters());
	}
	
	@Test
	public void getMonstersByTypeWorksProperly() {
		Trainer trainer = new Trainer("Test");
		HashMap<String, Set<Monster>> monsters = new HashMap<>();
		monsters.put("Water", new HashSet<>(Arrays.asList(new WaterMonster(200, 200))));
		monsters.put("Fire", new HashSet<>(Arrays.asList(new FireMonster(100, 100), new FireMonster(50, 200))));
		monsters.put("Electric", new HashSet<>(Arrays.asList(new ElectricMonster(10, 10))));
		
		for (String type : monsters.keySet()) {
			for (Monster m : monsters.get(type)) {
				trainer.addMonster(m);
			}
		}
		
		Assert.assertEquals("getMonstersByType should return categorised monsters",
				monsters, trainer.getMonstersByType());
	}
	
	@Test
	public void canFightReturnsTrueIfMonsterAvailable() {
		Assert.assertTrue("canFight() should return true if trainer has at least one monster with hit points", ash.canFight());
	}
	
	@Test
	public void canFightReturnsFalseIfNoMonstersAvailable() {
		Trainer trainer = new Trainer("Test");
		trainer.addMonster(new ElectricMonster(0, 100));
		trainer.addMonster(new FireMonster(0, 200));
		Assert.assertFalse("canFight() should return false if all trainer's monsters are knocked out", trainer.canFight());
	}
	
	@Test
	public void canFightReturnsFalseIfNoMonsters() {
		Trainer trainer = new Trainer("Test");
		Assert.assertFalse("canFight() should return false if trainer has no monsters", trainer.canFight());
	}
	
	@Test
	public void chooseAttackMonsterWorksProperly() {
		Assert.assertEquals("chooseAttackMonster should choose monster with most AP", pikachu, ash.chooseAttackMonster());
	}
	
	@Test
	public void chooseAttackMonsterWorksProperlyWithKnockOut() {
		ash.addMonster(new ElectricMonster(0, 400));
		Assert.assertEquals("chooseAttackMonster should choose monster with most AP, ignoring knocked-out monsters", pikachu, ash.chooseAttackMonster());
	}
	
	@Test
	public void chooseDefenseMonsterWorksProperly() {
		Assert.assertEquals("chooseDefenseMonster should choose monster with most HP", greninja, ash.chooseDefenseMonster());
	}
	
	@Test
	public void doBattleReturnsCorrectWinner() {
		Battle b = new Battle (ash, brock);
		Assert.assertEquals("doBattle() should produce correct answer", ash, b.doBattle());
	}
	
	@Test
	public void doBattleResultsInCorrectHP() {
		Battle b = new Battle (ash, brock);
		b.doBattle();
		Assert.assertEquals("Hit points must be correct after battle", 0, vulpix.getHitPoints());
		Assert.assertEquals("Hit points must be correct after battle", 150, greninja.getHitPoints());
		Assert.assertEquals("Hit points must be correct after battle", 65, pikachu.getHitPoints());
	}
	
	@Test
	public void doBattleProducesPlausibleLog() {
		Battle b = new Battle (ash, brock);
		b.doBattle();
		List<String> log = b.getBattleLog();
		// Battle has 3 turns, so log should be at least 9 items long
		Assert.assertTrue("Battle log should have at least three items per turn", log.size() >= 9);
	}
}
