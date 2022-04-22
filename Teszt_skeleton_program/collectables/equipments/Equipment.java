package com.csb.collectables.equipments;

import com.csb.collectables.Collectable;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

public abstract class Equipment implements Collectable {

    /**
     * Applies the effect of this equipment to the virologist
     * @param virologist the virologist that gets the effect
     */
    public abstract void applyEffect(Virologist virologist);

    /**
     * Removes the effect of this equipment from the virologist
     * @param virologist the virologist that gets the effect
     */
    public abstract void removeEffect(Virologist virologist);

    /**
     * Handles the event when a virologist obtains this equipment
     * @param virologist the collector virologist
     */
    public void collectBy(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.addEquipment(this);
        applyEffect(virologist);
        Tester.getInstance().functionEnd();
    }

    /**
     * Handles the event when a virologist discards this equipment
     * @param virologist the virologist that discards
     */
    public void discard(Virologist virologist) {
        Tester.getInstance().functionStart();
        removeEffect(virologist);
        virologist.removeEquipment(this);
        Tester.getInstance().functionEnd();
    }
}
