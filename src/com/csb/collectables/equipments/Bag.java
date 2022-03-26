package com.csb.collectables.equipments;

import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

public class Bag extends Equipment {

    private int extraInventorySize = 0;

    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setInventorySize(virologist.getInventorySize() + getExtraInventorySize());
        Tester.getInstance().functionEnd();
    }

    @Override
    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setInventorySize(virologist.getInventorySize() - getExtraInventorySize());
        Tester.getInstance().functionEnd();
    }

    private int getExtraInventorySize() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return extraInventorySize;
    }
}
