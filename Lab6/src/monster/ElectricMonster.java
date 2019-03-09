package monster;

public class ElectricMonster extends Monster {

    public ElectricMonster(int hitPoints, int attackPoints) {
        super("Electric", hitPoints, attackPoints, new String[0]);
    }

    @Override
    public boolean dodge() {
        return false;
    }

}
