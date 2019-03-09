package battle;

import monster.Monster;

import java.util.*;

public class Trainer {
    public String name;
    public String monsterS = new String();
    public Set<Monster> monsters = new HashSet<>();
    public HashMap<String,Set<Monster>> monstersMap = new HashMap<>();

    //public String index = "1";
    //~ Constructor for the trainer Class,takes in a String value and
    //~ Uses it to populate the name Parameter for Trainer
    public Trainer(String name)
    {
        this.name = name;
    }

    //~ Getters


    public String getName()
    {
        return this.name;
    }

    public Set<Monster> getMonsters()
    {
        return this.monsters;
    }

    //~LOOK AT IT LATER,MORE DIFFICULT THAN EXPECTED
    public Map<String,Set<Monster>> getMonstersByType()

    {
        for(Monster m : this.monsters)
        {
                HashSet<Monster> temp = new HashSet<>();
                temp.add(m);

                //System.out.println(temp);
                monsterS = m.getType();
                if(monstersMap.containsKey(monsterS))
                {
                    //If a monster shares the same key with onether one than we use .get()
                    //to access the monster at that entry and we add a second Set to it containing only the second monster
                    monstersMap.get(m.getType()).add(m);

                }else{
                    //tempList.add(monsterS);
                    HashSet<Monster> types = new HashSet<>();
                    types.add(m);
                    monstersMap.put(m.getType(),temp);
                }

        }
        return monstersMap;
    }

    //~Getters end

    //@Return true if there's at least a monster that can fight
    //@the condition is hitpoints > 0
    public boolean canFight()
    {
        for(Monster m: this.monsters)
        {
            if(m.getHitPoints() > 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    return false;
    }



    //@Add Monster to this Trainer's set
    public void addMonster(Monster monster)
    {
        this.monsters.add(monster);
    }

    //@Monster Battle
    //~~~~~~~Choose attacker~~~~~~~//
    public Monster chooseAttackMonster()
    {
        int initial = 0;
        //int temp = 0;
        //~We evaluate a variable initial to the highest attack points
        //~That a monster in the trainer's set can hav

        for(Monster m : this.monsters)
        {
            if(m.getHitPoints() != 0)
            {
                if(m.getAttackPoints() > initial)
                {
                    initial = m.getAttackPoints();
                }
            }else{
                continue;
            }

        }
        //~now we iterate over the monster set again and look for which
        //~Monster's attack point where the highest
        for(Monster m:this.monsters)
        {
            if(m.getAttackPoints() == initial)
            {

                if(m.getHitPoints() > 0)
                {
                    return m;
                }
            }
        }
        return null;
    }
    //~~~~~~End of choose attacker~~~~~~~//

    //~~~~~Choose defender~~~~~~//
    public Monster chooseDefenseMonster()
    {
        //~~~~Same as chooseAttacker we iterate over the monster set and initialise a temp variable "initial"
        //~~~~In order to get the highest hitpoints value that a monster can have
        int initial = 0;
        for(Monster m : this.monsters)
        {
            if(m.getHitPoints() > 0 && m.getHitPoints() > initial)
            {
                initial = m.getHitPoints();
            }
        }
        //~~~~On the second iteraation we look for what monster in the set had the highest hotpoint value
        for(Monster m : this.monsters)
        {
            if(m.getHitPoints() == initial)
            {
                return m;
            }
        }

        return null;
    }
    //~~~~~End of choose defender~~~~~//



}
