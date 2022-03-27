package com.csb.agents;

import com.csb.collectables.Collectable;
import com.csb.skeletonTester.Tester;
import com.csb.strategies.RoundRunStrategyInterface;
import com.csb.virologist.Virologist;

public class Paralyzed extends Agent implements RoundRunStrategyInterface {

    /**
     * Apply the Paralyzed effect on a virologist, now the virologist cant do anything
     */
    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setRoundRunStrategy(this);
        Tester.getInstance().functionEnd();
    }

    /**
     * Decrease the time to live of the agent, when it reaches zero the agent will be removed
     */
    @Override
    public void decreaseTTL(Virologist virologist) {
        Tester.getInstance().functionStart();
        if (getTTL() == 0) {
            removeEffect(virologist);
        } else {
            setTTL(getTTL() - 1);
        }
        Tester.getInstance().functionEnd();
    }

    /**
     * Remove the Paralyzed effect, now the virologist can do things
     */
    @Override
    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.removeRoundRunStrategy(this);
        Tester.getInstance().functionEnd();
    }

    /**
     * Other virologist steal collectables from the paralyed virologist
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
     */
    @Override
    public void RoundRun(Virologist virologist) {
        Tester.getInstance().functionStart();

        Tester.getInstance().functionEnd();
    }
}
