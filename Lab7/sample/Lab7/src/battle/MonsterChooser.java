package battle;
import monster.Monster;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public interface MonsterChooser {

    Monster chooseAttackMonster(Set<Monster> monsters);

    Monster chooseDefenseMonster(Set<Monster> monsters);


}


