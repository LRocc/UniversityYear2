
import battle.Battle;
import battle.Trainer;

import monster.ElectricMonster;
import monster.WaterMonster;
import monster.FireMonster;

public class BattleRunner {

	public static void main(String[] args) {
		Trainer t1 = new Trainer("Ash");
		t1.addMonster(new ElectricMonster(200, 100));

		Trainer t2 = new Trainer("Serena");
		t2.addMonster(new WaterMonster(100, 50));
		t2.addMonster(new FireMonster(100, 50));

		Battle battle = new Battle(t1, t2);
		Trainer winner = battle.doBattle();
		System.out.println("Winner is: " + winner.getName());
		System.out.println("Battle log: \n- " + String.join("\n- ", battle.getBattleLog()));
	}
}
