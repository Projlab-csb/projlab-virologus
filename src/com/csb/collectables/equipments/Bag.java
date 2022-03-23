package com.csb.collectables.equipments;

import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

public class Bag extends Equipment{

    private int extraInventorySize = 0;

    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart("Bag.applyEffect");
        virologist.setInventorySize(virologist.getInventorySize() + extraInventorySize);
        Tester.getInstance().functionEnd("Bag.applyEffect");
    }

    @Override
    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart("Bag.removeEffect");
        virologist.setInventorySize(virologist.getInventorySize() - extraInventorySize);
        Tester.getInstance().functionEnd("Bag.removeEffect");
    }

    @Override
    public void collectBy(Virologist virologist) {
        Tester.getInstance().functionStart("Bag.collectBy");
        applyEffect(virologist);
        Tester.getInstance().functionEnd("Bag.collectBy");
    }
}
