package com.csb.agents;

import com.csb.skeletonTester.Tester;
import com.csb.strategies.DefenseStrategyInterface;
import com.csb.strategies.RoundRunStrategyInterface;
import com.csb.virologist.Virologist;

public class Protection extends Agent {

    private DefenseStrategyInterface defense;
    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setDefenseStrategy(this.getStrategy());
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
        virologist.setDefenseStrategy(defense);
        Tester.getInstance().functionEnd();
    }

    public DefenseStrategyInterface getStrategy(){
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return defense;
    }
}
