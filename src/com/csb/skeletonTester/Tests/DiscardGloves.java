package com.csb.skeletonTester.Tests;

import com.csb.collectables.equipments.Cloak;
import com.csb.collectables.equipments.Gloves;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class DiscardGloves extends Test {
    public String getName() {
        return "Discard Gloves";
    }
    public void runTest() {
        Gloves gloves = new Gloves();
        Virologist virologist = new Virologist();
        virologist.discard(gloves);
    }
}
