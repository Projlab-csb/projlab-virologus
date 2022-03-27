package com.csb.skeletonTester.Tests;
import com.csb.collectables.Collectable;
import com.csb.collectables.equipments.Cloak;
import com.csb.collectables.equipments.Equipment;
import com.csb.fields.Shelter;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

import java.util.ArrayList;

public class CollectCloakTest extends Test {
    @Override
    public String getName() {
        return "Collect Cloak";
    }


    /**
     * runTest for the start the function
     */
    @Override
    public void runTest() {

        //Set the envirement for the test
        Virologist virologist = new Virologist();
        Shelter shelter = new Shelter(new ArrayList<Collectable>(){
            {
                add(new Cloak());
                add(new Cloak());
            }
        });

        //run the function to be tested
        virologist.collect(shelter);
    }
}
