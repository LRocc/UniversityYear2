class ElectricMonster extends Monster {
    public ElectricMonster(int hitPoints, int attackPoints) {
        super("Electric",hitPoints,attackPoints, new String[]{});

    }
    protected boolean dodge() {
        super.bool = false;
        return bool;
    }



}