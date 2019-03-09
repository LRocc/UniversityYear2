package battle;

import monster.Monster;

import java.util.Comparator;
import java.util.Set;

public class ComputerMonsterChooser {
    public Monster chooseDefenseMonster(Set<Monster> monsters) {
        Monster result = monsters.stream()
                .filter(m -> m.getHitPoints() > 0)
                .max(Comparator.comparing(m -> m.getHitPoints())).get();
        return result;
    }

    public Monster chooseAttackMonster(Set<Monster> monsters)
    {

        Monster result = monsters.stream()
                .filter(m -> m.getHitPoints() > 0)
                .max(Comparator.comparing(m -> m.getHitPoints())).get();
        return result;
    }
}
