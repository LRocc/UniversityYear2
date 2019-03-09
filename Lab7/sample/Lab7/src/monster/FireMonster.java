package monster;

public class FireMonster extends Monster {
	
	private boolean lastDodge = false;
	
	public FireMonster(int hitPoints, int attackPoints) {
		super("Fire",Type.valueOf("FIRE"), hitPoints, attackPoints);
	}

	@Override
	public boolean dodge() {
		return (lastDodge = !lastDodge);
	}

}
