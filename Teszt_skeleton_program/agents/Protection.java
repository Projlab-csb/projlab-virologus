package com.csb.agents;

import com.csb.skeletonTester.Tester;
import com.csb.strategies.DefenseStrategyInterface;
import com.csb.virologist.Virologist;

public class Protection extends Agent implements DefenseStrategyInterface {

    /**
     * Apply the Protection effect on a virologist, now the virologist is protected from agents
     * @param virologist the virologist to apply the effect on
     */
    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setDefenseStrategy(this);
        Tester.getInstance().functionEnd();
    }

    /**
     * Removes the Protection effect, now the virologist is not protected from agents
     * @param virologist the virologist to remove the effect from
     */
    @Override
    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.removeDefenseStrategy(this);
        Tester.getInstance().functionEnd();
    }

    /**
     * This function handles the virologist's defense(protected or not protected)
     * @param agent the virologist to handle the defense
     * @param defenderVirologist the virologist that is being attacked
     * @param attackerVirologist the virologist that is attacking
     */
    @Override
    public void defense(Agent agent, Virologist defenderVirologist, Virologist attackerVirologist) {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
    }
}
