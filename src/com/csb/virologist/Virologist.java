package com.csb.virologist;

import com.csb.agents.Agent;
import com.csb.collectables.Collectable;
import com.csb.fields.Field;
import com.csb.skeletonTester.Tester;
import com.csb.strategies.DefenseStrategyInterface;

import java.util.ArrayList;

public class Virologist {
    private DefenseStrategyInterface defenseStrategy;
    private int inventorySize;

    public Virologist() {
        inventorySize = 0;
    }

    public void setDefenseStrategy(DefenseStrategyInterface defenseStrategy) {
        Tester.getInstance().functionStart("Virologist.setDefenseStrategy");
        this.defenseStrategy = defenseStrategy;
        Tester.getInstance().functionEnd("Virologist.setDefenseStrategy");
    }

    public void attack(Agent agent, Virologist virologist, Virologist targetVirologist) {
        Tester.getInstance().functionStart("Virologist.attack");
        defenseStrategy.defense();
        Tester.getInstance().functionEnd("Virologist.attack");
    }

    public void collect(Field field){
        Tester.getInstance().functionStart("Virologist.collect");

        ArrayList<Collectable> collectables = field.getCollectable();
        if(collectables != null) {
            collectables.get(0).collectBy(this);
        }

        Tester.getInstance().functionEnd("Virologist.collect");
    }

    public int getInventorySize() {
        Tester.getInstance().functionStart("Virologist.getInventorySize");
        Tester.getInstance().functionEnd("Virologist.getInventorySize");
        return inventorySize;
    }

    public void setInventorySize(int inventorySize) {
        Tester.getInstance().functionStart("Virologist.setInventorySize");
        this.inventorySize = inventorySize;
        Tester.getInstance().functionEnd("Virologist.setInventorySize");
    }
}
