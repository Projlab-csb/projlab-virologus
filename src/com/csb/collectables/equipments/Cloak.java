package com.csb.collectables.equipments;

import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

public class Cloak extends Equipment {

    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
    }

    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
    }

    @Override
    public void collectBy(Virologist virologist) {

    }
}
