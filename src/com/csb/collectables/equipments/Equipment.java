package com.csb.collectables.equipments;

import com.csb.collectables.Collectable;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;
import java.io.Serializable;
import javax.swing.*;

public abstract class Equipment implements Collectable, Serializable {

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
        virologist.addEquipment(this);
        applyEffect(virologist);
        //TODO: maybe this don't write out the exact name, we expect the inherited class
        System.out.println(virologist.getName() + " has collected a(n) " + getClass().getSimpleName());
    }

    /**
     * Handles the event when a virologist discards this equipment
     * @param virologist the virologist that discards
     */
    public void discard(Virologist virologist) {
        removeEffect(virologist);
        virologist.removeEquipment(this);
        System.out.println(virologist.getName() + " has discarded a(n) " + getClass().getSimpleName());
    }
}
