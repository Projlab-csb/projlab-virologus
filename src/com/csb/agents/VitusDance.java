package com.csb.agents;

import com.csb.skeletonTester.Tester;
import com.csb.strategies.MoveStrategyInterface;
import com.csb.strategies.RoundRunStrategyInterface;
import com.csb.virologist.Virologist;

public class VitusDance extends Agent implements MoveStrategyInterface{

    /**
     * apply the VitudDance effect on a virologist, now the virologist moves random directions
     */
    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setmoveStrategy(this);
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
     * apply the VitudDance effect, now the virologist does not move random directions
     */
    @Override
    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setmoveStrategy(this);
        Tester.getInstance().functionEnd();
    }
    /**
     * this function handles the virologist's move(move random directions or not)
     */
    @Override
    public void move() {

    }

}
