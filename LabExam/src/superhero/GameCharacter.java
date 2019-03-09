package superhero;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public final class GameCharacter implements Comparable<GameCharacter>
{
    public final String name;
    public final int cost;
    public final HashSet<Power> powers;
    public GameCharacter(String name,int cost,Power... powers)
    {
        this.name = name;
        this.cost = cost;
        this.powers = new HashSet<>(Arrays.asList(powers));
    }

    /**
     * Getters
     * @return
     */
    public final String getName()
    {
        String temp = new String(this.name);
        return temp;
    }
    public final int getCost()
    {
        Integer temp = new Integer(this.cost);
        return temp;
    }
    public final HashSet<Power> getPowers()
    {
        return new HashSet<>(this.powers);
    }


    @Override
    public int compareTo(GameCharacter o) {
        return this.cost - o.getCost();
    }


    @Override
    public String toString()
    {
        return ("Name " + this.getName() + " Cost " + this.getCost() + " Powers " + this.getPowers());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCharacter character = (GameCharacter) o;
        return getCost() == character.getCost() &&
                Objects.equals(getName(), character.getName()) &&
                Objects.equals(getPowers(), character.getPowers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCost(), getPowers());
    }
}