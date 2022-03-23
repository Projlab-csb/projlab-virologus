package com.csb.collectables.equipments;

import com.csb.skeletonTester.TestInterface;
import com.csb.skeletonTester.Tester;
import com.csb.strategies.DefenseStrategyInterface;
import com.csb.virologist.Virologist;

public class Gloves extends Equipment implements DefenseStrategyInterface {

    @Override
    public void defense() {
        System.out.println("Gloves custom implementation of defend()");
    }

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
        Tester.getInstance().functionStart();
        applyEffect(virologist);
        Tester.getInstance().functionEnd();
    }
}
