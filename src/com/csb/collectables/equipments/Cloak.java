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
     * @param virologist - the virologist, who uses the equipment
     */
    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setDefenseStrategy(this);
        Tester.getInstance().functionEnd();
    }

    /**
     * Remove the effect of the equipment from the virologist
     * @param virologist - the virologist, who uses the equipment
     */
    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.removeDefenseStrategy(this);
        Tester.getInstance().functionEnd();
    }

    /**
     * The defense function for the defending strategy
     * @param agent - agent is used by the attacker
     * @param attacker - the virologist, who uses the agent
     * @param defender - the attacked virologist
     */
    @Override
    public void defense(Agent agent, Virologist defender, Virologist attacker) {
        Tester.getInstance().functionStart();
        if (!UserInputHandler.getUserInputBoolean("Was the coat useful?")) {
            agent.applyEffect(defender);
        }
        Tester.getInstance().functionEnd();
    }
}
