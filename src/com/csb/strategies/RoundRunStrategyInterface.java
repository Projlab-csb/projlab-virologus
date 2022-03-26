package com.csb.strategies;

import com.csb.collectables.Collectable;
import com.csb.virologist.Virologist;

public interface RoundRunStrategyInterface {
    public Collectable handleSteal(Collectable coll, Virologist robberVirologist, Virologist targetVirologist);
    public void RoundRun();

}
