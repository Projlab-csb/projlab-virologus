package com.csb.strategies;

import com.csb.agents.Agent;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

/**
 * The class, what describe the actions of the Virologist, when it is attacked.
 *
 */
public class DefaultDefense implements DefenseStrategyInterface{

    /**
     * Default defense - the attacked virologist suffers the effect
     */
    @Override
    public void defense(Agent agent,Virologist attackedVirologist, Virologist attackerVirologist) {
        Tester.getInstance().functionStart();
        agent.applyEffect(attackedVirologist);
        Tester.getInstance().functionEnd();
    }
}
