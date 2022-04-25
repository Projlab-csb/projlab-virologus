package com.csb.strategies;

import com.csb.agents.Agent;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;
import java.io.Serializable;

/**
 * The class, what describe the actions of the Virologist, when it is attacked.
 *
 */
public class DefaultDefense implements DefenseStrategyInterface, Serializable {

    /**
     * Default defense - the attacked virologist suffers the effect
     */
    @Override
    public void defense(Agent agent, Virologist attackedVirologist, Virologist attackerVirologist) {
        agent.applyEffect(attackedVirologist);
        attackedVirologist.storeAgent(agent);
        if (attackerVirologist != null) System.out.println(
            attackedVirologist.getName() + " get " + agent.getClass().getSimpleName() + " from " + attackerVirologist.getName()
        ); else System.out.println("The cursedLab attacked with Beardance " + attackedVirologist.getName());
    }
}
