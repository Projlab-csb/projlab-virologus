package com.csb.virologist;

import com.csb.agents.Agent;
import com.csb.agents.Forget;
import com.csb.collectables.Collectable;
import com.csb.fields.Field;
import com.csb.skeletonTester.Tester;
import com.csb.strategies.DefenseStrategyInterface;
import com.csb.strategies.MoveStrategyInterface;
import com.csb.strategies.RoundRunStrategyInterface;

import java.util.ArrayList;

public class Virologist {

    private DefenseStrategyInterface defenseStrategy;
    private RoundRunStrategyInterface roundRunStrategy;
    private MoveStrategyInterface moveStrategy;
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
        agent.applyEffect(targetVirologist);
        Tester.getInstance().functionEnd();
    }

    public void setAgents() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
    }
    public void collect(Field field) {
        Tester.getInstance().functionStart();

        ArrayList<Collectable> collectables = field.getCollectable();
        if (collectables != null) {
            collectables.get(0).collectBy(this);
        }

        Tester.getInstance().functionEnd();
    }
    public void useAgent(Agent agent, Virologist targetVirologist) {
        Tester.getInstance().functionStart();

        targetVirologist.attack(agent, this,  targetVirologist);

        Tester.getInstance().functionEnd();
    }

    public void setRoundRunStrategy(RoundRunStrategyInterface roundRunStrategy){
        Tester.getInstance().functionStart();
        this.roundRunStrategy = roundRunStrategy;
        Tester.getInstance().functionEnd();
    }
    public void moveStrategy(MoveStrategyInterface moveStrategy){
        Tester.getInstance().functionStart();
        this.moveStrategy = moveStrategy;
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
