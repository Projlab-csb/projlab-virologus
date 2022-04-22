package com.csb.strategies;

import com.csb.virologist.Virologist;

/**
 * Interface for the functions of the moving strategy
 */
public interface MurderStrategyInterface {
    public void murder(Virologist murderervirologist, Virologist murderedvirologist);
}
