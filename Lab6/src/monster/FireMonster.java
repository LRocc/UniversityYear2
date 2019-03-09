package monster;

public class FireMonster extends Monster {

    private boolean lastDodge = false;

    public FireMonster(int hitPoints, int attackPoints) {
        super("Fire", hitPoints, attackPoints, new String[] { "Water" });
    }

    @Override
    public boolean dodge() {
        return (lastDodge = !lastDodge);
    }

}
