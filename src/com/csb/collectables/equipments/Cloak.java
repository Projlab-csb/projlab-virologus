package com.csb.collectables.equipments;

import com.csb.agents.Agent;
import com.csb.skeletonTester.Tester;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.strategies.DefenseStrategyInterface;
import com.csb.virologist.Virologist;

public class Cloak extends Equipment implements DefenseStrategyInterface {

    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setDefenseStrategy(this);
        Tester.getInstance().functionEnd();
    }

    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setDefenseStrategy(null);
        Tester.getInstance().functionEnd();
    }

    @Override
    public void defense(Agent agent, Virologist attackedVirologist, Virologist attackerVirologist) {
        if (!UserInputHandler.getUserInputBoolean("Was the coat useful?")) {
            agent.applyEffect(attackedVirologist);
        }
    }
}
