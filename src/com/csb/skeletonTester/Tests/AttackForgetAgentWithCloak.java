package com.csb.skeletonTester.Tests;

import com.csb.agents.Forget;
import com.csb.collectables.equipments.Cloak;
import com.csb.collectables.equipments.Gloves;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

public class AttackForgetAgentWithCloak extends Test {
    /**
     * getName for the menu
     */
    public String getName() {
        return "Attack with Forget Agent when the attacked virologist wears Cloak";
    }
    /**
     * runTest for the start the function
     */
    public void runTest() {

        //Set the environment for the test
        Virologist virologist = new Virologist();
        Virologist targetvirologist = new Virologist();
        Cloak c=new Cloak();
        c.applyEffect(targetvirologist);
        Forget forget = new Forget();

        //run the function to be tested
        virologist.useAgent(forget, targetvirologist);
    }
}

