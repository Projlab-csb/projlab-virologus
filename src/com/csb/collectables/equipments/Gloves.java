package com.csb.collectables.equipments;

import com.csb.agents.Agent;
import com.csb.skeletonTester.TestInterface;
import com.csb.skeletonTester.Tester;
import com.csb.strategies.DefenseStrategyInterface;
import com.csb.virologist.Virologist;

public class Gloves extends Equipment implements DefenseStrategyInterface {

    @Override
    public void defense(Agent agent,Virologist attackedVirologist, Virologist attackerVirologist) {
        Tester.getInstance().functionStart();
        attackedVirologist.useAgent(agent,attackerVirologist);
        Tester.getInstance().functionEnd();
    }

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


}
