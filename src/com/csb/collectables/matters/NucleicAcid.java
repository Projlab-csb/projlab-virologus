package com.csb.collectables.matters;

import com.csb.skeletonTester.Tester;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.virologist.Virologist;

public class NucleicAcid extends Matter {

    public NucleicAcid(){super();}
    public NucleicAcid(int n){super(n);}

    @Override
    public void collectBy(Virologist virologist) {
        Tester.getInstance().functionStart();
        if (this.getAmount()==0) virologist.setNucleicAcid(virologist.getNucleicAcid()+ virologist.getInventorySize()- virologist.getAminoAcid());
        else virologist.setNucleicAcid(Math.min(virologist.getNucleicAcid()+ this.getAmount(),virologist.getNucleicAcid()+virologist.getInventorySize()- virologist.getAminoAcid()));
        Tester.getInstance().functionEnd();
    }

    @Override
    public void discard(Virologist virologist) {
        Tester.getInstance().functionStart();
        int discarded=this.getAmount();
        virologist.setNucleicAcid(Math.max(virologist.getNucleicAcid()-discarded, 0));
        Tester.getInstance().functionEnd();
    }
}
