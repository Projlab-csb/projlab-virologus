package com.csb.agents;

import com.csb.skeletonTester.Tester;
import com.csb.strategies.MoveStrategyInterface;
import com.csb.strategies.RoundRunStrategyInterface;
import com.csb.virologist.Virologist;

public class VitusDance extends Agent {


    private MoveStrategyInterface move;
    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.moveStrategy(this.getStrategy());
        Tester.getInstance().functionEnd();
    }
    public MoveStrategyInterface getStrategy(){
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return move;
    }
}
