package com.csb.skeletonTester.Tests;

import com.csb.agents.Forget;
import com.csb.agents.Protection;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;
/**
 * Test for Attack with Protection Agent
 */
public class AttackProtectionAgent extends Test {
    /**
     * getName for the menu
     */
    public String getName() {
        return "Attack with Protection Agent";
    }
    /**
     * runTest for the start the function
     */
    public void runTest() {
        Virologist virologist = new Virologist();
        Virologist targetvirologist = new Virologist();
        Protection protection = new Protection();
        virologist.useAgent(protection, targetvirologist);
    }
}
