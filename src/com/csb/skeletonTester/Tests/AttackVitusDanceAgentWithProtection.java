package com.csb.skeletonTester.Tests;

import com.csb.agents.Paralyzed;
import com.csb.agents.Protection;
import com.csb.agents.VitusDance;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class AttackVitusDanceAgentWithProtection extends Test {
    /**
     * getName for the menu
     */
    public String getName() {
        return "Attack with VitusDance Agent when the attacked virologist have Protection agent";
    }
    /**
     * runTest for the start the function
     */
    public void runTest() {

        //Set the envirement for the test
        Virologist virologist = new Virologist();
        Virologist targetvirologist = new Virologist();
        Protection p=new Protection();
        p.applyEffect(targetvirologist);
        VitusDance vitusDance = new VitusDance();

        //run the function to be tested
        virologist.useAgent(vitusDance, targetvirologist);
    }
}

