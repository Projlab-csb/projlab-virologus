package com.csb.agents;

import com.csb.skeletonTester.Tester;
import com.csb.strategies.DefenseStrategyInterface;
import com.csb.strategies.RoundRunStrategyInterface;
import com.csb.virologist.Virologist;

public class Protection extends Agent implements DefenseStrategyInterface {

    /**
     * apply the Protection effect on a virologist, now the virologist is protected from agents
     */
    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setDefenseStrategy(this);
        Tester.getInstance().functionEnd();
    }
    /**
     * decrease the time to live of the agent, when it reaches zero the agent will be removed
     */
    @Override
    public void decreaseTTL() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
    }
    /**
     * removes the Protection effect, now the virologist is not protected from agents
     */
    @Override
    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setDefenseStrategy(this);
        Tester.getInstance().functionEnd();
    }


    /**
     * this function handles the virologist's defense(protected or not protected)
     */
    @Override
    public void defense(Agent agent,Virologist attackedVirologist, Virologist attackerVirologist) {

    }
}
