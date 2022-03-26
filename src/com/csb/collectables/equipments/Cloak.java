package com.csb.collectables.equipments;

import com.csb.skeletonTester.Tester;
import com.csb.strategies.ProtectionStrategyInterface;
import com.csb.virologist.Virologist;

public class Cloak extends Equipment implements ProtectionStrategyInterface {

    public void protection() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
    }

    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        protection();
        Tester.getInstance().functionEnd();
    }

    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        protection();
        Tester.getInstance().functionEnd();
    }

    @Override
    public void discard(Virologist virologist) {
        Tester.getInstance().functionStart();
        removeEffect(virologist);
        Tester.getInstance().functionEnd();
    }

    @Override
    public void collectBy(Virologist virologist) {
        Tester.getInstance().functionStart();
        applyEffect(virologist);
        Tester.getInstance().functionEnd();
    }
}
