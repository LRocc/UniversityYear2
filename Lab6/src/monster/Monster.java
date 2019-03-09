package monster;
import battle.Trainer;

import java.util.Arrays;
import java.util.Collection;


/**
 * Represents a Monster for a battling game.
 */
public abstract class Monster implements Comparable<Monster> {

    // Fields
    protected String type;
    protected int hitPoints;
    protected int attackPoints;
    protected String[] weaknesses;

    /** Creates a new Monster with the given properties */
    public Monster(String type, int hitPoints, int attackPoints, String[] weaknesses) {
        this.type = type;
        this.hitPoints = hitPoints;
        this.attackPoints = attackPoints;
        this.weaknesses = weaknesses;
    }

    // Getters and setters
    public int getHitPoints() {
        return hitPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public String getType() {
        return this.type;
    }

    /**
     * Attacks another Monster
     * @param otherMonster The other Monster to attack
     * @throws MonsterException if either Monster is knocked out, or if otherMonster == this
     */
    public void attack(Monster otherMonster) throws MonsterException {
        // A monster cannot attack itself
        if (otherMonster == this) {
            throw new MonsterException("A monster cannot attack itself");
        }

        // A monster cannot attack or be attacked if it is knocked out
        if (this.hitPoints <= 0 || otherMonster.getHitPoints() <= 0) {
            throw new MonsterException("Knocked out monsters cannot attack or be attacked");
        }

        if (otherMonster.dodge()) {
            this.removeHitPoints(10);
        } else {
            // Check if the other monster is weak against our type
            boolean otherIsWeak = otherMonster.isWeakAgainst(type);
            int pointsToRemove = (otherIsWeak) ? this.attackPoints + 20 : this.attackPoints;
            otherMonster.removeHitPoints(pointsToRemove);
        }
    }

    /**
     * Allows a Monster to dodge in battle.
     *
     * @return True if the Monster will dodge when next attacked, and false if not
     */
    public abstract boolean dodge();

    /**
     * Checks if this Monster is weak against another type.
     *
     * @param otherType The type to look for
     * @return True if otherType is a weakness of this Monster, and false if not
     */
    public boolean isWeakAgainst(String otherType) {
        for (String weakness : this.weaknesses) {
            if (weakness.equals(otherType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the indicated number of hit points from this Monster. Hit points cannot go below zero.
     *
     * @param points The points to remove
     */
    private void removeHitPoints(int points) {
        this.hitPoints -= points;
        if (hitPoints <= 0) {
            // Monster is knocked out
            hitPoints = 0;
        }
    }

    @Override
    public String toString() {
        return "(type:" + type + ", hp:" + hitPoints + ", ap:" + attackPoints + ", wk:" + Arrays.toString(weaknesses) + ")";
    }

    @Override
    public int compareTo(Monster m)
    {
        int temp = m.getHitPoints() - this.getHitPoints();
        if(temp ==0)
        {
           temp = m.attackPoints - this.attackPoints;
           if(temp == 0)
           {
               return this.getType().compareTo(m.getType());
           }else
           {
               return temp;
           }
        }else
        {
            return temp;
        }
    }



}
