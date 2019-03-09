package monster;

public class ElectricMonster extends Monster {
	
	public ElectricMonster(int hitPoints, int attackPoints) {
		super("Electric",Type.valueOf("ELECTRIC"), hitPoints, attackPoints);
	}

	@Override
	public boolean dodge() {
		return false;
	}

}
