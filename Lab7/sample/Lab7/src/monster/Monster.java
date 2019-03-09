package monster;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a Monster for a battling game.
 */
public class Monster implements Comparable<Monster> {

	// Fields
	protected String name;
	protected String type;
	protected int hitPoints;
	protected int attackPoints;
	protected String[] weaknesses;
	private boolean lastDodge = false;

	/** Creates a new Monster with the given properties */
	public Monster(String name,Type type, int hitPoints, int attackPoints) {
		this.name = name;
		this.type = getType(); /**PROBABLY DOESEN'T WORK */
		this.hitPoints = hitPoints;
		this.attackPoints = attackPoints;
	}

	// Getters and setters
	public int getHitPoints() {
		return hitPoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public String getType() {
		return this.type;  /**PROBABLY DOESEN'T WORK */
	}
	public String getName(){return name;}

	/**
	 * Attacks another Monster
	 * @param otherMonster The other Monster to attack
	 * @throws MonsterException if either Monster is knocked out, or if otherMonster == this
	 */
	public void attack(Monster otherMonster) throws MonsterException {
		// A monster cannot attack itself
		if (otherMonster == this) {
			throw new MonsterException("A monster cannot attack itself");
		}

		// A monster cannot attack or be attacked if it is knocked out
		if (this.hitPoints <= 0 || otherMonster.getHitPoints() <= 0) {
			throw new MonsterException("Knocked out monsters cannot attack or be attacked");
		}

		if (otherMonster.dodge()) {
			this.removeHitPoints(10);
		} else {
			// Check if the other monster is weak against our type
			boolean otherIsWeak = otherMonster.isWeakAgainst(type);
			int pointsToRemove = (otherIsWeak) ? this.attackPoints + 20 : this.attackPoints;
			otherMonster.removeHitPoints(pointsToRemove);
		}
	}

	/**
	 * Allows a Monster to dodge in battle.
	 *
	 * @return True if the Monster will dodge when next attacked, and false if not
	 */
	public boolean dodge() {
		if(this.getType().equals("FIRE"))
		{
			return (lastDodge = !lastDodge);
		}
		if(this.getType().equals("WATER"))
		{
			return (hitPoints >= 100);
		}
		if(this.getType().equals("ELECTRIC"))
		{
			return false;
		}
		if(this.getType().equals("GRASS"))
		{
			return false;
		}
	return false;
	}

	/**
	 * Checks if this Monster is weak against another type.
	 * 
	 * @param otherType The type to look for
	 * @return True if otherType is a weakness of this Monster, and false if not
	 */
	public boolean isWeakAgainst(String otherType) {
		for (String weakness : this.weaknesses) {
			if (weakness.equals(otherType)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes the indicated number of hit points from this Monster. Hit points cannot go below zero.
	 * 
	 * @param points The points to remove
	 */
	private void removeHitPoints(int points) {
		this.hitPoints -= points;
		if (hitPoints <= 0) {
			// Monster is knocked out
			hitPoints = 0;
		}
	}

	@Override
	public String toString() {
		return "(Name:"+name+",type:" + this.getType()+ ", hp:" + hitPoints + ", ap:" + attackPoints + ", wk:" + Arrays.toString(weaknesses) + ")";
	}

	@Override
	public int compareTo (Monster otherMonster) {
		int result = Integer.compare(otherMonster.hitPoints, this.hitPoints);
		if (result == 0) {
			result = Integer.compare(otherMonster.attackPoints, this.attackPoints);
		}
		if (result == 0) {
			result = this.type.compareTo(otherMonster.type);
		}
		return result;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Monster monster = (Monster) o;
		return getHitPoints() == monster.getHitPoints() &&
				getAttackPoints() == monster.getAttackPoints() &&
				lastDodge == monster.lastDodge &&
				Objects.equals(getName(), monster.getName()) &&
				Objects.equals(getType(), monster.getType()) &&
				Arrays.equals(weaknesses, monster.weaknesses);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(getName(), getType(), getHitPoints(), getAttackPoints(), lastDodge);
		result = 31 * result + Arrays.hashCode(weaknesses);
		return result;
	}
}
