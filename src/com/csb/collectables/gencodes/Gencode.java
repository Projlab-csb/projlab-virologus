package com.csb.collectables.gencodes;

import com.csb.agents.Agent;
import com.csb.collectables.Collectable;
import com.csb.skeletonTester.Tester;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

import java.util.ArrayList;

public class Gencode implements Collectable {
    private AminoAcid requiredAminoAcid;
    private NucleicAcid requiredNucleicAcid;
    private Agent agent;


    public Gencode(Agent agent, int requriedAmino, int requriedNucleic){
        producedAgent=agent;
        requriedAminoAcid=requriedAmino;
        requriedNucleicAcid=requriedNucleic;

    }

    public NucleicAcid getRequiredNucleicAcid(){
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return requiredNucleicAcid;
    }
    public AminoAcid getRequiredAminoAcid(){
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return requiredAminoAcid;
    }

    public Agent getAgent() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return agent;
    }
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
