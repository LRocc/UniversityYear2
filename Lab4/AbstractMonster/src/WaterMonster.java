class WaterMonster extends Monster {

    public WaterMonster(int hitPoints, int attackPoints) {
        super("Water",hitPoints,attackPoints, new String[]{"Fire", "Electric"});

    }

    protected boolean dodge() {
        if (this.hitPoints >= 100) {
            super.bool = true;
            return bool;
        } else {
            super.bool = false;
            return bool;
        }
    }



}