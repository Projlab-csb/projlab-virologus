package com.csb.strategies;

import com.csb.collectables.Collectable;
import com.csb.virologist.Virologist;

public interface RoundRunStrategyInterface {
    Collectable handleSteal(Collectable coll, Virologist targetVirologist);
    public void RoundRun();

}
