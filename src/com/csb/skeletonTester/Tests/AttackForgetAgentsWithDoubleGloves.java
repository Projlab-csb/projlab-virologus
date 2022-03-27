package com.csb.skeletonTester.Tests;

import com.csb.agents.Forget;
import com.csb.agents.Paralyzed;
import com.csb.collectables.equipments.Gloves;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class AttackForgetAgentsWithDoubleGloves extends Test {

    /**
     * getName for the menu
     */
    public String getName() {
        return "Attack with Forget Agent when both Virologist have Gloves";
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
        Forget forget = new Forget();
        virologist.useAgent(forget, targetvirologist);
    }
}
