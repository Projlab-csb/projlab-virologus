package com.csb.collectables.gencodes;

import com.csb.agents.Agent;
import com.csb.collectables.Collectable;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

import java.util.ArrayList;

public class Gencode implements Collectable {

    private Agent producedAgent;
    private int requriedAminoAcid;
    private int requriedNucleicAcid;

    public Gencode(Agent agent, int requriedAmino, int requriedNucleic){
        producedAgent=agent;
        requriedAminoAcid=requriedAmino;
        requriedNucleicAcid=requriedNucleic;

    }

    @Override
    public void collectBy(Virologist virologist) {
        Tester.getInstance().functionStart();
        ArrayList<Gencode> gc=virologist.getGencodes();
        gc.add(this);
        virologist.setGencodes(gc);
        Tester.getInstance().functionEnd();
    }

    @Override
    public void discard(Virologist virologist) {

    }
}
