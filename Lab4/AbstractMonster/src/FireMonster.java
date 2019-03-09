class FireMonster extends Monster {

    //FireMonster Constructor
    public FireMonster(int hitPoints, int attackPoints) {
        super("Fire",hitPoints,attackPoints,new String[]{"Water"});

    }

    public boolean dodge() {
        super.bool = !bool;
        return bool;

    }




}