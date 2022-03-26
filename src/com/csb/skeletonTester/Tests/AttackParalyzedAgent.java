package com.csb.skeletonTester.Tests;

import com.csb.agents.Forget;
import com.csb.agents.Paralyzed;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class AttackParalyzedAgent extends Test {
    public String getName() {
        return "Attack with Paralyzed Agent";
    }

    public void runTest() {
        Virologist virologist = new Virologist();
        Virologist targetvirologist = new Virologist();
        Paralyzed paralyzed = new Paralyzed();
        virologist.useAgent(paralyzed, targetvirologist);
    }
}
