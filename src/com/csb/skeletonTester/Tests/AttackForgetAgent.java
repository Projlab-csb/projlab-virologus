package com.csb.skeletonTester.Tests;

import com.csb.agents.Forget;
import com.csb.collectables.equipments.Cloak;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class AttackForgetAgent extends Test {
    public String getName() {
        return "Attack with Forget Agent";
    }

    public void runTest() {
        Virologist virologist = new Virologist();
        Virologist targetvirologist = new Virologist();
        Forget forget = new Forget();
        virologist.useAgent(forget, targetvirologist);
    }
}
