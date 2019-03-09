package battle;

import monster.Monster;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class HumanMonsterChooser implements MonsterChooser  {


    public Scanner scanner1;
    public HumanMonsterChooser(Scanner scann)
    {
        this.scanner1 = scann;
    }
        /**
         *
         * Check Monster Set for non valid values
         */

        public static Monster chooseMonster(Set<Monster> monsters)
        {

            Scanner scanner2 = null;
        if(monsters.isEmpty())
        {
            return null;
        }
        /**
         * Iterate over monsters to get Monstrs that are able to fight
         */
        List<Monster> canFight = monsters.stream()
                .filter(m -> m.getHitPoints() > 0)
                .collect(Collectors.toList());
        canFight.forEach(s ->{
            System.out.println(s.toString());
        });
        int UserSel;
        while(true)
        {
            System.out.println("Select a monster");
            UserSel = scanner2.nextInt();
            if(!(UserSel >= canFight.size()) && !(UserSel < 0) )
            {
                return canFight.get(UserSel);
            }
            else
            {
                System.out.println("Invalid index selected");
            }
        }
    }

    @Override
    public Monster chooseAttackMonster(Set<Monster> monsters) {
        return null;
    }

    @Override
    public Monster chooseDefenseMonster(Set<Monster> monsters) {
        return null;
    }


    /**
     *
     * No idea
     */
}
