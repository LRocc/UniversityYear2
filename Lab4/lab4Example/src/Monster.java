public abstract class Monster
{
	protected int attackPoints;
	protected int hitPoints;
	public String type;
	public String[] weaknesses;
	protected boolean bool = true;

	public Monster(String type, String[] weaknesses) {
		this.type = type;
		this.weaknesses = weaknesses;
	}

	protected abstract boolean dodge();

	protected abstract int getHitPoints();

	protected abstract int getAttackPoints();

	//public abstract String getType();

	public void attack(Monster otherMonster) throws MonsterException {
		//Check if monster will dodge
		if (otherMonster.dodge()) {
			this.hitPoints = hitPoints - 20;
			throw new MonsterException(otherMonster + " Dodged!");


		} else{
			// A monster cannot attack itself
			if (otherMonster == this) {
				throw new MonsterException("Can't attack yourself");
			}
			// A monster cannot attack or be attacked if it is knocked out

			if (this.hitPoints <= 0 || otherMonster.getHitPoints() <= 0) {
				throw new MonsterException("One of the monsters is dead");
			}



			// Check if the other monster is weak against our type
			boolean otherIsWeak = otherMonster.isWeakAgainst(type);
			int pointsToRemove = (otherIsWeak) ? this.attackPoints + 20 : this.attackPoints;
			otherMonster.removeHitPoints(pointsToRemove);
		}
	}


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
			// Monster is knocked out
			hitPoints = 0;
		}
	}





}

class MonsterException extends Exception
{
	public MonsterException(String string1)
	{
		super(string1);
	}
}

class FireMonster extends Monster
{

	//FireMonster Constructor
	public FireMonster(int hitPoints,int attackPoints)
	{
		super("fire",new String[]{"Water"});
		this.hitPoints = hitPoints;
		this.attackPoints = attackPoints;
	}

	public boolean dodge()
	{
		bool = !bool;
		return bool;

	}

	public int getHitPoints()
	{
		return this.hitPoints;
	}
	public int getAttackPoints()
	{
		return this.attackPoints;
	}



}

class WaterMonster extends Monster{

	public WaterMonster(int hitPoints,int attackPoints)
	{
		super("water",new String[]{"fire","electric"});
		this.attackPoints = attackPoints;
		this.hitPoints = hitPoints;
	}

	protected boolean dodge()
	{
		if(this.hitPoints >= 100){
			bool = true;
			return bool;
		}else{
			bool = false;
			return bool;
		}
	}
	public int getHitPoints()
	{
		return this.hitPoints;
	}
	public int getAttackPoints()
	{
		return this.attackPoints;
	}

}
class ElectricMonster extends Monster
{
	public ElectricMonster(int hitPoints,int attackPoints)
	{
		super("electric",new String[]{});
		this.attackPoints = attackPoints;
		this.hitPoints = hitPoints;
	}


	protected boolean dodge()
	{
		bool = false;
		return bool;
	}

	public int getHitPoints()
	{
		return this.hitPoints;
	}
	public int getAttackPoints()
	{
		return this.attackPoints;
	}

}

