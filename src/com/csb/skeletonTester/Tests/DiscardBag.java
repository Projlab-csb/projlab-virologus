package com.csb.skeletonTester.Tests;

import com.csb.collectables.equipments.Bag;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class DiscardBag extends Test {
    public String getName() {
        return "Discard Bag";
    }
    public void runTest() {
        Bag bag = new Bag();
        Virologist virologist = new Virologist();
        virologist.discard(bag);
    }
}
