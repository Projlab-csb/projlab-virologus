package com.csb.agents;

import com.csb.collectables.Collectable;
import com.csb.skeletonTester.Tester;
import com.csb.strategies.MoveStrategyInterface;
import com.csb.strategies.RoundRunStrategyInterface;
import com.csb.virologist.Virologist;



public class Paralyzed extends Agent implements RoundRunStrategyInterface {

    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setRoundRunStrategy(this);
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
        virologist.setRoundRunStrategy(this);
        Tester.getInstance().functionEnd();
    }

    @Override
    public Collectable handleSteal(Collectable coll, Virologist robberVirologist, Virologist targetVirologist) {
        Tester.getInstance().functionStart();
        coll.discard(targetVirologist);
        Tester.getInstance().functionEnd();
        return coll;
    }

    @Override
    public void RoundRun() {

    }
}
