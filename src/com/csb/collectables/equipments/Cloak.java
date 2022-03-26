package com.csb.collectables.equipments;

import com.csb.skeletonTester.Tester;
import com.csb.strategies.DefenseStrategyInterface;
import com.csb.virologist.Virologist;

public class Cloak extends Equipment implements DefenseStrategyInterface {

    public void applyEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setDefenseStrategy(this);
        Tester.getInstance().functionEnd();
    }

    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setDefenseStrategy(this);
        Tester.getInstance().functionEnd();
    }

    @Override
    public void collectBy(Virologist virologist) {
        Tester.getInstance().functionStart();
        applyEffect(virologist);
        Tester.getInstance().functionEnd();
    }

    @Override
    public void discard(Virologist virologist) {

    }

    @Override
    public void defense() {

    }
}
