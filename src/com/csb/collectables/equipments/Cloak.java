package com.csb.collectables.equipments;

import com.csb.agents.Agent;
import com.csb.skeletonTester.Tester;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.strategies.DefenseStrategyInterface;
import com.csb.virologist.Virologist;

/**
 * Class for the Cloak objects. With a Cloak, the attacks towards the virologost can fail, with a gicen percentage
 */
public class Cloak extends Equipment implements DefenseStrategyInterface {

    /**
     * Apply and remove gloves effect on the virologist's defense strategy
     */
    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setDefenseStrategy(this);
        Tester.getInstance().functionEnd();
    }

    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.removeDefenseStrategy(this);
        Tester.getInstance().functionEnd();
    }

    /**
     * The defense function for the defending strategy
     * @param agent - agent is used by the attacker
     * @param attackedVirologist - the virologist, who uses the agent
     * @param attackerVirologist - the attacked virologist
     */
    @Override
    public void defense(Agent agent, Virologist attackedVirologist, Virologist attackerVirologist) {
        Tester.getInstance().functionStart();
        if (!UserInputHandler.getUserInputBoolean("Was the coat useful?")) {
            agent.applyEffect(attackedVirologist);
        }
        Tester.getInstance().functionEnd();
    }
}
