package com.csb.skeletonTester.Tests;

import com.csb.collectables.Collectable;
import com.csb.collectables.equipments.Bag;
import com.csb.fields.Shelter;
import com.csb.skeletonTester.TestInterface;
import com.csb.virologist.Virologist;

import java.util.ArrayList;

public class CollectBagTest implements TestInterface {

    @Override
    public String getName() {
        return "Collect Bag";
    }

    @Override
    public void runTest() {
        System.out.println("Collecting bag test");
        Virologist virologist = new Virologist();
        Shelter shelter = new Shelter(new ArrayList<Collectable>(){
            {
                add(new Bag());
                add(new Bag());
            }
        });

        virologist.collect(shelter);
    }
}
