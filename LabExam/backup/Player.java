package superhero;

import java.util.*;

public class Player{
    public int coins;
    public HashSet<GameCharacter> characters;

    public Player(int coins, Set<GameCharacter> characters)
    {
        this.coins = coins;
        this.characters = (HashSet<GameCharacter>)characters;
    }

    public int getCoins()
    {
        return this.coins;
    }
    public Set<GameCharacter> getCharacters()
    {
        return this.characters;
    }

    public void buy(GameCharacter gc) throws IllegalArgumentException
    {
        if(this.getCharacters().contains(gc))
        {
            throw new IllegalArgumentException();
        }
        if(this.getCoins() - gc.getCost() < 0)
        {
            throw new IllegalArgumentException();
        }
        this.characters.add(gc);
        this.coins = this.coins - gc.getCost();
    }

    public Set<GameCharacter> chooseCharacters(Power... neededPowers)
    {
        HashSet<Power> selectionCriteria = new HashSet<>(Arrays.asList(neededPowers));
        HashSet<Power> selectedPowers = new HashSet<>();
        HashSet<GameCharacter> out = new HashSet<>();

        for (Power power : selectionCriteria)
        {
            GameCharacter temp = this.getCharactersMethod(this.characters,power);
            if (temp == null)
            {
                return null;
            }
            out.add(temp);
            selectedPowers.add(power);
        }

        selectionCriteria.removeAll(selectedPowers);
        if (selectionCriteria.isEmpty())
        {
            return out;
        }
        HashSet<GameCharacter> toBuy = new HashSet<>();
        for (Power power : selectionCriteria)
        {
            GameCharacter temp = this.getCharactersListMethod(GameCharacters.getAllCharacters(),power);
            if (temp == null)
            {
                return null;
            }
            toBuy.add(temp);
        }
        int totalCost = toBuy.stream().mapToInt(a -> a.getCost()).sum();
        if (this.getCoins() - totalCost < 0)
        {
            return null;
        }
        for (GameCharacter character:toBuy)
        {
            this.buy(character);
            out.add(character);
        }
        return out;
    }

    private GameCharacter getCharactersListMethod(List<GameCharacter> allCharacters, Power power)
    {
        ArrayList<GameCharacter> temp = (ArrayList<GameCharacter>) allCharacters;
        Collections.sort(temp);
        for (GameCharacter character : temp)
        {
            if (character.getPowers().contains(power))
            {
                return character;
            }
        }
        return null;
    }

    private GameCharacter getCharactersMethod(HashSet<GameCharacter> characters, Power power)
    {
        for (GameCharacter character : characters)
        {
            if (character.getPowers().contains(power))
            {
                return character;
            }
        }
        return null;
    }
}