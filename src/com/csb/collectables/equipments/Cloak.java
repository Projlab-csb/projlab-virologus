package com.csb.collectables.equipments;

import com.csb.agents.Agent;
import com.csb.skeletonTester.Tester;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.strategies.DefenseStrategyInterface;
import com.csb.virologist.Virologist;

/**
 * Class for the Cloak objects. With a Cloak the attacks towards the virologost can fail, with a gicen percentage
 */
public class Cloak extends Equipment implements DefenseStrategyInterface {

    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setDefenseStrategy(this);
        Tester.getInstance().functionEnd();
    }

    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setDefenseStrategy(virologist.getDefaultDefenseStrategy());
        Tester.getInstance().functionEnd();
    }

    @Override
    public void defense(Agent agent, Virologist attackedVirologist, Virologist attackerVirologist) {
        Tester.getInstance().functionStart();
        if (!UserInputHandler.getUserInputBoolean("Was the coat useful?")) {
            agent.applyEffect(attackedVirologist);
        }
        Tester.getInstance().functionEnd();
    }
}
