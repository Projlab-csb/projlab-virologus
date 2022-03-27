package com.csb.collectables.equipments;

import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

/**
 * Class for the Bag objects Bag let virologists carrying more matter
 */
public class Bag extends Equipment {

    private int extraInventorySize = 10;

    /**
     *add plus space to the owners inventory
     * @param virologist - his inventory will be bigger til owns the bag
     */
    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setInventorySize(virologist.getInventorySize() + getExtraInventorySize());
        Tester.getInstance().functionEnd();
    }

    /**
     * Remove inventory bonus
     * @param virologist - his inventory's size must be reduced
     */
    @Override
    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setInventorySize(virologist.getInventorySize() - getExtraInventorySize());
        Tester.getInstance().functionEnd();
    }

    /**
     *getter for extrainventorysize
     */
    private int getExtraInventorySize() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return extraInventorySize;
    }
}
