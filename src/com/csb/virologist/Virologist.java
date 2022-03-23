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
        Tester.getInstance().functionStart();
        this.defenseStrategy = defenseStrategy;
        Tester.getInstance().functionEnd();
    }

    public void attack(Agent agent, Virologist virologist, Virologist targetVirologist) {
        Tester.getInstance().functionStart();
        defenseStrategy.defense();
        Tester.getInstance().functionEnd();
    }

    public void collect(Field field){
        Tester.getInstance().functionStart();

        ArrayList<Collectable> collectables = field.getCollectable();
        if(collectables != null) {
            collectables.get(0).collectBy(this);
        }

        Tester.getInstance().functionEnd();
    }

    public int getInventorySize() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return inventorySize;
    }

    public void setInventorySize(int inventorySize) {
        Tester.getInstance().functionStart();
        this.inventorySize = inventorySize;
        Tester.getInstance().functionEnd();
    }
}
