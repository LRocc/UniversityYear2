package battle;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import monster.Monster;
import monster.MonsterException;

/**
 * Represents a Trainer, who has a set of Monsters and can battle other
 * Trainers.
 */
public class Trainer {

	/** This Trainer's name */
	private String name;
	/** The set of Monsters on this Trainer's team */
	private Set<Monster> monsters;

	/**
	 * Creates a new trainer with the given name.
	 * 
	 * @param name The name of the Trainer
	 */
	public Trainer(String name) {
		this.name = name;
		this.monsters = new HashSet<>();
	}

	/**
	 * Adds a Monster to this Trainer's set.
	 * 
	 * @param m The Monster to add
	 */
	public void addMonster(Monster m) {
		this.monsters.add(m);
	}

	/**
	 * Returns all Monsters owned by this Trainer.
	 * 
	 * @return The set of Monster
	 */
	public Set<Monster> getMonsters() {
		return monsters;
	}

	/**
	 * Returns this Trainer's name
	 * 
	 * @return This Trainer's name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the set of Monsters owned by this Trainer, divided by their type.
	 * 
	 * @return All of this Trainer's Monsters, categorised by type
	 */
	public Map<String, Set<Monster>> getMonstersByType() {
		Map<String, Set<Monster>> result = new HashMap<>();

		for (Monster m : monsters) {
			Set<Monster> typeMonsters = result.get(m.getType());
			if (typeMonsters == null) {
				typeMonsters = new HashSet<>();
				result.put(m.getType(), typeMonsters);
			}
			typeMonsters.add(m);
		}
		return result;
	}

	/**
	 * Checks whether this Trainer can still continue in a battle.
	 * 
	 * @return True if there is at least one monster with positive HP, and false if
	 *         not
	 */
	public boolean canFight() {
		for (Monster m : monsters) {
			if (m.getHitPoints() > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Chooses a monster to attack in battle. This implementation chooses the
	 * non-knocked-out Monster with the maximum Attack Points.
	 * 
	 * @return The selected Monster, or null if no such Monster can be found
	 */
	public Monster chooseAttackMonster() {
		Monster result = null;
		for (Monster m : monsters) {
			if (result == null || m.getAttackPoints() > result.getAttackPoints()) {
				if (m.getHitPoints() > 0) {
					result = m;
				}
			}
		}
		return result;
	}

	/**
	 * Chooses a monster to defent in battle. This implementation chooses the
	 * non-knocked-out Monster with the maximum Hit Points.
	 * 
	 * @return The selected Monster, or null if no such Monster can be found
	 */
	public Monster chooseDefenseMonster() {
		Monster result = null;
		for (Monster m : monsters) {
			if (result == null || m.getHitPoints() > result.getHitPoints()) {
				if (m.getHitPoints() > 0) {
					result = m;
				}
			}
		}
		return result;
	}

}
