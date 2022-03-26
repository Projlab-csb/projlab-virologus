package com.csb.collectables.matters;

import com.csb.skeletonTester.Tester;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.virologist.Virologist;

public class AminoAcid extends Matter {

    public AminoAcid(){super();}
    public AminoAcid(int n){super(n);}

    @Override
    public void collectBy(Virologist virologist) {
        Tester.getInstance().functionStart();
        if (this.getAmount()==0) virologist.setAminoAcid(virologist.getAminoAcid()+ virologist.getInventorySize()- virologist.getNucleicAcid());
        else virologist.setAminoAcid(Math.min(virologist.getAminoAcid()+ this.getAmount(),virologist.getAminoAcid()+ virologist.getInventorySize()- virologist.getNucleicAcid()));
        Tester.getInstance().functionEnd();
    }

    @Override
    public void discard(Virologist virologist) {
        Tester.getInstance().functionStart();
        int discarded=this.getAmount();
        virologist.setAminoAcid(Math.max(virologist.getAminoAcid()-discarded, 0));
        Tester.getInstance().functionEnd();
    }
}
