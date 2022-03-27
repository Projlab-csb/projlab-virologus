package com.csb.collectables.gencodes;

import com.csb.agents.Agent;
import com.csb.collectables.Collectable;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.skeletonTester.Tester;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;
import java.util.ArrayList;

/**
 * Gencodes is one of the most important Class of the game
 * Virologists searching for these in labs.
 * A Gencode says how many AminoAcid and NucleicAcid is needed to craft an agent
 */
public class Gencode implements Collectable {

    private AminoAcid requiredAminoAcid;
    private NucleicAcid requiredNucleicAcid;
    private Agent agent;

    /**
     *
     * @param agent - what can a Virologist craft from a Gencode
     * @param requriedAmino - How many AminoAcid is needed for that agent
     * @param requriedNucleic - How many NucleicAcid is needed for that agent
     */
    public Gencode(Agent agent, AminoAcid requriedAmino, NucleicAcid requriedNucleic) {
        agent = agent;
        requiredAminoAcid = requriedAmino;
        requiredNucleicAcid = requriedNucleic;
    }

    /**
     *Getters for required Amino and NoulecAcid, and the agent
     */
    public NucleicAcid getRequiredNucleicAcid() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return requiredNucleicAcid;
    }

    public AminoAcid getRequiredAminoAcid() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return requiredAminoAcid;
    }

    public Agent getAgent() {
        Tester.getInstance().functionStart();
        Tester.getInstance().functionEnd();
        return agent;
    }

    /**
     * A gencode is collected by a virologist.
     * @param virologist who learns the gencode from the lab
     */
    public void collectBy(Virologist virologist) {
        Tester.getInstance().functionStart();
        ArrayList<Gencode> gc = virologist.getGencodes();
        gc.add(this);
        virologist.setGencodes(gc);
        Tester.getInstance().functionEnd();
    }

    /**
     *The virologists dont't discrard their gencodes thus this have an emty function body
     */
    @Override
    public void discard(Virologist virologist) {}
}
