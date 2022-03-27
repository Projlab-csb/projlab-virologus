package com.csb.skeletonTester.Tests;

import com.csb.collectables.equipments.Bag;
import com.csb.collectables.equipments.Cloak;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class DiscardCloak extends Test {
    public String getName() {
        return "(Collect and) Discard Cloak";
    }

    /**
     * runTest for the start the function
     */
    public void runTest() {

        //Set the envirement for the test
        Cloak cloak = new Cloak();
        Virologist virologist = new Virologist();
        cloak.collectBy(virologist);

        //run the function to be tested
        virologist.discard(cloak);
    }
}
