package com.csb.skeletonTester.Tests;

import com.csb.agents.Forget;
import com.csb.agents.VitusDance;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

/**
 * Test for Attack with VitusDance Agent
 */
public class AttackVitusDanceAgent extends Test {

    /**
     * getName for the menu
     */
    public String getName() {
        return "Attack with VitusDance Agent";
    }

    /**
     * runTest for the start the function
     */
    public void runTest() {
        Virologist virologist = new Virologist("bob");
        Virologist targetvirologist = new Virologist("james");
        VitusDance vitusDance = new VitusDance();
        virologist.useAgent(vitusDance, targetvirologist);
    }
}
