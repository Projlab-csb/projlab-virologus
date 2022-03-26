package com.csb.skeletonTester.Tests;

import com.csb.agents.Paralyzed;
import com.csb.collectables.equipments.Cloak;
import com.csb.collectables.equipments.Gloves;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class StealGloves extends Test {
    public String getName() {
        return "Steal Gloves";
    }
    public void runTest() {
        Gloves gloves = new Gloves();
        Virologist robbervirologist = new Virologist();
        Virologist targetvirologist = new Virologist();
        gloves.collectBy(targetvirologist);
        Paralyzed paralyzedAgent=new Paralyzed();
        paralyzedAgent.applyEffect(targetvirologist);
        robbervirologist.steal(gloves, targetvirologist);

    }
}
