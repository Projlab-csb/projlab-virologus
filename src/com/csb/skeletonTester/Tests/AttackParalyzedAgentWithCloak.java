package com.csb.skeletonTester.Tests;

import com.csb.agents.Forget;
import com.csb.agents.Paralyzed;
import com.csb.collectables.equipments.Cloak;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class AttackParalyzedAgentWithCloak extends Test {

    /**
     * getName for the menu
     */
    public String getName() {
        return "Attack with Paralyzed Agent when the attacked virologist wears Cloak";
    }

    /**
     * runTest for the start the function
     */
    public void runTest() {
        //Set the envirement for the test
        Virologist virologist = new Virologist("bob");
        Virologist targetvirologist = new Virologist("james");
        Cloak c = new Cloak();
        c.applyEffect(targetvirologist);
        Paralyzed paralyzed = new Paralyzed();

        //run the function to be tested
        virologist.useAgent(paralyzed, targetvirologist);
    }
}
