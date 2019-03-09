package superhero;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public final class GameCharacter implements Comparable<GameCharacter>
{
    private final String name;
    private final Integer cost;
    private final HashSet<Power> power;

    public GameCharacter(String name,Integer cost,Power... power)
    {
        this.name = name;
        this.power = new HashSet<>(Arrays.asList(power));
        this.cost = cost;
    }


    /**Getters and setters*/

    //Get Name
    public final String getName()
    {
        String temp = this.name;
        return temp;
    }
    //get Cost
    public final Integer getCost() {
        Integer temp = this.cost;
        return temp;
    }
    //GetPowers
    public final HashSet<Power> getPowers() {

        return new HashSet<>(this.power);
    }

    //T string
    @Override
    public final String toString()
    {
        return ("Name: "+ this.getName() + " Power: "+ this.getPowers() + " Cost: " + this.getCost());
    }


    
    @Override
    public final int compareTo(GameCharacter otherGameCharacter) {
        return this.cost - otherGameCharacter.cost;
    }

    /** HashCode And Equals */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCharacter character = (GameCharacter) o;
        return Objects.equals(getName(), character.getName()) &&
                Objects.equals(getCost(), character.getCost()) &&
                Objects.equals(power, character.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCost(), power);
    }
}
