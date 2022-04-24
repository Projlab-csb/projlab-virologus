package com.csb.skeletonTester.Tests;

import com.csb.collectables.equipments.Cloak;
import com.csb.collectables.equipments.Gloves;
import com.csb.skeletonTester.Test;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

public class DiscardGloves extends Test {

    public String getName() {
        return "Discard Gloves";
    }

    /**
     * runTest for the start the function
     */
    public void runTest() {
        Tester.getInstance().turnOffLogging();
        //Set the envirement for the test
        Gloves gloves = new Gloves();
        Virologist virologist = new Virologist("bob");
        gloves.collectBy(virologist);

        Tester.getInstance().turnOnLogging();
        //run the function to be tested
        virologist.discard(gloves);
    }
}
