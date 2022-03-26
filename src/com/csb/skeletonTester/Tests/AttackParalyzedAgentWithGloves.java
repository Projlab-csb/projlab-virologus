package com.csb.skeletonTester.Tests;

import com.csb.agents.Paralyzed;
import com.csb.collectables.equipments.Gloves;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class AttackParalyzedAgentWithGloves extends Test {
    /**
     * getName for the menu
     */
    public String getName() {
        return "Attack with Paralyzed Agent when the attacked virologist wears Gloves";
    }
    /**
     * runTest for the start the function
     */
    public void runTest() {
        Virologist virologist = new Virologist();
        Virologist targetvirologist = new Virologist();
        Gloves g=new Gloves();
        g.applyEffect(targetvirologist);
        Paralyzed paralyzed = new Paralyzed();
        virologist.useAgent(paralyzed, targetvirologist);
    }
}

