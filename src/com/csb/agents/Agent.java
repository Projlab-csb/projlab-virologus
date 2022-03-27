package com.csb.agents;

import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

public abstract class Agent {

    private int TTL;
    public abstract void applyEffect(Virologist virologist);
    public abstract void decreaseTTL(Virologist virologist);

    public abstract void removeEffect(Virologist virologist);

    public int getTTL() {
        return TTL;
    }

    public void setTTL(int TTL) { this.TTL = TTL; }
}
