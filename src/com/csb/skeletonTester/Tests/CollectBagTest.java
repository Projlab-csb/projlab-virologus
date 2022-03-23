package com.csb.skeletonTester.Tests;

import com.csb.collectables.Collectable;
import com.csb.collectables.equipments.Bag;
import com.csb.fields.Shelter;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

import java.util.ArrayList;

public class CollectBagTest extends Test {

    @Override
    public String getName() {
        return "Collect Bag";
    }

    @Override
    public void runTest() {
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
