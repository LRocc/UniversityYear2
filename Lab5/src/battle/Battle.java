package battle;
import monster.Monster;
import monster.MonsterException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
public class Battle {
    private Trainer trainer1;
    private Trainer trainer2;
    private int turn = 1;
    List<String> mylist = new ArrayList<>();

    public Battle(Trainer trainer1, Trainer trainer2) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;

        System.out.println("Initiating Battle between " + trainer1.name + " And " + trainer2.name);
        //~~~Checks if the trainers can fight
        System.out.println(trainer1.name + " Enters the battle with " + trainer1.getMonsters() + "\n");
        System.out.println(trainer2.name + " Enters the battle with " + trainer2.getMonsters() + "\n");
        /*try {
            doBattle(t1, t2);
        }catch(MonsterException e )
        {
            System.out.println("HEYYY SOMETHING FAILED BADLY");
        }*/

    }

    public Trainer doBattle() {
        Monster attackMonster;
        Monster defenseMonster;
        Trainer attacker = this.trainer1;
        Trainer defender = this.trainer2;
        //First we establish a looping check that goes on for as long as both trainer1 and trainer 2 can fight
        while (trainer1.canFight() && trainer2.canFight()) {
            attackMonster = attacker.chooseAttackMonster();
            defenseMonster = defender.chooseDefenseMonster();
            this.getBattleLog(attacker, defender);
            this.getBattleLog(attackMonster, defenseMonster, false);
            try
            { attackMonster.attack(defenseMonster);
            } catch (MonsterException e)
            {
                e.printStackTrace();
                System.out.println(e.toString());
            }
            this.getBattleLog(attackMonster, defenseMonster, true);
            //Compare which was the previous attacker and who was the defender
            //Switch them accordingly
            attacker = (attacker == trainer1) ? trainer2 : trainer1;
            defender = (defender == trainer2) ? trainer1 : trainer2;

        }

        //pick the trainer than can still fight (winner)
        Trainer winner = (trainer1.canFight()) ? trainer1 : trainer2;
        this.getBattleLog(winner);

        return winner;
    }




    //Log routines,we define 3 different modules to update the battleLog

    private void getBattleLog(Trainer winner) {
        String message = "The winner is: " + winner.getName();
        this.mylist.add(message);

    }

    public List<String> getBattleLog() {
        return this.mylist;
    }

    private void getBattleLog(Trainer attacker, Trainer defender) {

        String log = "The attacker is:" + attacker.getName() + "\nThe defender is " + defender.getName();
        this.mylist.add(log);
    }

    private void getBattleLog(Monster attacker, Monster defender, boolean ItHappened) {

        String log = (ItHappened) ? "After attack":"Before attack";
        log += "\nAttacking Monster: " + attacker.toString() + " Defending monster: " + defender.toString();
        this.mylist.add(log);

    }

}