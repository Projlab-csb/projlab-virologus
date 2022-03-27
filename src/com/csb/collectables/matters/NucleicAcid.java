package com.csb.collectables.matters;

import com.csb.skeletonTester.Tester;
import com.csb.virologist.Virologist;

/**
 * Class, what describes the AminoAcid matter with its amount. AminoAcid is one of the two ingredients of the Agents
 */
public class NucleicAcid extends Matter {

    /**
     * Constructors with or without parameter
     * the amount -1 means infinite amount
     */
    public NucleicAcid() {
        super();
    }

    public NucleicAcid(int n) {
        super(n);
    }

    @Override
    public void collectBy(Virologist virologist) {
        Tester.getInstance().functionStart();

        //if the source is a storage, the amount is infinite, the virologist fill his stock to full capacity
        if (this.getAmount() == -1) virologist.setNucleicAcid(
            virologist.getNucleicAcid() + virologist.getInventorySize() - virologist.getAminoAcid()
        );
        //if the source is an other virologist, the source is finite, so it is the minimum of these two: his full capacity, or the sum of his owned acid and stolen acid
        else virologist.setNucleicAcid(
            Math.min(
                virologist.getNucleicAcid() + this.getAmount(),
                virologist.getNucleicAcid() + virologist.getInventorySize() - virologist.getAminoAcid()
            )
        );

        Tester.getInstance().functionEnd();
    }

    /**
     *
     * @param virologist - who discard an amount of Aminoacid
     */
    @Override
    public void discard(Virologist virologist) {
        Tester.getInstance().functionStart();
        int discarded = this.getAmount();

        //if the player want to discard more acid than the virologist has, he discards all the virologist has
        virologist.setNucleicAcid(Math.max(virologist.getNucleicAcid() - discarded, 0));

        Tester.getInstance().functionEnd();
    }
}
