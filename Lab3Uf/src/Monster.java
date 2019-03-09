import java.util.*;

public class Monster {
    /*
     *@Monster is a class that creates 3 objects Monster that are
     *going to battle each other.
     *the messages displayed are going to be the hitpoints of the monsters decreasing as they battle each other
     */

    private int hitPoints;
    private String type;
    private int attackPoints;
    private String[] weaknesses;

    /*
        *Constructor of the Monster class.
        * when supplied the right parameters it will create a new Monster object

     */
    public Monster(String type, int hitPoints, int attackPoints,
                    String[]weaknesses) {
        this.type = type;
        this.hitPoints = hitPoints;
        this.attackPoints = attackPoints;
        this.weaknesses = weaknesses;
    }


    public String getType() {
        //System.out.println(type);
        return this.type;
    }

    public int getHitPoints() {
        //System.out.println(hitPoints)
        return this.hitPoints;
    }

    public int getAttackPoints() {
        //System.out.println(attackPoints);
        return this.attackPoints;
    }
    //this method overrides the default toString() method supplied by java
    //As the default one prints criptic values
    public String toString() {
        return "Type=" + type + " hitPoints=" + hitPoints + ", attackPoints=" + attackPoints+" ,weaknesses="+ Arrays.toString(weaknesses);
    }

    public Boolean isWeakAgainst(String otherType)         //*this IF controls if our monster is weak to a certain type.
    {
     String weaknessesString = new String(Arrays.toString(this.weaknesses));   //*we convert the whole array of weaknesses of out monster to a string for easier
                                                                                //*access to it
     //System.out.println(weaknessesString);
     if(weaknessesString.contains(otherType))
     {
         //System.out.println("I AM WEAK AGAINST "+otherType+"!");
         return true;
     }else{
         //System.out.println("I AM NOT WEAK AGAINST "+otherType+"!");
         return false;
     }


    }

    public void removeHitpoints(int pointsToRemove) //this method removes a supplied nomber of hitpoints from a monster
    {
        this.hitPoints = hitPoints - pointsToRemove;
        //System.out.println("Damaged for "+pointsToRemove+"!");
        if(this.hitPoints <= 0)
        {
            this.hitPoints = 0;
            //System.out.println("The monster is dead!");
        }

    }

    public String getName()
    {
        String name = new String();
        name.getClass().getName();
        System.out.println(name);
        return name;

    }

    public boolean attack(Monster otherMonster)
    {
        if(this.equals(otherMonster)) //controlling whether the monster to attack is the monster itself,it this control is true,then we return false
                                        //as the monste cant attack itself
        {
            //System.out.println("Can't attack yourself!");
            return false;
        }else{
            if(this.hitPoints == 0 || otherMonster.hitPoints == 0) //*controlling hitpoints value of our monster and the attacked monster,if either is 0,one of the monster is dead
            {
                //System.out.println("One of the monsters is dead!!");
                return false;

            }else{
                    if(otherMonster.isWeakAgainst(this.getType())) //*cheking if the attacked monster is weak against the attacker,if so,
                    {
                        int points = this.attackPoints;
                        points = points + 20;               //*we romove a bonus of 20 points from the attacked monster hitPoints
                        otherMonster.removeHitpoints(points);
                        return true;
                    }else{
                        int points = this.attackPoints;  //*otherwise,the attack will remove the standard amount of attackpoints
                        otherMonster.removeHitpoints(points);
                        return true;
                    }
            }
        }


    }



}
