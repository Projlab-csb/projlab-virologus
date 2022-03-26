package com.csb.skeletonTester.Tests;
import com.csb.collectables.Collectable;
import com.csb.collectables.equipments.Gloves;
import com.csb.fields.Shelter;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

import java.util.ArrayList;

public class CollectGlovesTest extends Test {

    @Override
    public String getName() {
        return "Collect Gloves";
    }

    @Override
    public void runTest() {
        Virologist virologist = new Virologist();
        Shelter shelter = new Shelter(new ArrayList<Collectable>(){
            {
                add(new Gloves());
                add(new Gloves());
            }
        });
        virologist.collect(shelter);
    }

}