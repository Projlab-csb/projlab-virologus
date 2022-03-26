package com.csb.collectables;

import com.csb.virologist.Virologist;

public interface Collectable {
    void collectBy(Virologist virologist);
    void discard(Virologist virologist);
}
