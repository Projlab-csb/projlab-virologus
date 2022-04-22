package com.csb.skeletonTester.Tests;

import com.csb.collectables.Collectable;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.fields.Storage;
import com.csb.skeletonTester.Test;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

import java.util.ArrayList;

public class CollectAminoAcid extends Test {

    /**
     * getName for the menu
     */
    @Override
    public String getName() {
        return "Collect AminoAcid";
    }


    /**
     * runTest for the start the function
     */
    @Override
    public void runTest() {

        Tester.getInstance().turnOffLogging();
        //Set the envirement for the test
        ArrayList<Collectable> collectableArrayList= new ArrayList<Collectable>();
        collectableArrayList.add(new AminoAcid());
        collectableArrayList.add(new NucleicAcid());
        Virologist virologist = new Virologist();

        Storage storage = new Storage(collectableArrayList);

        Tester.getInstance().turnOnLogging();
        //Collect AminoAcid
        virologist.collect(storage);

    }

}