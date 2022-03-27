package com.csb.strategies;

import com.csb.virologist.Virologist;

/**
 * Interface for the functions of the moving strategy
 */
public interface MoveStrategyInterface {
    public void move(Virologist virologist, int nextTileIndex);
}
