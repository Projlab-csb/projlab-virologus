package com.csb.virologist;

import com.csb.agents.Agent;
import com.csb.strategies.DefenseStrategyInterface;

public class Virologist {
    public DefenseStrategyInterface defenseStrategy;

    public Virologist(DefenseStrategyInterface defenseStrategy) {
        this.defenseStrategy = defenseStrategy;
    }

    public void setDefenseStrategy(DefenseStrategyInterface defenseStrategy) {
        this.defenseStrategy = defenseStrategy;
    }

    public void attack(Agent agent, Virologist virologist, Virologist targetVirologist) {
        defenseStrategy.defend();
    }
}
