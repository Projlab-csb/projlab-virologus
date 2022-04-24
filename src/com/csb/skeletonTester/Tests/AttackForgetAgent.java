package com.csb.skeletonTester.Tests;

import com.csb.agents.Forget;
import com.csb.collectables.equipments.Cloak;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

/**
 * Test for Attack with Forget Agent
 */
public class AttackForgetAgent extends Test {

    /**
     * getName for the menu
     */
    public String getName() {
        return "Attack with Forget Agent";
    }

    /**
     * runTest for the start the function
     */
    public void runTest() {
        Virologist virologist = new Virologist("bob");
        Virologist targetvirologist = new Virologist("james");
        Forget forget = new Forget();
        virologist.useAgent(forget, targetvirologist);
    }
}
