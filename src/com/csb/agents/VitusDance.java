package com.csb.agents;

import com.csb.skeletonTester.Tester;
import com.csb.strategies.MoveStrategyInterface;
import com.csb.virologist.Virologist;

public class VitusDance extends Agent implements MoveStrategyInterface {

    /**
     * Apply the VitusDance effect on a virologist, now the virologist moves random directions
     */
    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setMoveStrategy(this);
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
     * Removes the VitusDance effect, now the virologist does not move random directions
     */
    @Override
    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setMoveStrategy(this);
        Tester.getInstance().functionEnd();
    }

    /**
     * This function handles the virologist's move(move random directions or not)
     */
    @Override
    public void move() {
        Tester.getInstance().functionStart();

        Tester.getInstance().functionEnd();
    }
}
