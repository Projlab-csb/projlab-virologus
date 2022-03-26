package com.csb.skeletonTester.Tests;

import com.csb.collectables.equipments.Bag;
import com.csb.collectables.equipments.Cloak;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class DiscardCloak extends Test {
    public String getName() {
        return "Discard Cloak";
    }
    public void runTest() {
        Cloak cloak = new Cloak();
        Virologist virologist = new Virologist();
        virologist.discard(virologist, cloak);
    }
}
