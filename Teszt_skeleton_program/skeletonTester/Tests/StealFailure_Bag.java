package com.csb.skeletonTester.Tests;

import com.csb.agents.Paralyzed;
import com.csb.collectables.equipments.Gloves;
import com.csb.skeletonTester.Test;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

public class StealFailure_Bag extends Test {

    /**
     * * getName for the menu
     */

    public String getName() {
        return "Steal Gloves, but it ends with failure";
    }

    /**
     * runTest for the start the function
     */
    public void runTest() {
        Tester.getInstance().turnOffLogging();
        //Set the envirement for the test
        Gloves gloves = new Gloves();
        Virologist robbervirologist = new Virologist();
        Virologist targetvirologist = new Virologist();
        gloves.collectBy(targetvirologist);

        Tester.getInstance().turnOnLogging();
        //run the function to be tested
        robbervirologist.steal(gloves, targetvirologist);
    }
}
