package com.csb.strategies;

import com.csb.agents.Agent;
import com.csb.virologist.Virologist;

/**
 * Interface for the functions of the defending strategy
 */
public interface DefenseStrategyInterface {
    public void defense(Agent agent,Virologist defenderVirologist, Virologist attackerVirologist);
}
