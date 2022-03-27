package com.csb.skeletonTester.Tests;

import com.csb.agents.Protection;
import com.csb.agents.VitusDance;
import com.csb.collectables.equipments.Cloak;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class AttackVitusDanceAgentWithCloak extends Test {
    /**
     * getName for the menu
     */
    public String getName() {
        return "Attack with VitusDance Agent when the attacked virologist wears Cloak";
    }
    /**
     * runTest for the start the function
     */
    public void runTest() {

        //Set the envirement for the test
        Virologist virologist = new Virologist();
        Virologist targetvirologist = new Virologist();
        Cloak c=new Cloak();
        c.applyEffect(targetvirologist);
        VitusDance vitusDance = new VitusDance();

        //run the function to be tested
        virologist.useAgent(vitusDance, targetvirologist);
    }
}

