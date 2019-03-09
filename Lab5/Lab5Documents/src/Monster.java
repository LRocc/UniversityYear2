

import java.util.Arrays;

public abstract class Monster {

	protected String type;
	protected int hitPoints;
	protected int attackPoints;
	protected String[] weaknesses;

	public Monster(String type, int hitPoints, int attackPoints, String[] weaknesses) {
		this.type = type;
		this.hitPoints = hitPoints;
		this.attackPoints = attackPoints;
		this.weaknesses = weaknesses;
	}

	// Getters and setters
	public int getHitPoints() {
		return hitPoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public String getType() {
		return this.type;
	}

	public boolean attack(Monster otherMonster) throws MonsterException {
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
			return false;
		} else {
			// Check if the other monster is weak against our type
			boolean otherIsWeak = otherMonster.isWeakAgainst(type);
			int pointsToRemove = (otherIsWeak) ? this.attackPoints + 20 : this.attackPoints;
			otherMonster.removeHitPoints(pointsToRemove);
			return true;
		}
	}
	
	public abstract boolean dodge();
	
	public boolean isWeakAgainst(String otherType) {
		for (String weakness : this.weaknesses) {
			if (weakness.equals(otherType)) {
				return true;
			}
		}
		return false;
	}

	private void removeHitPoints(int points) {
		this.hitPoints -= points;
		if (hitPoints <= 0) {
			// monster.Monster is knocked out
			hitPoints = 0;
		}
	}

	@Override
	public String toString() {
		return "(type:" + type + ", hp:" + hitPoints + ", ap:" + attackPoints + ", wk:" + Arrays.toString(weaknesses) + ")";
	}

}
