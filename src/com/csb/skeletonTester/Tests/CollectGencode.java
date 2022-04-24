package com.csb.skeletonTester.Tests;

import com.csb.agents.Paralyzed;
import com.csb.collectables.Collectable;
import com.csb.collectables.equipments.Gloves;
import com.csb.collectables.gencodes.Gencode;
import com.csb.collectables.matters.AminoAcid;
import com.csb.collectables.matters.NucleicAcid;
import com.csb.fields.Lab;
import com.csb.fields.Shelter;
import com.csb.skeletonTester.Test;
import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;
import java.util.ArrayList;

public class CollectGencode extends Test {

    /**
     * * getName for the menu
     */

    public String getName() {
        return "Collect Gencode";
    }

    /**
     * runTest for the start the function
     */
    public void runTest() {
        Tester.getInstance().turnOffLogging();
        //Set the envirement for the test
        AminoAcid a = new AminoAcid(30);
        NucleicAcid n = new NucleicAcid(30);
        Paralyzed p = new Paralyzed();
        Virologist virologist = new Virologist("bob");
        Lab lab = new Lab(
            new ArrayList<Collectable>() {
                {
                    add(new Gencode(p, a, n));
                }
            }
        );

        Tester.getInstance().turnOnLogging();
        //run the function to be tested
        virologist.collect(lab);
    }
}
