package com.csb.collectables.equipments;

import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

public class Cloak extends Equipment {

    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart("Gloves.applyEffect");
        Tester.getInstance().functionEnd("Gloves.applyEffect");
    }

    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart("Gloves.removeEffect");
        Tester.getInstance().functionEnd("Gloves.removeEffect");
    }

    @Override
    public void collectBy(Virologist virologist) {

    }
}
