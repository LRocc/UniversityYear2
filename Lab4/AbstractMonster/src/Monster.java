public abstract class Monster {
    protected int attackPoints;
    protected int hitPoints;
    public String type;
    public String[] weaknesses;
    protected boolean bool = false;


    public Monster(String type,int hitPoints,int attackPoints, String[] weaknesses) {
        this.type = type;
        this.weaknesses = weaknesses;
        this.attackPoints = attackPoints;
        this.hitPoints = hitPoints;
    }

    protected abstract boolean dodge();

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

    public void attack(Monster otherMonster) throws MonsterException {
        //Check if monster will dodge


        // A monster cannot attack itself
        if (otherMonster == this) {
            throw new MonsterException("Can't attack yourself");
        }
        // A monster cannot attack or be attacked if it is knocked out

        if (this.hitPoints <= 0 || otherMonster.getHitPoints() <= 0) {

            throw new MonsterException("One of the monsters is dead");
        }
        // Check if the other monster is weak against our type
        if (otherMonster.dodge()) {
            this.removeHitPoints(10);
            //throw new MonsterException(otherMonster + " Dodged!");
        } else {
            boolean otherIsWeak = otherMonster.isWeakAgainst(type);
            int pointsToRemove = (otherIsWeak) ? this.attackPoints + 20 : this.attackPoints;
            otherMonster.removeHitPoints(pointsToRemove);
        }
    }


    public boolean isWeakAgainst(String otherType) {
        for (String weakness : this.weaknesses) {
            if (weakness.equals(otherType)) {
                return true;
            }
        }
        return false;
    }

    private void removeHitPoints(int points) {
        this.hitPoints -= points;
        if (hitPoints <= 0) {
            // Monster is knocked out
            hitPoints = 0;
        }
    }


}









