package com.csb.skeletonTester.Tests;

import com.csb.agents.Forget;
import com.csb.agents.Protection;
import com.csb.collectables.equipments.Gloves;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class AttackProtectionAgentsWithDoubleGloves extends Test {

    /**
     * getName for the menu
     */
    public String getName() {
        return "Attack with Protection Agent when both Virologist have Gloves";
    }

    /**
     * runTest for the start the function
     */
    public void runTest() {
        Virologist virologist = new Virologist("bob");
        Virologist targetvirologist = new Virologist("james");
        Gloves g = new Gloves();
        Gloves g1 = new Gloves();
        g.applyEffect(targetvirologist);
        g1.applyEffect(virologist);
        Protection protection = new Protection();
        virologist.useAgent(protection, targetvirologist);
    }
}
