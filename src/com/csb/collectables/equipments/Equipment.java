package com.csb.collectables.equipments;

import com.csb.collectables.Collectable;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

public abstract class Equipment implements Collectable {

    public abstract void applyEffect(Virologist virologist);

    public abstract void removeEffect(Virologist virologist);

    public void collectBy(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.addEquipment(this);
        applyEffect(virologist);
        Tester.getInstance().functionEnd();
    }

    public void discard(Virologist virologist) {
        Tester.getInstance().functionStart();
        removeEffect(virologist);
        virologist.removeEquipment(this);
        Tester.getInstance().functionEnd();
    }
}
