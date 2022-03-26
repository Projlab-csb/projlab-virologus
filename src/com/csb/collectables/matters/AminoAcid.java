package com.csb.collectables.matters;

import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

public class AminoAcid extends Matter {

    @Override
    public void collectBy(Virologist virologist) {
        Tester.getInstance().functionStart();
        virologist.setAminoAcid(this);
        Tester.getInstance().functionEnd();
    }
}
