package com.csb.virologist;

import com.csb.agents.Agent;
import com.csb.collectables.Collectable;
import com.csb.collectables.gencodes.Gencode;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.fields.Field;
import com.csb.skeletonTester.Tester;
import com.csb.strategies.*;

import java.util.ArrayList;

public class Virologist {

    private DefenseStrategyInterface defenseStrategy;
    private DefenseStrategyInterface defaultDefenseStrategy;
    private MoveStrategyInterface moveStrategy;
    private MoveStrategyInterface defaultMoveStrategy;
    private RoundRunStrategyInterface roundRunStrategy;
    private RoundRunStrategyInterface defaultRoundRunStrategy;
    private int inventorySize;
    private AminoAcid aminoAcidStock;
    private NucleicAcid nucleicAcidStock;
    private ArrayList<Gencode> gencodes;

    public Virologist() {
        inventorySize = 0;
        nucleicAcidStock=new NucleicAcid(0);
        aminoAcidStock=new AminoAcid(0);
        gencodes=new ArrayList<Gencode>();
        defaultDefenseStrategy=new DefaultDefense();
        defaultRoundRunStrategy=new DefaultRoundRun();
        defaultMoveStrategy=new DefaultMove();
        defenseStrategy=defaultDefenseStrategy;
        roundRunStrategy=defaultRoundRunStrategy;
        moveStrategy=defaultMoveStrategy;

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

    public void collect(Field field) {
        Tester.getInstance().functionStart();

        ArrayList<Collectable> collectables = field.getCollectable();
        if (collectables != null) {
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

    public int getAminoAcid() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return aminoAcidStock.getAmount();

    }

    public void setAminoAcid(int aminoAcid) {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        aminoAcidStock.setAmount(aminoAcid);
    }

    public int getNucleicAcid() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return nucleicAcidStock.getAmount();
    }

    public void setNucleicAcid(int nucleicAcid) {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        nucleicAcidStock.setAmount(nucleicAcid);
    }

    public ArrayList<Gencode> getGencodes() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return gencodes;
    }

    public void setGencodes(ArrayList<Gencode> gencodes) {
        Tester.getInstance().functionStart();
        this.gencodes = gencodes;
        Tester.getInstance().functionEnd();
    }
    public void steal(Collectable coll, Virologist targetVirologist ){
        Tester.getInstance().functionStart();
        Collectable stolen=roundRunStrategy.handleSteal(coll, this, targetVirologist);
        if(stolen==null) return;
        stolen.collectBy(targetVirologist);
        Tester.getInstance().functionStart();
    }
    public void discard(Collectable coll){
        coll.discard(this);
    };



}
