package battle;

import java.util.ArrayList;
import java.util.List;

import monster.Monster;
import monster.MonsterException;

/**
 * A class to represent a battle between two Trainers.
 */
public class Battle {
	
	/** The trainers who are battling */
	private Trainer trainer1, trainer2;
	
	/** A record of every event during the battle */
	private List<String> battleLog;
	
	/** Creates a new Battle between the two Trainers */
	public Battle (Trainer trainer1, Trainer trainer2) {
		this.trainer1 = trainer1;
		this.trainer2 = trainer2;
		this.battleLog = new ArrayList<>();
	}
	
	/** Executes the battle, and returns the winner -- or null if the result is a draw. */
	public Trainer doBattle() {
		battleLog.add("Battle between " + trainer1.getName() + " and " + trainer2.getName());
		battleLog.add("Trainer 1 monsters: " + trainer1.getMonsters());
		battleLog.add("Trainer 2 monsters: " + trainer2.getMonsters());
		
		boolean order = true;
		while (trainer1.canFight() && trainer2.canFight()) {
			oneAttack (order);
			order = !order;
		}
		
		if (!trainer1.canFight() && !trainer2.canFight()) {
			return null;
		} else if (trainer1.canFight()) {
			return trainer1;
		} else {
			return trainer2;
		}
	}
	
	/** Returns the list of events during the battle */
	public List<String> getBattleLog() {
		return battleLog;
	}
	
	/** Helper method -- implements one attack during the battle.
	 * 
	 * @param order If true, trainer1 attacks trainer2; if false, trainer2 attacks trainer1.
	 */
	private void oneAttack(boolean order) {
		Trainer attacker, defender;
		if (order) {
			attacker = trainer1;
			defender = trainer2;
		} else {
			attacker = trainer2;
			defender = trainer1;
		}
		
		battleLog.add(attacker.getName() + " attacks " + defender.getName());
		
		Monster attackMonster = attacker.chooseAttackMonster();
		battleLog.add(attacker.getName() + " chooses " + attackMonster);
		Monster defenseMonster = defender.chooseDefenseMonster ();
		battleLog.add(defender.getName() + " chooses " + defenseMonster);
		
		battleLog.add(attackMonster + " attacks " + defenseMonster);
		try {
			attackMonster.attack(defenseMonster);
		} catch (MonsterException ex) {
			battleLog.add("Attacking error: " + ex.getMessage());
		}
		
		battleLog.add("Attacker monsters now: " + attacker.getMonsters());
		battleLog.add("Defender monsters now: " + defender.getMonsters());
	}

}
