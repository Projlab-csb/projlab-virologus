package com.csb.skeletonTester.Tests;

import com.csb.collectables.equipments.Cloak;
import com.csb.collectables.equipments.Gloves;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class DiscardGloves extends Test {
    public String getName() {
        return "(Collect and) Discard Gloves";
    }

    /**
     * runTest for the start the function
     */
    public void runTest() {

        //Set the envirement for the test
        Gloves gloves = new Gloves();
        Virologist virologist = new Virologist();
        gloves.collectBy(virologist);

        //run the function to be tested
        virologist.discard(gloves);
    }
}
