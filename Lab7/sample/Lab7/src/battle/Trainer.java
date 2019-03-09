package battle;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import monster.ElectricMonster;
import monster.FireMonster;
import monster.Monster;
import monster.WaterMonster;

/**
 * Represents a Trainer, who has a set of Monsters and can battle other
 * Trainers.
 */
public class Trainer implements MonsterChooser {
    /** The choosen monster*/
	Monster chooser ;
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
	/**
	public Monster chooseAttackMonster(Set<Monster> monsters)
	{

          		for (Monster m : monsters) {
          			if (chooser == null || m.getAttackPoints() > chooser.getAttackPoints()) {
          				if (m.getHitPoints() > 0)
          				{
          					chooser = m;
          				}
          			}
          		}
          		return chooser;
	}
	*/
	@Override
	public Monster chooseAttackMonster(Set<Monster> monsters)
	{
		System.out.println("Choose atk Monster");
		return HumanMonsterChooser.chooseMonster(monsters);
	}
	/**
	 * Chooses a monster to defent in battle. This implementation chooses the
	 * non-knocked-out Monster with the maximum Hit Points.
	 * 
	 * @return The selected Monster, or null if no such Monster can be found
	 */
	/**
	public Monster chooseDefenseMonster(Set<Monster> monsters) {
		for (Monster m : monsters) {
			if (chooser== null || m.getHitPoints() > chooser.getHitPoints()) {
				if (m.getHitPoints() > 0) {
					chooser = m;
				}
			}
		}
		return chooser;
	}

	*/
	@Override
	public Monster chooseDefenseMonster(Set<Monster> monsters)
	{
		System.out.println("Choose def. monster ");
		return HumanMonsterChooser.chooseMonster(monsters);
	}

	public void setMonsterChooser(MonsterChooser monsterChooser) /** WHAT!??? no idea what to do ask a tutor*/
    {
        monsterChooser.chooseAttackMonster(monsters);
        monsterChooser.chooseDefenseMonster(monsters);
    }

	/**
	 * Loads a Trainer from the given file and returns the result.
	 * 
	 * @param filename The file to load from
	 * @return A Trainer based on the information in the file
	 * @throws IOException               If the file cannot be accessed
	 */
	public static Trainer loadFromFile(String filename) throws IOException {
		// Load the whole file into a List<String> in memory
		Path p = Paths.get(filename);
		List<String> lines = Files.readAllLines(p);

		// First line is name
		String name = lines.remove(0);
		Trainer trainer = new Trainer(name);

		// Process the rest of the lines into Monster objects
		for (String line : lines) {
			// Split the line
			String[] fields = line.split("\\^");
			// Use the fields to create a new Monster and add it
			Monster monster = createMonster(fields[0],fields[1], Integer.parseInt(fields[2]),
					Integer.parseInt(fields[3]));
			if (monster != null) {
				trainer.addMonster(monster);
			}
		}

		// Return the newly created Trainer
		return trainer;
	}

	/**
	 * Saves the current Trainer to the given file.
	 * 
	 * @param filename The file to save to
	 * @throws IOException If there is an error accessing the file.
	 */
	public void saveToFile(String filename) throws IOException {
		// Open the file
		PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(filename)));

		writer.println(this.name);

		// Write the monsters one line at a time
		for (Monster monster : monsters) {
			String[] properties = new String[4];
			properties[0] = monster.getName();
			properties[1] = monster.getType();
			properties[2] = String.valueOf(monster.getHitPoints());
			properties[3] = String.valueOf(monster.getAttackPoints());
			writer.println(String.join("^", properties));
		}

		// We are done!
		writer.close();
	}

	/** Helper method: creates a new Monster object based on the parameters */
	private static Monster createMonster(String name,String type, int hitPoints, int attackPoints) {
		switch (type) {
		case "Fire":
			return new FireMonster(hitPoints, attackPoints);
			
		case "Water":
			return new WaterMonster(hitPoints, attackPoints);
			
		case "Electric":
			return new ElectricMonster(hitPoints, attackPoints);
		}
		System.err.println("Invalid type: " + type);
		return null;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Trainer trainer = (Trainer) o;
		return Objects.equals(getName(), trainer.getName()) &&
				Objects.equals(getMonsters(), trainer.getMonsters());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getMonsters());
	}



}
