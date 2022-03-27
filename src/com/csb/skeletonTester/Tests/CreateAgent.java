package com.csb.skeletonTester.Tests;

import com.csb.agents.Paralyzed;
import com.csb.collectables.gencodes.Gencode;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.skeletonTester.Test;
import com.csb.virologist.*;

/**
 * This class tests the creation of a new agent.
 */
public class CreateAgent extends Test {

    public String getName() {
        return "Create Agent";
    }

    public void runTest() {
        Virologist virologist = new Virologist();
        Gencode genCode = new Gencode(new Paralyzed(), new AminoAcid(50), new NucleicAcid(50));
        virologist.createAgent(genCode);
    }
}
