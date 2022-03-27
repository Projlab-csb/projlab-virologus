package com.csb.skeletonTester.Tests;

import com.csb.agents.Forget;
import com.csb.agents.VitusDance;
import com.csb.collectables.equipments.Gloves;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class AttackVitusDanceAgentWithGloves extends Test {
    /**
     * getName for the menu
     */
    public String getName() {
        return "Attack with VitusDance Agent when the attacked virologist wears Gloves";
    }
    /**
     * runTest for the start the function
     */
    public void runTest() {

        //Set the envirement for the test
        Virologist virologist = new Virologist();
        Virologist targetvirologist = new Virologist();
        Gloves g=new Gloves();
        g.applyEffect(targetvirologist);
        VitusDance vitusDance = new VitusDance();

        //run the function to be tested
        virologist.useAgent(vitusDance, targetvirologist);
    }
}

