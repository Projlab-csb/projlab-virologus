package com.csb.agents;

import com.csb.skeletonTester.Tester;
import com.csb.strategies.RoundRunStrategyInterface;
import com.csb.virologist.Virologist;



public class Paralyzed extends Agent {

    private RoundRunStrategyInterface roundRound;

    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setRoundRunStrategy(this.getStrategy());
        Tester.getInstance().functionEnd();
    }
    public RoundRunStrategyInterface getStrategy(){
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return roundRound;
    }
}
