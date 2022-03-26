package com.csb.collectables.gencodes;

import com.csb.agents.Agent;
import com.csb.collectables.Collectable;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

public class Gencode implements Collectable {
    private AminoAcid requiredAminoAcid;
    private NucleicAcid requiredNucleicAcid;
    private Agent agent;
    @Override
    public void collectBy(Virologist virologist) {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
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
}
