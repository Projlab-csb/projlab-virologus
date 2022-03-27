package com.csb.skeletonTester.Tests;

import com.csb.collectables.equipments.Bag;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class DiscardBag extends Test {
    public String getName() {
        return "(Collect and) Discard Bag";
    }

    /**
     * runTest for the start the function
     */
    public void runTest() {

        //Set the envirement for the test
        Virologist virologist = new Virologist();
        Bag bag = new Bag();
        bag.collectBy(virologist);

        //run the function to be tested
        virologist.discard(bag);
    }
}
