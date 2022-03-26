package com.csb.collectables.matters;

import com.csb.skeletonTester.Tester;
import com.csb.skeletonTester.UserInputHandler;
import com.csb.virologist.Virologist;

/**
 * Class, what describes the AminoAcid matter with its amount. AminoAcid is one of the two ingredients of the Agents
 */
public class AminoAcid extends Matter {

    /**
     * Constructors with or without parameter
     * the amount -1 means infinite amount
     */
    public AminoAcid(){super();}
    public AminoAcid(int n){super(n);}

    /**
     *
     * @param virologist - a virologist who collect the matter
     */
    @Override
    public void collectBy(Virologist virologist) {
        Tester.getInstance().functionStart();
        //
        if (this.getAmount()==-1) virologist.setAminoAcid(virologist.getAminoAcid()+ virologist.getInventorySize()- virologist.getNucleicAcid());

        //
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
