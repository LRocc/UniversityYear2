package monster;

public class WaterMonster extends Monster {

	public WaterMonster(int hitPoints, int attackPoints) {
		super("Water",Type.valueOf("WATER"), hitPoints, attackPoints);
	}
	
	@Override
	public boolean dodge() {
		return (hitPoints >= 100);
	}

}
