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
        virologist.setRoundRunStrategy(this);
    }

    /**
     * Remove the Paralyzed effect, now the virologist can do things
     * @param virologist the virologist to remove the effect from
     */
    @Override
    public void removeEffect(Virologist virologist) {
        virologist.removeRoundRunStrategy(this);
    }

    /**
     * Other virologist steal collectables from the paralyed virologist
     * @param coll the collectable to steal
     * @param targetVirologist the virologist that steals the collectable
     */
    @Override
    public Collectable handleSteal(Collectable coll, Virologist targetVirologist) {
        coll.discard(targetVirologist);
        return coll;
    }

    /**
     * This function handles the virologist's round(can or can't do things)
     * @param virologist the virologist that is doing the round
     */
    @Override
    public void RoundRun(Virologist virologist) {
        System.out.println("The virologist is paralyzed, you can do nothing to prevent your impending doom (at least for the time being)!");
        //The virologist can't do anything, hence the function is empty
    }
}
