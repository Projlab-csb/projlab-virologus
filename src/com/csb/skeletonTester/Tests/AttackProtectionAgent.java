package com.csb.skeletonTester.Tests;

import com.csb.agents.Forget;
import com.csb.agents.Protection;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class AttackProtectionAgent extends Test {
    public String getName() {
        return "Attack with Protection Agent";
    }

    public void runTest() {
        Virologist virologist = new Virologist();
        Virologist targetvirologist = new Virologist();
        Protection protection = new Protection();
        virologist.useAgent(protection, targetvirologist);
    }
}
