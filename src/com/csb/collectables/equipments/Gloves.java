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
        Tester.getInstance().functionStart("Gloves.applyEffect");
        Tester.getInstance().functionEnd("Gloves.applyEffect");
    }

    public void removeEffect(Virologist virologist) {
        Tester.getInstance().functionStart("Gloves.removeEffect");
        Tester.getInstance().functionEnd("Gloves.removeEffect");
    }

    @Override
    public void collectBy(Virologist virologist) {
        Tester.getInstance().functionStart("Gloves.collectBy");
        applyEffect(virologist);
        Tester.getInstance().functionEnd("Gloves.collectBy");
    }
}
