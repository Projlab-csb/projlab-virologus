package com.csb.agents;

import com.csb.collectables.Collectable;
import com.csb.strategies.RoundRunStrategyInterface;
import com.csb.virologist.Virologist;

public class Paralyzed extends Agent implements RoundRunStrategyInterface {
    @Override
    public void applyEffect(Virologist virologist) {

    }

    @Override
    public void decreaseTTL() {

    }

    @Override
    public void removeEffect(Virologist virologist) {

    }

    @Override
    public Collectable handleSteal(Collectable coll, Virologist robberVirologist, Virologist targetVirologist) {
        coll.discard(targetVirologist);
        return coll;
    }

    @Override
    public void RoundRun() {

    }
}
