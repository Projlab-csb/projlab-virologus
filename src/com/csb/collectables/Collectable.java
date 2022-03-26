package com.csb.collectables;

import com.csb.virologist.Virologist;

/**
 * Interface for collectable items functions
 */
public interface Collectable {
    void collectBy(Virologist virologist);
    void discard(Virologist virologist);
}
