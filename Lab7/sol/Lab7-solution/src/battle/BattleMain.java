package battle;

import java.util.Scanner;

import monster.Monster;
import monster.Type;

public class BattleMain {
	public static void main(String[] args) throws Exception {
		// Old loading/saving code -- use if you want to, but there is no requirement to use my exact file format
		/*
		Trainer t1 = Trainer.loadFromFile("trainer1.txt");
		t1.setMonsterChooser(new HumanMonsterChooser(new Scanner(System.in)));
		Trainer t2 = Trainer.loadFromFile("trainer2.txt");
		t2.setMonsterChooser(new ComputerMonsterChooser());
		*/
		
		// New code -- creating the trainers directly
		Trainer t1 = new Trainer("Ash");
		t1.addMonster(new Monster("Pikachu", Type.ELECTRIC, 35, 55));
		t1.addMonster(new Monster("Rowlet", Type.GRASS, 68, 55));
		t1.addMonster(new Monster("Litten", Type.FIRE, 45, 65));
		t1.setMonsterChooser(new HumanMonsterChooser(new Scanner(System.in)));

		Trainer t2 = new Trainer("Kiawe");
		t2.addMonster(new Monster("Turtonator", Type.FIRE, 60, 78));
		t2.addMonster(new Monster("Alolan Marowak", Type.FIRE, 60, 80));
		t2.setMonsterChooser(new ComputerMonsterChooser());

		Battle b = new Battle (t1, t2);
		Trainer winner = b.doBattle();
		System.out.println("Winner is: " + winner.getName());
	}
}
