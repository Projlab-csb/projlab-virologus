package com.csb.skeletonTester.Tests;

import com.csb.agents.Paralyzed;
import com.csb.collectables.equipments.Cloak;
import com.csb.collectables.equipments.Gloves;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

//Zoli todo
public class StealCloak extends Test {

    /**
     * * getName for the menu
     */

    public String getName() {
        return "Steal Cloak";
    }

    /**
     * runTest for the start the function
     */
    public void runTest() {
        //Set the envirement for the test
        Cloak cloak = new Cloak();
        Virologist robbervirologist = new Virologist();
        Virologist targetvirologist = new Virologist();
        cloak.collectBy(targetvirologist);
        Paralyzed paralyzedAgent = new Paralyzed();
        paralyzedAgent.applyEffect(targetvirologist);

        //run the function to be tested
        robbervirologist.steal(cloak, targetvirologist);
    }
}
