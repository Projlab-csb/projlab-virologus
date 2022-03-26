package com.csb.skeletonTester.Tests;

import com.csb.collectables.equipments.Bag;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class DiscardBag extends Test {
    public String getName() {
        return "(Collect and) Discard Bag";
    }
    public void runTest() {
        Virologist virologist = new Virologist();
        Bag bag = new Bag();
        bag.collectBy(virologist);
        virologist.discard(bag);
    }
}
