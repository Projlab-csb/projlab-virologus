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

    @Override
    public void runTest() {
        Virologist virologist = new Virologist();
        Shelter shelter = new Shelter(new ArrayList<Collectable>(){
            {
                add(new Cloak());
                add(new Cloak());
            }
        });
        virologist.collect(shelter);
    }
}
