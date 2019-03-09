package superhero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;



public class Player
{
	HashSet<GameCharacter> characters;
	int coins;

	public Player(int coins,Set<GameCharacter> characters)
	{
		this.coins = coins;
		this.characters = (HashSet<GameCharacter>) characters;
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
            for(GameCharacter character : this.getCharacters())
            {
                if (character.getPowers().contains(power)) {
                    out.add(character);
                    selectedPowers.add(power);
                }
            }

        }
        selectionCriteria.removeAll(selectedPowers);
        if (selectionCriteria.isEmpty())
        {
            return out;
        }
        int totalCost = 0;
        HashSet<GameCharacter> toBuy = new HashSet<>();
        ArrayList<GameCharacter> available = new ArrayList<>(GameCharacters.getAllCharacters());
        Collections.sort(available);
        for(GameCharacter character : available)
        {
        	if(this.getCharacters().contains(character))
        	{
        		continue;
        	}
        	for(Power power:character.getPowers())
        	{
        		if(selectionCriteria.contains(power))
        		{
        			if(this.getCoins() >= (totalCost + character.getCost()))
        			{
        				totalCost += character.getCost();
        				toBuy.add(character);
        				for(Power power1: character.getPowers())
        				{
        					selectionCriteria.remove(power1);
        				}
        			}
        		}
        	}
        }
        if(selectionCriteria.isEmpty())
        {
        	for(GameCharacter character: toBuy)
        	{
        		this.buy(character);
        		out.add(character);
        	}
        	
        }else {
        	return null;
        }
        	return out;
    }

}