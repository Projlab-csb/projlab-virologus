package com.csb.strategies;

import com.csb.collectables.Collectable;
import com.csb.virologist.Virologist;

/**
 * Interface for the functions of the Round control and steal handling strategy
 */
public interface RoundRunStrategyInterface {
    Collectable handleSteal(Collectable coll, Virologist robber);

    public void RoundRun(Virologist virologist);
}
