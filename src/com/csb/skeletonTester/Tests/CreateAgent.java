package com.csb.skeletonTester.Tests;

import com.csb.agents.Protection;
import com.csb.virologist.Virologist;

public class CreateVitusDanceAgent {
    public String getName() {
        return "Create VitusDanceAgent";
    }
    public void runTest() {
        Virologist virologist = new Virologist();
        Protection protection = new Protection();
        virologist.useAgent(protection, targetvirologist);
    }
}
