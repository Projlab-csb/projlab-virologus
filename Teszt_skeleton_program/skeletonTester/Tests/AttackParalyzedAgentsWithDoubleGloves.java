package com.csb.skeletonTester.Tests;

import com.csb.agents.Paralyzed;
import com.csb.collectables.equipments.Gloves;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class AttackParalyzedAgentsWithDoubleGloves extends Test {

    /**
     * getName for the menu
     */
    public String getName() {
        return "Attack with Paralyzed Agent when both Virologist have Gloves";
    }

    /**
     * runTest for the start the function
     */
    public void runTest() {
        Virologist virologist = new Virologist();
        Virologist targetvirologist = new Virologist();
        Gloves g = new Gloves();
        Gloves g1 = new Gloves();
        g.applyEffect(targetvirologist);
        g1.applyEffect(virologist);
        Paralyzed paralyzed = new Paralyzed();
        virologist.useAgent(paralyzed, targetvirologist);
    }
}
