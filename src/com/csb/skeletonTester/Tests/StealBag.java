package com.csb.skeletonTester.Tests;

import com.csb.agents.Paralyzed;
import com.csb.collectables.equipments.Bag;
import com.csb.collectables.equipments.Gloves;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

//Zoli todo
public class StealBag extends Test {

    /**
     * * getName for the menu
     */

    public String getName() {
        return "Steal Bag";
    }

    /**
     * runTest for the start the function
     */
    public void runTest() {
        //Set the envirement for the test
        Bag bag = new Bag();
        Virologist robbervirologist = new Virologist();
        Virologist targetvirologist = new Virologist();
        bag.collectBy(targetvirologist);
        Paralyzed paralyzedAgent = new Paralyzed();
        paralyzedAgent.applyEffect(targetvirologist);

        //run the function to be tested
        robbervirologist.steal(bag, targetvirologist);
    }
}