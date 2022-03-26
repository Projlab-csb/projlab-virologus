package com.csb.skeletonTester.Tests;

import com.csb.collectables.Collectable;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.fields.Storage;
import com.csb.skeletonTester.Test;
import com.csb.virologist.Virologist;

import java.util.ArrayList;

public class CollectAcid extends Test {

    @Override
    public String getName() {
        return "Collect and discard AminoAcid";
    }

    @Override
    public void runTest() {
        ArrayList<Collectable> collectableArrayList= new ArrayList<Collectable>();
        collectableArrayList.add(new AminoAcid());
        collectableArrayList.add(new NucleicAcid());
        Virologist virologist = new Virologist();
        Storage storage = new Storage(collectableArrayList);
        virologist.collect(storage);

        virologist.discard(new AminoAcid(30));
    }

}