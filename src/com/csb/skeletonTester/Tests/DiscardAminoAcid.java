package com.csb.skeletonTester.Tests;

import com.csb.collectables.Collectable;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.fields.Storage;
import com.csb.skeletonTester.Test;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;
import java.util.ArrayList;

public class DiscardAminoAcid extends Test {

    /**
     * getName for the menu
     */
    @Override
    public String getName() {
        return "Discard AminoAcid";
    }

    /**
     * runTest for the start the function
     */
    @Override
    public void runTest() {
        Tester.getInstance().turnOffLogging();
        //Set the envirement for the test
        ArrayList<Collectable> collectableArrayList = new ArrayList<Collectable>();
        collectableArrayList.add(new AminoAcid());
        collectableArrayList.add(new NucleicAcid());
        Virologist virologist = new Virologist("bob");
        Storage storage = new Storage(collectableArrayList);
        //Collect AminoAcid
        virologist.collect(storage);

        Tester.getInstance().turnOnLogging();
        //run the function to be tested
        virologist.discard(new AminoAcid(30));
    }
}
