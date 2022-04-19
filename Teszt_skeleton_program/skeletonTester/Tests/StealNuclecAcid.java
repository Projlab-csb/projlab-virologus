package com.csb.skeletonTester.Tests;

import com.csb.agents.Paralyzed;
import com.csb.collectables.Collectable;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.fields.Storage;
import com.csb.skeletonTester.Test;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

import java.util.ArrayList;

public class StealNuclecAcid extends Test {

    /**
     * * getName for the menu
     */

    public String getName() {
        return "Steal AminoAcid";
    }

    /**
     * runTest for the start the function
     */
    public void runTest() {

        Tester.getInstance().turnOffLogging();
        //Set the envirement for the test
        ArrayList<Collectable> collectableArrayList= new ArrayList<Collectable>();
        collectableArrayList.add(new NucleicAcid());
        collectableArrayList.add(new AminoAcid());
        Virologist targetvirologist = new Virologist();
        Storage storage = new Storage(collectableArrayList);

        //Collect NucleicAcid
        targetvirologist.collect(storage);

        Virologist robbervirologist = new Virologist();

        Paralyzed paralyzedAgent = new Paralyzed();
        paralyzedAgent.applyEffect(targetvirologist);

        Tester.getInstance().turnOnLogging();
        //run the function to be tested
        robbervirologist.steal(new NucleicAcid(10), targetvirologist);
    }
}