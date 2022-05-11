package com.csb.strategies;

import com.csb.collectables.Collectable;
import com.csb.virologist.Virologist;

/**
 * Interface for the functions of the Round control and steal handling strategy
 */
public interface RoundRunStrategyInterface {
    Collectable handleSteal(Collectable coll, Virologist robber);

    //public void RoundRun(Virologist virologist);

    public void roundRun();

    public void move(Virologist virologist);

    public void murder(Virologist virologist);

    public void steal(Virologist virologist);

    public void discard(Virologist virologist);

    public void createAgent(Virologist virologist);

    public void useAgent(Virologist virologist);

    public void collect(Virologist virologist);
}
