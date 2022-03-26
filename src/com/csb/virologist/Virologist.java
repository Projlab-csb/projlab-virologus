package com.csb.virologist;

import com.csb.agents.*;
import com.csb.collectables.Collectable;
import com.csb.collectables.equipments.Equipment;
import com.csb.collectables.gencodes.Gencode;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.fields.Field;
import com.csb.skeletonTester.Tester;
import com.csb.strategies.DefenseStrategyInterface;
import com.csb.strategies.MoveStrategyInterface;
import com.csb.strategies.ProtectionStrategyInterface;
import com.csb.strategies.RoundRunStrategyInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Virologist {

    private DefenseStrategyInterface defenseStrategy;
    private RoundRunStrategyInterface roundRunStrategy;
    private MoveStrategyInterface moveStrategy;
    private ProtectionStrategyInterface protectionStrategy;
    private AminoAcid aminoAcid;
    private NucleicAcid nucleicAcid;


    private int inventorySize;
    private List<Agent> agentlist = Arrays.asList(new Paralyzed(), new Protection(), new VitusDance());

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
    public void setmoveStrategy(MoveStrategyInterface moveStrategy){
        Tester.getInstance().functionStart();
        this.moveStrategy = moveStrategy;
        Tester.getInstance().functionEnd();
    }

    public void setProtectionStrategy(ProtectionStrategyInterface protectionStrategy){
        Tester.getInstance().functionStart();
        this.protectionStrategy = protectionStrategy;
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

    public void createAgent(Gencode genCode){
        Tester.getInstance().functionStart();
        genCode.getRequiredNucleicAcid();
        genCode.getRequiredAminoAcid();
        this.storeAgent(genCode.getAgent());
        Tester.getInstance().functionEnd();
    }
    public void storeAgent(Agent agent){
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
    }

    public void refreshAgents(){
        Tester.getInstance().functionStart();
        for(Agent agent: agentlist){
            agent.decreaseTTL();
            agent.removeEffect(this);
        }
        Tester.getInstance().functionEnd();
    }

    public void discard(Virologist virologist, Equipment equipment) {
        Tester.getInstance().functionStart();
        equipment.discard(virologist);
        Tester.getInstance().functionEnd();
    }

    public void setAminoAcid(AminoAcid aminoAcid) {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionStart();
        aminoAcid = aminoAcid;
        Tester.getInstance().functionEnd();
    }

    public void setNucleicAcid(NucleicAcid nucleicAcid) {
        nucleicAcid = nucleicAcid;
        Tester.getInstance().functionEnd();
    }
}
