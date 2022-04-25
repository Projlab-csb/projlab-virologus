package com.csb.collectables.gencodes;

import com.csb.agents.Agent;
import com.csb.collectables.Collectable;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.skeletonTester.Tester;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Gencodes is one of the most important Class of the game
 * Virologists searching for these in labs.
 * A Gencode says how many AminoAcid and NucleicAcid is needed to craft an agent
 */
public class Gencode implements Collectable, Serializable {

    public static final int MIN_AMINO_ACID = 1;
    public static final int MAX_AMINO_ACID = 3;
    public static final int MIN_NUCLEIC_ACID = 1;
    public static final int MAX_NUCLEIC_ACID = 3;

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
        this.agent = agent;
        requiredAminoAcid = requriedAmino;
        requiredNucleicAcid = requriedNucleic;
    }

    /**
     * Getter for required Amino Acids
     * @return requiredAminoAcid - How many AminoAcid is needed for that agent
     */
    public NucleicAcid getRequiredNucleicAcid() {
        return requiredNucleicAcid;
    }

    /**
     * Getter for required AminoAcid
     * @return requiredAminoAcid - How many AminoAcid is needed for that agent
     */
    public AminoAcid getRequiredAminoAcid() {
        return requiredAminoAcid;
    }

    /**
     * Getter for the agent
     * @return agent - what can a virologist craft from a gencode
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * A gencode is collected by a virologist.
     * @param virologist who learns the gencode from the lab
     */
    public void collectBy(Virologist virologist) {
        ArrayList<Gencode> gc = virologist.getGencodes();
        gc.add(this);
        virologist.setGencodes(gc);
        System.out.println(
            virologist.getName() +
            " has collected/learned a Gencode, with that, he can create ang Agent " +
            agent.getClass().getSimpleName()
        );
    }

    /**
     * The virologists dont't discrard their gencodes thus this have an emty function body
     * @param virologist who discards the gencode
     */
    @Override
    public void discard(Virologist virologist) {
        return;
    }

    public String toString() {
        return agent.getClass().getSimpleName();
    }
}
