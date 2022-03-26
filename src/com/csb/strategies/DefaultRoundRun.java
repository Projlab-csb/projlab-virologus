package com.csb.strategies;

import com.csb.collectables.Collectable;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

public class DefaultRoundRun implements RoundRunStrategyInterface{
    public Collectable handleSteal(Collectable coll,Virologist targetVirologist){
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return null;
    }

    @Override
    public void RoundRun() {

    }
}
