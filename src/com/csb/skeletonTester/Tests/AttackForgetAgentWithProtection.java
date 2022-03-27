package com.csb.skeletonTester.Tests;

import com.csb.agents.Forget;
import com.csb.agents.Protection;
import com.csb.collectables.equipments.Cloak;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class AttackForgetAgentWithProtection extends Test {
    /**
     * getName for the menu
     */
    public String getName() {
        return "Attack with Forget Agent when the attacked virologist have Protection agent";
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
        Forget forget = new Forget();

        //run the function to be tested
        virologist.useAgent(forget, targetvirologist);
    }
}

