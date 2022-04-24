package com.csb.skeletonTester.Tests;

import com.csb.agents.Paralyzed;
import com.csb.agents.Protection;
import com.csb.collectables.equipments.Cloak;
import com.csb.skeletonTester.Test;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

public class AttackProtectionAgentWithCloak extends Test {

    /**
     * getName for the menu
     */
    public String getName() {
        return "Attack with Protection Agent when the attacked virologist wears Cloak";
    }

    /**
     * runTest for the start the function
     */
    public void runTest() {
        Tester.getInstance().turnOffLogging();

        //Set the envirement for the test
        Virologist virologist = new Virologist("bob");
        Virologist targetvirologist = new Virologist("james");
        Cloak c = new Cloak();
        c.applyEffect(targetvirologist);
        Protection protection = new Protection();

        Tester.getInstance().turnOnLogging();

        //run the function to be tested
        virologist.useAgent(protection, targetvirologist);
    }
}
