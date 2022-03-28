package com.csb.agents;

import com.csb.collectables.Collectable;
import com.csb.skeletonTester.Tester;
import com.csb.strategies.RoundRunStrategyInterface;
import com.csb.virologist.Virologist;

public class Paralyzed extends Agent implements RoundRunStrategyInterface {

    /**
     * Apply the Paralyzed effect on a virologist, now the virologist cant do anything
     * @param virologist the virologist to apply the effect on
     */
    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setRoundRunStrategy(this);
        Tester.getInstance().functionEnd();
    }

    /**
     * Remove the Paralyzed effect, now the virologist can do things
     * @param virologist the virologist to remove the effect from
     */
    @Override
    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.removeRoundRunStrategy(this);
        Tester.getInstance().functionEnd();
    }

    /**
     * Other virologist steal collectables from the paralyed virologist
     * @param coll the collectable to steal
     * @param targetVirologist the virologist that steals the collectable
     */
    @Override
    public Collectable handleSteal(Collectable coll, Virologist targetVirologist) {
        Tester.getInstance().functionStart();
        coll.discard(targetVirologist);
        Tester.getInstance().functionEnd();
        return coll;
    }

    /**
     * This function handles the virologist's round(can or can't do things)
     * @param virologist the virologist that is doing the round
     */
    @Override
    public void RoundRun(Virologist virologist) {
        Tester.getInstance().functionStart();

        Tester.getInstance().functionEnd();
    }
}
