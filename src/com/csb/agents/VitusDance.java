package com.csb.agents;

import com.csb.skeletonTester.Tester;
import com.csb.strategies.MoveStrategyInterface;
import com.csb.strategies.RoundRunStrategyInterface;
import com.csb.virologist.Virologist;

public class VitusDance extends Agent {


    private MoveStrategyInterface move;
    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setmoveStrategy(this.getStrategy());
        Tester.getInstance().functionEnd();
    }

    @Override
    public void decreaseTTL() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
    }

    @Override
    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setmoveStrategy(move);
        Tester.getInstance().functionEnd();
    }

    public MoveStrategyInterface getStrategy(){
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return move;
    }
}
