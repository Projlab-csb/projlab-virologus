package com.csb.skeletonTester.Tests;

import com.csb.agents.Forget;
import com.csb.agents.VitusDance;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class AttackVitusDanceAgent extends Test {
    public String getName() {
        return "Attack with VitusDance Agent";
    }

    public void runTest() {
        Virologist virologist = new Virologist();
        Virologist targetvirologist = new Virologist();
        VitusDance vitusDance = new VitusDance();
        virologist.useAgent(vitusDance, targetvirologist);
    }
}