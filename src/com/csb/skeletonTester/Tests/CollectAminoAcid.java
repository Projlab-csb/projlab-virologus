package com.csb.skeletonTester.Tests;

import com.csb.collectables.Collectable;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.fields.Storage;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

import java.util.ArrayList;

public class CollectAminoAcid extends Test {

    /**
     * getName for the menu
     */
    @Override
    public String getName() {
        return "Collect and discard AminoAcid";
    }


    /**
     * runTest for the start the function
     */
    @Override
    public void runTest() {

        //Set the envirement for the test
        ArrayList<Collectable> collectableArrayList= new ArrayList<Collectable>();
        collectableArrayList.add(new AminoAcid());
        collectableArrayList.add(new NucleicAcid());
        Virologist virologist = new Virologist();

        Storage storage = new Storage(collectableArrayList);
        //Collect AminoAcid
        virologist.collect(storage);

        //run the function to be tested
        virologist.discard(new AminoAcid(30));
    }

}