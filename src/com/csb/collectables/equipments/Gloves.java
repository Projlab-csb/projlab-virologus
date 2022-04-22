package com.csb.collectables.equipments;

import com.csb.agents.Agent;
import com.csb.skeletonTester.TestInterface;
import com.csb.skeletonTester.Tester;
import com.csb.strategies.DefenseStrategyInterface;
import com.csb.virologist.Virologist;

/**
 * Class for the Gloves objects. With Gloves the virologist instead of suffer the agents effect, attacks back the during an attack
 */
public class Gloves extends Equipment implements DefenseStrategyInterface {

    int life = 3;

    /**
     * The defense function for the defending strategy
     * @param agent - agent is used by the attacker
     * @param defenderVirologist - the virologist, who uses the agent
     * @param attackerVirologist - the attacked virologist
     */
    @Override
    public void defense(Agent agent, Virologist defenderVirologist, Virologist attackerVirologist) {
        if (attackerVirologist != null) {
            defenderVirologist.useAgent(agent, attackerVirologist);
        }
        life--;
        if (life == 0) {
            attackerVirologist.discard(this);
        }
    }

    /**
     * Apply and remove gloves effect on the virologist's defense strategy
     * @param virologist - the virologist who is using the gloves
     */
    public void applyEffect(Virologist virologist) {
        virologist.setDefenseStrategy(this);
    }

    /**
     * Remove the effect of the gloves
     * @param virologist - the virologist who is using the gloves
     */
    public void removeEffect(Virologist virologist) {
        virologist.removeDefenseStrategy(this);
    }
}
