package com.csb.agents;

import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

public abstract class Agent {
    public abstract void applyEffect(Virologist virologist);
    public abstract void decreaseTTL();

    public abstract void removeEffect(Virologist virologist);
}
